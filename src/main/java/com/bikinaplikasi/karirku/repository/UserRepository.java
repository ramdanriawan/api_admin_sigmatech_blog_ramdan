package com.bikinaplikasi.karirku.repository;

import com.bikinaplikasi.karirku.entity.User.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Cacheable("userFindAll")
    List<User> findAll();

    User findUserByEmail(String email);
}
