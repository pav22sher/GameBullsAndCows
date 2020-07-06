package game.service;

import java.util.Arrays;
import java.util.Collections;

/**
 * The class guesses and checks the number
 * @autor Scherbakov Pavel
 * @version 2.1
 */

public class ComputerService {
    private static final Integer[] numbers = {0,1,2,3,4,5,6,7,8,9};
    /**
     * @return returns the generated number
     */
    public static String generateNumber(){
        StringBuilder number=new StringBuilder();
        Collections.shuffle(Arrays.asList(numbers));
        number.append(numbers[0]);
        number.append(numbers[1]);
        number.append(numbers[2]);
        number.append(numbers[3]);
        return number.toString();
    }
    /**
     * @return how many numbers are correctly guessed
     * and how many guessed without numbers's position
     */
    public static String checkNumber(String userNumber,String number){
        int b=0;
        int k=0;
        for(int i=0;i<userNumber.length();i++){
            if(userNumber.charAt(i)==number.charAt(i)){
                b++;
            } else if(number.indexOf(userNumber.charAt(i))!=-1){
                k++;
            }
        }
        return b+"B"+k+"K";
    }
    /**
     * @param inputNumber - the number which user entered
     * @return is valid number
     */
    public static boolean isValidNumber(String inputNumber){
        if(inputNumber.length()!=4) return false;
        for(int i=0;i<4;i++){
            char cur=inputNumber.charAt(i);
            if(inputNumber.indexOf(cur) != inputNumber.lastIndexOf(cur)){
                return false;
            }
        }
        return true;
    }
}
