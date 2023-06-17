package personal.model;

import java.util.List;

public interface Repository {
    List<User> getAllUsers();
    String CreateUser(User user);
    User updateUser(User user);
    // Создали интерфейс по обновлению и передали в него класс и пользователя
    User deleteUser(User user);
}
