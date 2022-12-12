package com.example.backend.User.Repository;

import com.example.backend.User.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {

}
