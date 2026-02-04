package ru.itis.medportal.service;

import jakarta.servlet.ServletException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itis.medportal.dto.UserDTO;
import ru.itis.medportal.model.User;
import ru.itis.medportal.repository.AbstractUserRepository;
import ru.itis.medportal.util.ValidateUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractUserService<T extends User,K extends UserDTO> {
    private AbstractUserRepository<T> repository;

    public AbstractUserService(AbstractUserRepository<T> repository) {
        this.repository = repository;
    }

    protected abstract K createUserDTO(T user);
    protected abstract void validateSpecificFields(T user);


    public List<K> getAll() {
        List<T> users = repository.findAll();
        List<K> dtos = new ArrayList<>();
        for (T user : users) {
            dtos.add(createUserDTO(user));
        }
        return dtos;
    }

    public K save(T user) {
        validateUser(user);
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setId(repository.save(user));
            return createUserDTO(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public K checkAccount(String email, String password) {
        ValidateUtil.validateEmail(email);
        ValidateUtil.validatePassword(password);
        T user = repository.checkByEmailAndPassword(email).orElse(null);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            K userDTO = createUserDTO(user);
            return userDTO;
        } else {
            return null;
        }
    }

    public K update(T user, K userDTO) throws ServletException, IOException {
        validateUser(user);
        try {
            user.setId(userDTO.getId());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.update(user);
            return createUserDTO(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void checkEmail(String email) {
        ValidateUtil.validateEmailExist(email,repository);
    }

    private void validateUser(T user) {
        ValidateUtil.validateText(user.getName());
        ValidateUtil.validateText(user.getSurname());
        ValidateUtil.validateEmail(user.getEmail());
        ValidateUtil.validatePassword(user.getPassword());
        validateSpecificFields(user);
    }

}
