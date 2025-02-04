package com.example.webregister.repository;

import com.example.webregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByUserIdAsc();
    User findByMail(String mail);
    User findByUserId(Long userId);
    @Transactional
    void deleteByUserId(Long userId);

}