package com.Application1.in.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Application1.in.EntityVO.UserVO;

public interface UserRepository extends JpaRepository<UserVO, Long> {
}

