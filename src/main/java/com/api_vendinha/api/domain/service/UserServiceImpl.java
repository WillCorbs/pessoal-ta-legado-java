package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação do serviço de usuários.
 *
 * Esta classe fornece a implementação dos métodos definidos na interface UserServiceInterface,
 * lidando com a lógica de negócios relacionada aos usuários, como criar e atualizar usuários.
 */
@Service
public class UserServiceImpl implements UserServiceInterface {

    // Repositório para a persistência de dados de usuários.
    private final UserRepository userRepository;

    /**
     * Construtor para injeção de dependência do UserRepository.
     *
     * @param userRepository O repositório de usuários a ser injetado.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Salva um novo usuário ou atualiza um usuário existente.
     *
     * Cria uma nova entidade User a partir dos dados fornecidos no UserRequestDto, persiste essa
     * entidade no banco de dados, e retorna um UserResponseDto com as informações do usuário salvo.
     *
     * @param userRequestDto DTO contendo os dados do usuário a ser salvo ou atualizado.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado e o nome.
     */
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        // Cria uma nova instância de User.
        User user = new User();
        // Define o nome do usuário a partir do DTO.
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setCpf_cnpj(userRequestDto.getCpf_cnpj());
        user.setIs_active(Boolean.TRUE);
        user.setCep(userRequestDto.getCep());

        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        User savedUser = userRepository.save(user);

        // Cria um DTO de resposta com as informações do usuário salvo.
        return getUserResponse(savedUser);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User userExist = userRepository.findById(id).orElseThrow();

        return getUserResponse(userExist);
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User userExist = userRepository.findById(id).orElseThrow();
        userExist.setName(userRequestDto.getName());
        userExist.setEmail(userRequestDto.getEmail());
        userExist.setPassword(userRequestDto.getPassword());
        userExist.setCpf_cnpj(userRequestDto.getCpf_cnpj());
        userExist.setIs_active(Boolean.TRUE);
        userExist.setCep(userRequestDto.getCep());

        userRepository.save(userExist);

        return getUserResponse(userExist);
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
        return userRepository.findAll().stream().map(user -> {
            UserResponseDto userResponseDto = new UserResponseDto();

            userResponseDto.setId(user.getId());
            userResponseDto.setName(user.getName());
            userResponseDto.setEmail(user.getEmail());
            userResponseDto.setPassword(user.getPassword());
            userResponseDto.setCpf_cnpj(user.getCpf_cnpj());
            userResponseDto.setIs_active(Boolean.TRUE);
            userResponseDto.setCep(user.getCep());

            return userResponseDto;
        }).toList();
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
