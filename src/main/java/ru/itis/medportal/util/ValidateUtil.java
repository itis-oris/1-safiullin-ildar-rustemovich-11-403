package ru.itis.medportal.util;


import ru.itis.medportal.exception.ValidationException;
import ru.itis.medportal.repository.AbstractUserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ValidateUtil {

    public static void validateEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9@._-]+$") || email.toLowerCase().contains("xn--")) {
            throw new ValidationException("Email может содержать только английские буквы, цифры и символы @ . _ -");
        }
    }

    public static void validateEmailExist(String email, AbstractUserRepository repository) {
        if (repository.checkEmail(email) || "admin@mail.ru".equals(email)) {
            throw new ValidationException("Пользователь с такой почтой уже существует");
        }
    }

    public static void validatePassword(String password) {
        if (!password.matches("^[a-zA-Z0-9]+$")) {
            throw new ValidationException("Пароль может содержать только английские буквы и цифры");
        }
    }

    public static void validateText(String text) {
        if (text == null) return;
        String[] dangerousPatterns = {
                "<script", "</script", "javascript:", "onload=", "onerror=",
                "onclick=", "eval(", "alert(", "document.cookie", "window.location",
                "<iframe", "<object", "<embed", "<form", "onmouse"
        };
        String lowerText = text.toLowerCase();
        for (String pattern : dangerousPatterns) {
            if (lowerText.contains(pattern)) {
                throw new ValidationException("Текст содержит запрещенные конструкции: " + pattern);
            }
        }
    }

    public static void validateBirthDate(LocalDate localDate) {
        if (!localDate.isBefore(LocalDate.now())) {
            throw new ValidationException("Некорректная дата рождения");
        }
    }

    public static void validateAppointmentDate(LocalDateTime localDate) {
        if (localDate.isBefore(LocalDateTime.now())) {
            throw new ValidationException("Некорректная дата приёма");
        }
    }

    public static void validateFile(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!"pdf".equals(extension)) throw new ValidationException("Некорректный формат файла");
    }

}
