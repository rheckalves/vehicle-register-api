package com.carregisterapi.users.controller;
import com.carregisterapi.users.service.UserServiceImpl;
import com.carregisterapi.exceptions.BadRequestException;
import com.carregisterapi.users.model.User;
import com.carregisterapi.users.model.UserDTO;
import com.carregisterapi.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import com.carregisterapi.config.ModelMapperConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final ModelMapperConfig mapper;
    @GetMapping("/users")
    public List<UserDTO> allUsers() {
        System.out.println(userService.getLoggedUser());
        return this.userRepository.findAll()
                .stream()
                .map(this::parseUserToDTO)
                .collect(Collectors.toList());
    }
    @PostMapping("/users")
    public ResponseEntity<Optional<UserDTO>> createCar(@Valid @RequestBody User user) {
        verifyExistentUser(user);
        user.setIsAdmin(false);
        user.setPassword(this.userService.passwordEncript(user.getPassword()));
        Optional<User> userOptional = Optional.of(
                this.userRepository.save(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.map(this::parseUserToDTO));
    }
    private UserDTO parseUserToDTO(User user) {
        return this.mapper.modelMapper().map(user, UserDTO.class);
    }


    private void verifyExistentUser(User user) {
        Optional<User> userEmail = Optional.ofNullable(
                this.userRepository.findByEmail(user.getEmail()));
        if (userEmail.isPresent()) {
            throw  new BadRequestException("Usuário " + user.getEmail() + " já existe!");
        }
    }
}
