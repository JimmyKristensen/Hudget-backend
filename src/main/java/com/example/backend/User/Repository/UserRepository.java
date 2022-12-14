package com.example.backend.User.Repository;

import com.example.backend.User.Model.User;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, Long> {

}
