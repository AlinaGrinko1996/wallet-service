package ua.nure.hrynko.walletservice.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Player {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private double balance;

    Player() {}

    public Player(String firstName, String lastName, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }
}
