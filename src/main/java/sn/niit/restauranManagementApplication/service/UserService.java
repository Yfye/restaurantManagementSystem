package sn.niit.restauranManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import sn.niit.restauranManagementApplication.domain.Category;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.dto.UserDto;

public interface UserService {

	Optional<User> findByUserId(Long userId);

	void saveUser(UserDto userDto);

	void saveEmployee(UserDto userDto);

	User findUserByEmail(String email);

	List<UserDto> findAllUsers();
}
