package group.raf.webproject.repository.role;

import group.raf.webproject.database.model.Role;

import java.util.List;

public interface RoleRepository {

    List<Role> allRoles();
    Role updateRole(Role role);

}
