package sn.niit.restauranManagementApplication.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.niit.restauranManagementApplication.domain.Role;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.dto.UserDto;
import sn.niit.restauranManagementApplication.repository.RoleRepository;
import sn.niit.restauranManagementApplication.repository.UserRepository;
import sn.niit.restauranManagementApplication.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
    public Optional<User> findByUserId(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void saveUser(UserDto userDto, String _role) {
        User user = new User();
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

        switch (_role) {
            case "user":
                addRoleToUser(user.getEmail(), "ROLE_USER");
                break;

            case "employee":
                addRoleToUser(user.getEmail(), "ROLE_EMPLOYEE");
                break;

            default:
                throw new RuntimeException("Registration process failed !");
        }

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
        if (role == null) {
            role = checkRoleExist("ROLE_EMPLOYEE");
        }
        user.setRoles(Arrays.asList(role));

    }

    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        if (user != null && role != null) {
            Collection<Role> roles = user.getRoles();
            roles.add(role);
            user.setRoles(roles);
            userRepository.save(user);
        } else
            throw new RuntimeException("Operation Failed!");
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

    private UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setPrenom(user.getPrenom());
        userDto.setNom(user.getNom());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(String roleName) {
        if (roleName == "")
            throw new RuntimeException("Role name cannot be empty!!");
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }
}
