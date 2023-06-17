package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {

    private final Repository repository;

    public UserController(Repository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        // Создает пользователя,но не заменяет его
        repository.CreateUser(user);
    }
    public User updateUser(User user){
        // Метод передает обновленного пользователя и мы его присваиваем userUpdate
        User userUpdate = repository.updateUser(user);
        return userUpdate;
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }
    public List<User> readAllUsers(){
        return repository.getAllUsers();
        // создали метод для чтения пользователей
    }
    public void deleteUser(User user){
        repository.deleteUser(user);
    }
}
