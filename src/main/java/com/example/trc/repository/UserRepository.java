package com.example.trc.repository;

import com.example.trc.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * @author Surkov Aleksey (stibium128@gmail.com)
 * @date 07.10.2020 13:34
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
