package com.numberguess.numberguess.Controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.numberguess.numberguess.Service.Game;

import jakarta.servlet.http.HttpSession;

@Controller
public class mainController {
    
    @Autowired
    private Game game;

    @RequestMapping("/")
    public String home(Model model,HttpSession session){
        
        game=new Game();

        //session.setAttribute("game", game);
        System.out.println(game.getSecretNumber());
        return "home";

    }
    @RequestMapping("/start")
    public String start(Model model,HttpSession session){
        
        Game game=new Game();

        session.setAttribute("game", game);

        return "game";
    }



    @RequestMapping("/guess")
    public String game(@ModelAttribute("guessNumber") Integer guessNumber, HttpSession session,Model model){

        Game g=(Game)session.getAttribute("game");
        g.setGuessNumber(guessNumber);
        String ar=g.guess();

        if(ar=="You won"){
            String won[] =   {"Hooray !! you have found the Secret number "+g.getSecretNumber(),
                            "An Absolute Masterclass. The secret number is "+g.getSecretNumber(),
                            "You won!!! The secret number was "+g.getSecretNumber()+". Wanna Play again??"};
            Random random=new Random();
            model.addAttribute("score", won[random.nextInt(won.length)]);
            return "score";
        }
        if(ar=="Ran out"){

            String lost[]=  { "You ran out of chances.    The secret number is "+g.getSecretNumber(),
                            "Game Over!! Wanna try again",};

            model.addAttribute("score", "You ran out of chances.    The secret number is "+g.getSecretNumber());
            return "score";
        }
        System.out.println("Guesss"+g.getGuessNumber());
        System.out.println(g.getSecretNumber()+"secret");
        System.out.println(g.getAttempts()+"attempts");
        
        model.addAttribute("game",g);
        model.addAttribute("msg",ar);

        return "game";
    }

    @RequestMapping("/score")
    public String score(Model model){

        return "";
    }
}
