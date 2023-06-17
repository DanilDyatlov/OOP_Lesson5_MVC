package personal.views.validator;

import personal.model.User;

public class NameAndSurnameValidator {
    // Методы на проверку правильности написания
    String userInput;
    public NameAndSurnameValidator(String userInput) {
        this.userInput = userInput;
    }

    public void validate() throws Exception {
        // Проверка на пробел, есть или нет
        // Метод через проверку на ошибку
        if(!userInput.matches("\\S+$")){
            throw new Exception("Валидация не прошла");

        }


    }
}
