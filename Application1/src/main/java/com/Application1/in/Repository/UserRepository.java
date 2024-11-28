package com.Application1.in.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Application1.in.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

