package com.misionTIC2022_grupo11.security_backend.repositories;

import com.misionTIC2022_grupo11.security_backend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = " SELECT * FROM user WHERE email=? AND password=?;", nativeQuery = true)
    public Optional<User> login(String email, String password);
}
