package com.example.backend.User.Controller;


import com.example.backend.DailyBudget.Model.DailyBudget;
import com.example.backend.User.Model.User;
import com.example.backend.User.Repository.UserRepository;
import com.example.backend.User.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
public class UserController {





    private final UserService service;

    public UserController(UserService service){this.service = service;}

    @GetMapping
    public Iterable<User> getAllUsers(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> find(@PathVariable("id") Long id){
        Optional<User> user = Optional.of(service.find(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found.".formatted(id))));
        return ResponseEntity.ok().body(user.get());
    }
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User item = service.create(user);
        return ResponseEntity.ok().body(item);
    }

    @PostMapping("/userChecker")
    public ResponseEntity<User> session(@RequestBody User user){
        User userToReturn = service.session(user.getName(), user.getPassword());
        return ResponseEntity.ok().body(userToReturn);
    }
}
