package group.raf.webproject.mapper;

import group.raf.webproject.database.model.Role;
import group.raf.webproject.dto.role.RoleRequestDTO;
import group.raf.webproject.dto.role.RoleResponseDTO;

public class RoleMapper {

    public Role roleRequestDTOToRole(RoleRequestDTO roleRequestDTO) {
        Role role = new Role();
        role.setName(roleRequestDTO.getRoleName());
        return role;
    }

    public RoleResponseDTO roleToRoleResponseDTO(Role role) {
        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
        roleResponseDTO.setId(role.getId());
        roleResponseDTO.setName(role.getName());
        return roleResponseDTO;
    }
}
