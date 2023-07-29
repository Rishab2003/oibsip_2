package com.numberguess.numberguess.Service;

import java.util.Random;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Service
public class Game {
    Integer secretNumber;
    Integer guessNumber;
    Integer attempts;

    public Game(){
        Random rand=new Random();
        this.secretNumber= rand.nextInt(100);
        this.attempts=5;
        this.guessNumber=-1;
    }

    public String guess(){

        if(this.attempts<=0){
            return "Ran out";
        }
        else if(this.guessNumber==secretNumber){
                return "You won";
        }
        else if(this.guessNumber<secretNumber){
            this.attempts--;
            if(this.attempts==0)
            return "Ran out";
            else
            return "The secret number is higher than "+guessNumber;
        }
        else{
            this.attempts--;
            if(this.attempts==0)
            return "Ran out";
            else
             return "The secret number is lower than "+guessNumber;
        }

    }

}

