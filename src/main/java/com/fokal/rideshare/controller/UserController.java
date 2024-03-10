package com.fokal.rideshare.controller;

import com.fokal.rideshare.dto.UserCreateRequest;
import com.fokal.rideshare.dto.UserGetAllResponse;
import com.fokal.rideshare.dto.UserGetResponse;
import com.fokal.rideshare.dto.UserUpdateRequest;
import com.fokal.rideshare.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public List<UserGetAllResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserGetResponse getUserById(Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("")
    public UserGetResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public UserGetResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest){
        return userService.updateUser(id, userUpdateRequest);
    }
}
