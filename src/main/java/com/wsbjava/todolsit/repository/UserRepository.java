package com.wsbjava.todolsit.repository;

import com.wsbjava.todolsit.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);


}
