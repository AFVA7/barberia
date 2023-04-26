package com.barberback.repository;

import com.barberback.model._User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface _UserRepository extends JpaRepository<_User,Long> {
    _User findUserByUsername(String username);
}
