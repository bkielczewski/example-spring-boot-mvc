package eu.kielczewski.example.service;

import eu.kielczewski.example.domain.User;
import eu.kielczewski.example.domain.UserCreateForm;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getList();

}
