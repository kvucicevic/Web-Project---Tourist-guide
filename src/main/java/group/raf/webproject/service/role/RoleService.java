package group.raf.webproject.service.role;

import group.raf.webproject.dto.role.RoleRequestDTO;
import group.raf.webproject.dto.role.RoleResponseDTO;

import java.util.List;

public interface RoleService {

    List<RoleResponseDTO> findAll();

    RoleResponseDTO update(Integer id, RoleRequestDTO roleRequestDTO);
    
}
