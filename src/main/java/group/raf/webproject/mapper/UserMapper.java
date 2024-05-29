package group.raf.webproject.mapper;

import group.raf.webproject.database.model.User;
import group.raf.webproject.dto.user.UserRequestDTO;
import group.raf.webproject.dto.user.UserResponseDTO;

public class UserMapper {

    public User UserRequestDtoToUser(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getFirstName());
        user.setSurname(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setActive(true);

        //User.setRole(roleRepository.findByName("User").orElse(null));
        return user;
    }

    public UserResponseDTO UserToUserResponseDto(User User) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(User.getId());
        dto.setFirstName(User.getName());
        dto.setLastName(User.getSurname());
        dto.setEmail(User.getEmail());
        dto.setActive(true);
        return dto;
    }
}
