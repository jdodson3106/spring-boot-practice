package com.justindodson.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator{

    // == fields ==
    private final Game game;

    // == Constructors ==
    @Autowired
    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    // == Init Method ==
    @PostConstruct
    public void gameValue(){
        log.info("GAMEIMPL OBJECT: {}", game);
    }

    // == public methods ==
    @Override
    public String getMainMessage() {
        return "Number is between " +
                game.getSmallest() +
                " and " +
                game.getBiggest() +
                ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon()) {
            return "You guessed it! The number was " + game.getNumber();
        } else if(game.isGameLost()) {
            return "You Lost. The number was " + game.getNumber();
        } else if(!game.isValidNumberRange()) {
            return "Invalid Number Range!";
        } else if(game.getRemainingGuesses() == game.getGuessCount()){
            return "What is your first guess?";
        } else {
            String direction = "Lower";
            if(game.getGuess() < game.getNumber()){
                direction = "Higher";
            }
            return direction + "! You have " + game.getRemainingGuesses() + " guesses left.";
        }
    }
}
