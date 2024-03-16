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
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<UserGetAllResponse> getAllUsers() {
        return userRepository.findAllByIsActiveTrue().stream()
                .map(user -> modelMapper.map(user, UserGetAllResponse.class))
                .toList();
    }

    public UserGetResponse getUserById(Long id) {
        return modelMapper.map(userRepository.findByIdAndIsActiveTrue(id).orElseThrow(), UserGetResponse.class);
    }

    public UserGetResponse createUser(UserCreateRequest userCreateRequest) {
        User user = modelMapper.map(userCreateRequest, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(userCreateRequest.getPassword()));
        user.setActive(true);
        return modelMapper.map(userRepository.save(user), UserGetResponse.class);
    }

    public void deleteUser(Long id) {
        Optional<User> temp = userRepository.findById(id);

        if(temp.isEmpty()){
            throw new RuntimeException("User Not Found");
        }
        User user = temp.get();
        user.setActive(false);
        userRepository.save(user);
    }

    public UserGetResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow();
        modelMapper.map(userUpdateRequest, user);
        return modelMapper.map(userRepository.save(user), UserGetResponse.class);
    }
}
