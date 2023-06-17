package personal.views;

import personal.controllers.UserController;
import personal.model.User;
import personal.views.validator.NameAndSurnameValidator;

import java.util.List;
import java.util.Scanner;

public class ViewUser {


    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE;

        while (true) {
            String command = prompt("Введите команду: ");
            try {
                com = Commands.valueOf(command.toUpperCase());
                // С помощью toUpperCase можно вводить любым шрифтом команду
                if (com == Commands.EXIT) return;
                switch (com) {
                    case CREATE:
                        createUser();
                        break;
                    case READ:
                        readUser();
                        break;
                    case LIST:
                        readList();
                        break;
                    case UPDATE:
                        updateUser();
                        break;
                    case DELETE:
                        deleteUser();
                        break;
                }
            } catch (Exception e) {
                // Использует try catch, чтобы поймать ошибку и вывести ее пользователю
                System.out.println(e.getMessage());
            }
        }
    }

    private void deleteUser() throws Exception {
        User user = getUser();
        userController.deleteUser(user);
    }

    private void updateUser() throws Exception {
        // Метод обновления данных. Для работы методу нужно дать данные пользователя которые хотим изменить.
        // Используем метод чтения списка и получение пользователя
        // После получения нужно сохранить. Для этого передаем контролеру, он сам сделает сохранение
        readList();
        User user = getUser();
        User updatedUser = getNewUser();
        updatedUser.setId(user.getId()); // подставили пользователю ID
        User saveUser = userController.updateUser(updatedUser); // обновление пользователя
        System.out.println(saveUser);

    }

    private void readList() {
        // Создаем метод на команду LIST
        List<User> listUser = userController.readAllUsers();
        // Передаем другой метод и получает лист пользователей
        System.out.println(listUser);
    }

    private void readUser() throws Exception {
        User user = getUser();
        System.out.println(user);
    }
    private User getUser() throws Exception {
        // Чтобы не дублировать код, сделали еще один метод getUser,
        // который будет использоваться для двух других методов
        // Используем userController
        String id = prompt("Идентификатор пользователя: ");
        User user = userController.readUser(id);
        return user;
    }

    private void createUser() throws Exception {
        User user = getNewUser();
        userController.saveUser(user);
    }
    // Сделали метод получение нового пользователя и добавили в createUser и updateUser
    // Это делается для того чтобы не дублировать код, а сделать один метод и использовать его в других методах
    private User getNewUser() throws Exception {
        String firstName = prompt("Имя: ");
        // Вызываем проверку на валидацию
        new NameAndSurnameValidator(firstName).validate();
        //  В метод отправляем firstName и вызываем метод
        String lastName = prompt("Фамилия: ");
        new NameAndSurnameValidator(lastName).validate();
        String phone = prompt("Номер телефона: ");
        User user = new User(firstName, lastName, phone);
        return user;
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
