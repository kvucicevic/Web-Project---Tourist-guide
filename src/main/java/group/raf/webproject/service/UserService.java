package group.raf.webproject.service;

import group.raf.webproject.dto.token.TokenRequestDTO;
import group.raf.webproject.dto.token.TokenResponseDTO;
import group.raf.webproject.dto.user.UserRequestDTO;
import group.raf.webproject.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO add(UserRequestDTO UserRequestDTO);

    List<UserResponseDTO> findAll();

    UserResponseDTO findById(Integer id);

    UserResponseDTO update(Integer id, UserRequestDTO UserRequestDTO);

    UserResponseDTO activate(Integer id, boolean activate);

    TokenResponseDTO login(TokenRequestDTO tokenRequestDTO);

    UserResponseDTO delete(Integer id);
}
