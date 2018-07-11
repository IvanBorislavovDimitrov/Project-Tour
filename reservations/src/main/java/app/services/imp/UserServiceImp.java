package app.services.imp;

import app.entities.*;
import app.model.dtos.*;
import app.repostiories.base.GenericRepository;
import app.services.api.UserService;
import app.validation_utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {

    private static final String INVALID_USER_MESSAGE = "Invalid user!";
    private PasswordEncoder passwordEncoder;
    private final GenericRepository<Privilege> roleRepository;
    private final GenericRepository<User> userRepository;
    private final GenericRepository<TourGuide> tourGuideRepository;
    private final GenericRepository<Room> roomRepository;
    private final GenericRepository<Reservation> reservationRepository;

    @Autowired
    public UserServiceImp(PasswordEncoder passwordEncoder,
                          @Qualifier("Privilege") GenericRepository<Privilege> roleRepository, @Qualifier("User") GenericRepository<User> userRepository, GenericRepository<TourGuide> tourGuideRepository, GenericRepository<Room> roomRepository, GenericRepository<Reservation> reservationRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.tourGuideRepository = tourGuideRepository;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<UserDto> findAll() {
        return this.userRepository.getAll().stream()
                .map(u -> new UserDto(u.getUsername(), u.getEmail(), u.getPhone(), u.getPassword(),
                        String.join(", ", u.getPrivileges()
                                .stream().map(Privilege::getName).collect(Collectors.toList()))))
                .collect(Collectors.toList());
    }

    @Override
    public void register(UserDto userDto) {
        if (!ValidationUtil.isValid(userDto)) {
            throw new IllegalArgumentException(INVALID_USER_MESSAGE);
        }

        User user = new User(userDto.getUsername(), userDto.getEmail(), userDto.getPhoneNumber(),
                this.passwordEncoder.encode(userDto.getPassword()));

        List<Privilege> roles = this.roleRepository.getAll();
        List<User> users = this.userRepository.getAll();

        if (users.size() == 0) {
            Privilege role = roles.stream().filter(r -> r.getName().equals("ADMIN")).findFirst().orElse(null);
            if (role == null) {
                role = new Privilege();
                role.setName("ADMIN");
                this.roleRepository.create(role);
            }
            role.getUsers().add(user);
            user.getPrivileges().add(role);
        } else {
            Privilege role = roles.stream().filter(r -> r.getName().equals("USER")).findFirst().orElse(null);
            if (role == null) {
                role = new Privilege();
                role.setName("USER");
                this.roleRepository.create(role);
            }
            role.getUsers().add(user);
            user.getPrivileges().add(role);
        }

        this.userRepository.create(user);
    }

    @Override
    public UserProfileDto findByUsername(String username) {
        return this.userRepository.getAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .map(user -> new UserProfileDto(user.getUsername(), user.getEmail(), user.getPhone()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addReservation(RoomDto room, TourGuideDto tourGuide, String username, String date) {
        Room roomEntity = this.roomRepository.getAll().stream()
                .filter(r -> r.getId() == room.getId())
                .findFirst()
                .orElse(null);

        TourGuide guideEntity = this.tourGuideRepository.getAll().stream()
                .filter(t -> t.getId() == tourGuide.getId())
                .findFirst()
                .orElse(null);

        User userEntity = this.userRepository.getAll().stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElse(null);

        Reservation reservation = new Reservation();
        try {
            reservation.setDate(new SimpleDateFormat("yy-MM-yyyy").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.getRooms().add(roomEntity);
        reservation.setTourGuide(guideEntity);
        userEntity.getReservations().add(reservation);
        reservation.setUser(userEntity);

        this.reservationRepository.create(reservation);
    }

    @Override
    public List<ReservationForShowingInProfile> getAllReservationsForShowing(String username) {
        List<User> users = this.userRepository.getAll().stream().filter(u -> u.getUsername().equalsIgnoreCase(username))
                .collect(Collectors.toList());
        List<ReservationForShowingInProfile> reservations = new ArrayList<>();
        users.forEach(user -> {
            user.getReservations().forEach(r -> {
                ReservationForShowingInProfile reservation = new ReservationForShowingInProfile();
                reservation.setDate(r.getDate().toString());
                reservation.setGuideName(r.getTourGuide().getName());
                reservation.setGuideNumber(r.getTourGuide().getPhoneNumber());
                reservations.add(reservation);
            });
        });

        return reservations;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = this.userRepository.getAll();

        User user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        Set<Privilege> roles = user.getPrivileges();
        Set<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName())).collect(Collectors.toSet());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities);

        return userDetails;
    }
}
