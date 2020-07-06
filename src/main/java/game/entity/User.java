package game.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;

/**
 * The User entity class
 * @autor Scherbakov Pavel
 * @version 2.1
 */

@Entity
@Table(name = "user_table")
public class User {
    @Id
    private String name;
    private String password;
    private double rating;
    private int numberOfGames;
    @Transient
    private String number;
    @Transient
    private ArrayList<String> Attempts;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.rating = 0;
        this.numberOfGames=0;
        this.number= "0000";
        this.Attempts=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public ArrayList<String> getAttempts() {
        return Attempts;
    }

    public void setAttempts(ArrayList<String> attempts) {
        Attempts = attempts;
    }
}
