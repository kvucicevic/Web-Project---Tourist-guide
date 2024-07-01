package group.raf.webproject.service.role;

import group.raf.webproject.database.model.Role;
import group.raf.webproject.dto.role.RoleResponseDTO;
import group.raf.webproject.dto.role.RoleRequestDTO;
import group.raf.webproject.mapper.RoleMapper;
import group.raf.webproject.repository.role.RoleRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private RoleMapper roleMapper;

    @Override
    public List<RoleResponseDTO> findAll() {
        List<RoleResponseDTO> RoleResponseDTOS = new ArrayList<>();
        for(Role Role : roleRepository.allRoles()){
            RoleResponseDTOS.add(roleMapper.roleToRoleResponseDTO(Role));
        }
        return RoleResponseDTOS;
    }

    @Override
    public RoleResponseDTO findByName(String name) {
        Role role = roleRepository.findRoleByName(name);
        return roleMapper.roleToRoleResponseDTO(role);
    }

}
