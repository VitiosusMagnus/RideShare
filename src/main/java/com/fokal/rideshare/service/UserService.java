package com.fokal.rideshare.service;

import com.fokal.rideshare.dto.UserCreateRequest;
import com.fokal.rideshare.dto.UserGetAllResponse;
import com.fokal.rideshare.dto.UserGetResponse;
import com.fokal.rideshare.dto.UserUpdateRequest;
import com.fokal.rideshare.model.User;
import com.fokal.rideshare.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UserGetAllResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserGetAllResponse.class))
                .toList();
    }

    public UserGetResponse getUserById(Long id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(), UserGetResponse.class);
    }

    public UserGetResponse createUser(UserCreateRequest userCreateRequest) {
        User user = modelMapper.map(userCreateRequest, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userCreateRequest.getPassword()));
        return modelMapper.map(userRepository.save(user), UserGetResponse.class);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserGetResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow();
        modelMapper.map(userUpdateRequest, user);
        return modelMapper.map(userRepository.save(user), UserGetResponse.class);
    }
}
