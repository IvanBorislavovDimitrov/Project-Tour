package app.services.api;

import app.entities.User;
import app.model.dtos.UserDto;
import app.model.dtos.UserProfileDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    void register(UserDto userDto);

    UserProfileDto findByUsername(String username);
}
