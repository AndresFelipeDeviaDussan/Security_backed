package com.misionTIC2022_grupo11.security_backend.repositories;

import com.misionTIC2022_grupo11.security_backend.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
