package com.example.backend.User.Model;

import com.example.backend.Meal.Model.Meal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "logins")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    private String name;
    private String password;

    @OneToMany //one user has a number of meals
    private Set<Meal> userMeal;

    public User(String name, String password, Set<Meal> meals) {
        this.name = name;
        this.password = password;
        this.userMeal = meals;
    }
    public User(String name, String password) { //0712 2022 denne konstru bruges kun af CmdLineRunnerens test-cases
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
