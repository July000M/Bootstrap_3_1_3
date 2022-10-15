package ru.kata.spring.boot_security.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);
}

