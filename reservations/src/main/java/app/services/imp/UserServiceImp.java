package app.services.imp;

import app.entities.Privilege;
import app.model.dtos.UserDto;
import app.entities.User;
import app.model.dtos.UserProfileDto;
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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {

    private static final String INVALID_USER_MESSAGE = "Invalid user!";
    private PasswordEncoder passwordEncoder;
    private GenericRepository<Privilege> roleRepository;
    private GenericRepository<User> userRepository;

    @Autowired
    public UserServiceImp(PasswordEncoder passwordEncoder,
                          @Qualifier("Role") GenericRepository<Privilege> roleRepository, @Qualifier("User") GenericRepository<User> userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAll() {
        return this.userRepository.getAll().stream()
                .map(u -> new UserDto() {{
                    this.setEmail(u.getEmail());
                    this.setPassword(u.getPassword());
                    this.setPhoneNumber(u.getPhone());
                    this.setUsername(u.getUsername());
                    this.setRoles(String.join(", ", u.getRoles().stream()
                            .map(Privilege::getName).collect(Collectors.toList())));
                }})
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
            user.getRoles().add(role);
        } else {
            Privilege role = roles.stream().filter(r -> r.getName().equals("USER")).findFirst().orElse(null);
            if (role == null) {
                role = new Privilege();
                role.setName("USER");
                this.roleRepository.create(role);
            }
            role.getUsers().add(user);
            user.getRoles().add(role);
        }

        this.userRepository.create(user);
    }

    @Override
    public UserProfileDto findByUsername(String username) {
        return this.userRepository.getAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .map(user -> new UserProfileDto(){{
                    this.setEmail(user.getEmail());
                    this.setUsername(user.getUsername());
                    this.setPhoneNumber(user.getPhone());
                }})
                .findFirst()
                .orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = this.userRepository.getAll();

        User user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        Set<Privilege> roles = user.getRoles();
        Set<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName())).collect(Collectors.toSet());


        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities);

        return userDetails;
    }
}
