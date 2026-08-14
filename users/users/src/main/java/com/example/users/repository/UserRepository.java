package com.example.users.repository;

import java.util.List;
import java.util.Optional;

import com.example.users.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.users.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User> findByEmail(String email);

   Boolean existsByEmail(String email);
}
