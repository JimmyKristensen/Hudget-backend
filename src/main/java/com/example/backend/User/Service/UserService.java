package com.example.backend.User.Service;

import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.MonthlyBudget.Model.MonthlyBudget;
import com.example.backend.User.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    private final CrudRepository<User, Long> repository;

    //constructor
    public UserService(CrudRepository<User, Long> repository) {this.repository = repository; }

    public Iterable<User> findAll(){return repository.findAll();}

    public Optional<User> find(Long id){
        return repository.findById(id);
    }

    public User create(User user) {return repository.save(user);}

    public User session(String username, String password){
        Iterable<User> listToSearchIn = findAll();
        for (User user : listToSearchIn){
            if (user.getName().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }

        return null;
    }
}
