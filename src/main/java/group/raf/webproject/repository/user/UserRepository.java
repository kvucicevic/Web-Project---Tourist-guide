package group.raf.webproject.repository.user;

import group.raf.webproject.database.model.User;

import java.util.List;

public interface UserRepository {

    User addUser(User user);
    List<User> allUsers();
    User findUserById(Integer id);
    User findUserByName(String name);
    User updateUser(User user);
    User setActive(Integer id, boolean active);
    User deleteUserById(Integer id);

}
