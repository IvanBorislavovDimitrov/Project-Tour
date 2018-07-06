package app.services.api;

import app.model.dtos.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    void register(UserDto userDto);
}
