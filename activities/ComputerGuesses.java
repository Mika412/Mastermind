package activities;

import objects.ItemChooseAnswer;
import objects.ItemGuessed;
import processing.core.PApplet;
import strategy.*;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mykha on 14/04/2017.
 */
public class ComputerGuesses extends PApplet{

    List<ItemGuessed> guessedList = new ArrayList<>();
    ItemChooseAnswer chooseItem;
    static private int numberBalls;
    static IStrategy STRATEGY;

    public static Code guess = null;

    public void settings(){
        size(Constants.windowWidth,Constants.windowHeight);
    }

    public void setup() {
        STRATEGY = new KnuthStrategy(numberBalls);
        guess(null);
        chooseItem = new ItemChooseAnswer(this, 0, Constants.windowHeight - Constants.hightChoice,
                Constants.windowWidth,
                Constants.hightChoice,
                numberBalls, guess);
    }

    public void draw() {
        background(100);

        chooseItem.display();
        chooseItem.checkMouse();
        checkSubmitted();

        for (int i = 0; i < guessedList.size(); i++) {
            guessedList.get(i).display();
        }
    }

    public void checkSubmitted(){
        Answer answer = chooseItem.checkSubmittedGuess();
        if(answer != null){
            System.out.println("Answer " + answer.blacks + " " + answer.whites);
            guessedList.add(new ItemGuessed(this,
                    0,
                    Constants.windowHeight - Constants.guessedRectangleHeight * (guessedList.size()+1) - Constants.hightChoice-Constants.hightGap,
                    Constants.windowWidth,
                    Constants.guessedRectangleHeight,
                    numberBalls,
                    guess,
                    answer));

            if(answer.blacks == numberBalls)
                guess(null);
            else
                guess(answer);

            chooseItem.changeCode(guess);
            checkExceedPositioning();
        }
    }

    void checkExceedPositioning(){
        if(guessedList.size() * Constants.guessedRectangleHeight > (Constants.windowHeight - Constants.hightChoice)) {
            guessedList.remove(0);
            for (int i = 0; i < guessedList.size(); i++) {
                ItemGuessed tempGuessed = guessedList.get(i);
                tempGuessed.updateYpos(Constants.windowHeight - Constants.guessedRectangleHeight * (i + 1) - Constants.hightChoice-Constants.hightGap);
                guessedList.set(i, tempGuessed);
            }
        }
    }



    public static void guess(Answer answer) {
        if(answer == null)
            guess = STRATEGY.reset();
        else {
            guess = STRATEGY.guess(answer);
        }
    }

    public static void run(int nBalls) {
        numberBalls = nBalls;
        PApplet.main("activities.ComputerGuesses");
    }
}
