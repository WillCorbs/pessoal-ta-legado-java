package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServiceInterface {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = new User();

        return getUserResponse(setUserAttributes(user, userRequestDto));
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User userExist = userRepository.findById(id).orElseThrow();

        return getUserResponse(userExist);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User userExist = userRepository.findById(id).orElseThrow();

        return getUserResponse(setUserAttributes(userExist, userRequestDto));
    }

    @Override
    public UserResponseDto updateStatus(Long id, UserRequestDto userRequestDto) {
        User userExist = userRepository.findById(id).orElseThrow();
        userExist.setIs_active(userRequestDto.getIs_active());

        userRepository.save(userExist);

        return getUserResponse(userExist);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::getUserResponse).toList();
    }


    private User setUserAttributes(User user, UserRequestDto userRequestDto) {
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setCpf_cnpj(userRequestDto.getCpf_cnpj());
        user.setIs_active(Boolean.TRUE);
        user.setCep(userRequestDto.getCep());

        userRepository.save(user);

        return user;
    }

    private UserResponseDto getUserResponse(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setCpf_cnpj(user.getCpf_cnpj());
        userResponseDto.setIs_active(Boolean.TRUE);
        userResponseDto.setCep(user.getCep());

        return userResponseDto;
    }
}
