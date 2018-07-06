package app.services.imp;

import app.entities.Role;
import app.model.dtos.UserDto;
import app.entities.User;
import app.repostiories.RoleRepository;
import app.repostiories.UserRepository;
import app.services.api.UserService;
import app.validation_utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {

    private static final String INVALID_USER_MESSAGE = "Invalid user!";
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAll() {
        return this.userRepository.findAll().stream()
                .map(u -> new UserDto(u.getUsername(), u.getEmail(), u.getPhoneNumber()))
                .collect(Collectors.toList());
    }

    @Override
    public void register(UserDto userDto) {
        if (!ValidationUtil.isValid(userDto)) {
            throw new IllegalArgumentException(INVALID_USER_MESSAGE);
        }

        User user = new User(userDto.getUsername(), userDto.getEmail(), userDto.getPhoneNumber(),
                this.passwordEncoder.encode(userDto.getPassword()));

        Role role = this.roleRepository.findFirstByName("USER");
        role.getUsers().add(user);
        user.getRoles().add(role);

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        Set<Role> roles = user.getRoles();
        Set<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName())).collect(Collectors.toSet());


        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                grantedAuthorities);
        return userDetails;
    }
}
