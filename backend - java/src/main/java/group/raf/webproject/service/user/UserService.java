package group.raf.webproject.service.user;

import com.auth0.jwt.interfaces.DecodedJWT;
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

    Boolean userExists(DecodedJWT jwt);

    Boolean isAdmin(DecodedJWT jwt);

    Boolean isEditor(DecodedJWT jwt);
}
