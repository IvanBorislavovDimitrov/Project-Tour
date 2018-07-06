package app.services.imp;

import app.model.dtos.UserDto;
import app.entities.User;
import app.repostiories.UserRepository;
import app.services.api.UserService;
import app.validation_utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {

    private static final String INVALID_USER_MESSAGE = "Invalid user!";
    private final PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
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

        this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new HashSet<>());

        return userDetails;
    }
}
