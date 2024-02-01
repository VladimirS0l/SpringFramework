package ru.solarev.lesson3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.solarev.lesson3.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
