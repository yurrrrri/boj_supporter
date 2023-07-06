package com.huh.BaekJoonSupporter.solvedApi.repository;

import com.huh.BaekJoonSupporter.solvedApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByHandle(String handle);

    Integer findBySolvedCount(User user);
}
