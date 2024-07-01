package group.raf.webproject.service.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import group.raf.webproject.database.model.Role;
import group.raf.webproject.database.model.User;
import group.raf.webproject.dto.token.TokenRequestDTO;
import group.raf.webproject.dto.token.TokenResponseDTO;
import group.raf.webproject.dto.user.UserRequestDTO;
import group.raf.webproject.dto.user.UserResponseDTO;
import group.raf.webproject.mapper.UserMapper;
import group.raf.webproject.repository.role.RoleRepository;
import group.raf.webproject.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

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
        user.setId(id);
        return userMapper.UserToUserResponseDto(userRepository.updateUser(user));
    }

    @Override
    public UserResponseDTO activate(Integer id, boolean activate) {
        return userMapper.UserToUserResponseDto(userRepository.setActive(id, activate));
    }

    @Override
    public TokenResponseDTO login(TokenRequestDTO tokenRequestDTO) {
        List<Object> result = this.userRepository.loginUser(tokenRequestDTO);
        if (result == null) return null;
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        String token = generateJWTToken((User) result.get(0), (String) result.get(1));
        tokenResponseDTO.setToken(token);
        return tokenResponseDTO;

    }

    @Override
    public UserResponseDTO delete(Integer id) {
        return userMapper.UserToUserResponseDto(userRepository.deleteUserById(id));
    }

    private String generateJWTToken(User user, String roleName) {
        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000);          // One day
        Algorithm algorithm = Algorithm.HMAC256("secret");

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withClaim("id", user.getId())
                .withClaim("role", roleName)
                .sign(algorithm);
    }

    public Boolean userExists(DecodedJWT jwt) {
        Integer id = jwt.getClaim("id").asInt();
        User user = userRepository.findUserById(id);
        return user != null;
    }

    public Boolean isAdmin(DecodedJWT jwt) {
        String roleName = jwt.getClaim("role").asString();
        Role role = roleRepository.findRoleByName(roleName);
        return role != null && role.getName().equalsIgnoreCase("ADMIN");
    }

    public Boolean isEditor(DecodedJWT jwt) {
        String roleName = jwt.getClaim("role").asString();
        Role role = roleRepository.findRoleByName(roleName);
        return role != null && role.getName().equalsIgnoreCase("EDITOR");
    }
}
