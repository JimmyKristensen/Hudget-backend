package com.example.backend.AdditionalExpenses.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class AdditionalExpenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long expenses_id;

    private String item;
    private int cost;


    public AdditionalExpenses(String item, int cost) {
        this.item = item;
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "AdditionalExpenses{" +
                "item='" + item + '\'' +
                ", cost=" + cost +
                '}';
    }
}
