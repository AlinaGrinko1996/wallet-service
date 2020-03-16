package ua.nure.hrynko.walletservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ua.nure.hrynko.walletservice.enums.TransactionType;
import ua.nure.hrynko.walletservice.exceptions.TransactionException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private double balance;

    //gives smth strange when quering for if
    //bidirectional
    //performance - no difference, recommended to use
    //there is no sense to store transaction if user entity was deleted

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private Set<Transaction> transactions = new HashSet<>();

    Player() {}

    public Player(String firstName, String lastName, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    //atomicity - transaction wont be added in case if there is not enough money
    public void addTransaction(Transaction transaction) throws TransactionException {
        if (transaction.getTransactionType() == TransactionType.CREDIT) {
            this.balance += transaction.getAmount();
        } else if (this.balance >= transaction.getAmount()) {
            this.balance -= transaction.getAmount();
        } else {
            throw new TransactionException("You balance is not enough");
        }
        this.transactions.add(transaction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
