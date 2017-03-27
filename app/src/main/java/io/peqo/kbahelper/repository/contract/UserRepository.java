package io.peqo.kbahelper.repository.contract;

import java.util.List;

import io.peqo.kbahelper.model.User;

public interface UserRepository {

    List<User> fetchAll();
    User fetchObject(Long id);

}
