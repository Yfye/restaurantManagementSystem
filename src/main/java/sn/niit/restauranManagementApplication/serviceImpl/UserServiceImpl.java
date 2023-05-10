package sn.niit.restauranManagementApplication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.niit.restauranManagementApplication.domain.Role;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.dto.UserDto;
import sn.niit.restauranManagementApplication.repository.RoleRepository;
import sn.niit.restauranManagementApplication.repository.UserRepository;
import sn.niit.restauranManagementApplication.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist("ROLE_USER");
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public void saveEmployee(UserDto userDto) {
        User user = new User();
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_EMPLOYEE");
        if(role == null){
            role = checkRoleExist("ROLE_EMPLOYEE");
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();

        userDto.setPrenom(user.getPrenom());
        userDto.setNom(user.getNom());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(String roleName){
        if (roleName == "")
            throw new RuntimeException("Role name cannot be empty!!");
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }
}
