package group.raf.webproject.service.user;

import group.raf.webproject.database.model.User;
import group.raf.webproject.dto.token.TokenRequestDTO;
import group.raf.webproject.dto.token.TokenResponseDTO;
import group.raf.webproject.dto.user.UserRequestDTO;
import group.raf.webproject.dto.user.UserResponseDTO;
import group.raf.webproject.mapper.UserMapper;
import group.raf.webproject.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    @Override
    public UserResponseDTO add(UserRequestDTO userRequestDTO) {
        User user = userMapper.UserRequestDtoToUser(userRequestDTO);
        return userMapper.UserToUserResponseDto(userRepository.addUser(user));
    }

    @Override
    public List<UserResponseDTO> findAll() {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for(User user : userRepository.allUsers()){
            userResponseDTOS.add(userMapper.UserToUserResponseDto(user));
        }
        return userResponseDTOS;
    }

    @Override
    public UserResponseDTO findById(Integer id) {
        return userMapper.UserToUserResponseDto(userRepository.findUserById(id));
    }

    @Override
    public UserResponseDTO update(Integer id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findUserByName(userRequestDTO.getFirstName());
        return userMapper.UserToUserResponseDto(userRepository.updateUser(user));
    }

    @Override
    public UserResponseDTO activate(Integer id, boolean activate) {
        return userMapper.UserToUserResponseDto(userRepository.setActive(id, activate));
    }

    @Override
    public TokenResponseDTO login(TokenRequestDTO tokenRequestDTO) {
        // todo
        return null;
    }

    @Override
    public UserResponseDTO delete(Integer id) {
        return userMapper.UserToUserResponseDto(userRepository.deleteUserById(id));
    }
}
