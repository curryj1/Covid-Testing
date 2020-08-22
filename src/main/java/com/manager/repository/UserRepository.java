package com.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.manager.model.users.Users;

@Repository
@CrossOrigin("http://localhost:4200")
public interface UserRepository extends JpaRepository<Users, String> {

}
