package game.dao;

import game.entity.User;
import game.service.ComputerService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class provides access to the User entity
 * @autor Scherbakov Pavel
 * @version 2.1
 */

public class UserDao {

    private EntityManager em;

    public UserDao(EntityManager em) {
        this.em = em;
    }

    /**
     * @param user - new user
     * Makes this user manageable
     */
    public void save(User user){
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    /**
     * @return Returns users sorted by rating
     * who have played more than 0 games
     */
    public List<User> getUsersRating(){
        em.getTransaction().begin();
        List<User> results = em
                .createQuery("Select a from User a", User.class)
                .getResultList();
        em.getTransaction().commit();
        return results.stream()
                .sorted(Comparator.comparingDouble(User::getRating))
                .filter(user -> user.getNumberOfGames()!=0)
                .collect(Collectors.toList());
    }
    /**
     * @param name - user name
     * @param password - user password
     * @return is exist user with this name and password
     */
    public boolean userIsExist(String name, String password) {
        em.getTransaction().begin();
        User user = em.find(User.class, name);
        em.getTransaction().commit();
        return user != null && user.getPassword().equals(password);
    }
    /**
     * @param inputNumber - the number which user entered
     * @param name - user name
     * @return not is game over
     */
    public boolean addAttempt(String inputNumber,String name){
        User user = em.find(User.class, name);
        String compResponse = ComputerService.checkNumber(inputNumber,user.getNumber());
        user.getAttempts().add(inputNumber+" - "+compResponse);
        return !compResponse.equals("4B0K");
    }
    /**
     * @param name - user name
     * @return user attempts or null
     */
    public ArrayList<String> getAttempts(String name){
        User user = em.find(User.class, name);
        if(user==null){
            return null;
        }else{
            return user.getAttempts();
        }
    }
    /**
     * @param name - user name
     * @param inputNumber - the number which user entered
     * @return has the user entered this number before
     */
    public boolean isHaveAttempt(String name,String inputNumber){
        User user = em.find(User.class, name);
        ArrayList<String> attempts=user.getAttempts();
        for(String attempt:attempts){
            if(attempt.contains(inputNumber)){
                return true;
            }
        }
        return false;
    }

    /**
     * Restart game
     * @param name - user name
     */
    public void restart(String name){
        User user = em.find(User.class, name);
        user.setAttempts(new ArrayList<>());
        user.setNumber(ComputerService.generateNumber());
        System.out.println(user.getNumber());
    }
    /**
     * Calculates the user's new rating
     * when they have guessed a number
     * @param name - user name
     */
    public void calculateRating(String name){
        em.getTransaction().begin();
        User user = em.find(User.class, name);
        if(user.getNumberOfGames()==0){
            user.setRating(user.getAttempts().size());
            user.setNumberOfGames(1);
        }else{
            double Sn=user.getRating()*user.getNumberOfGames();
            int anplus1=user.getAttempts().size();
            int nplus1=user.getNumberOfGames()+1;
            user.setRating((Sn+anplus1)/nplus1);
            user.setNumberOfGames(nplus1);
        }
        em.getTransaction().commit();
    }
    /**
     * Get user by name
     * @param name - user name
     */
    public User getUserByName(String name){
        em.getTransaction().begin();
        User user = em.find(User.class, name);
        em.getTransaction().commit();
        return user;
    }
}
