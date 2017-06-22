package activities;

import edu.princeton.cs.algs4.StdOut;
import pObjects.ItemChoose;
import pObjects.ItemGuessed;
import processing.core.PApplet;
import strategy.Answer;
import strategy.Code;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class UserGuess extends PApplet{

    List<ItemGuessed> guessedList = new ArrayList<>();
    ItemChoose chooseItem;
    Code generatedCode = null;
    static private int numberBalls;


    public void settings(){
        size(Constants.windowWidth,Constants.windowHeight);
    }

    public void setup() {
        chooseItem = new ItemChoose(this, 0, Constants.windowHeight - Constants.hightChoice,
                Constants.windowWidth,
                Constants.hightChoice,
                numberBalls);
        generateRandom();
        /*for(int i = 0; i < 8; i++) {
            guessedList.add(new ItemGuessed(this,
                    0,
                    Constants.windowHeight - Constants.guessedRectangleHeight * (guessedList.size() + 1) - Constants.hightChoice-Constants.hightGap,
                    Constants.windowWidth,
                    Constants.guessedRectangleHeight,
                    numberBalls,
                    new Code("123456"),
                    new Answer(3, 2)));
        }*/
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
        String sub = chooseItem.checkSubmittedGuess();
        if(!sub.isEmpty() && !sub.equals("") && sub.length() == numberBalls){
            Code guess = new Code(sub);
            Answer answer = guess.compare(generatedCode);
            guessedList.add(new ItemGuessed(this,
                    0,
                    Constants.windowHeight - Constants.guessedRectangleHeight * (guessedList.size()+1) - Constants.hightChoice-Constants.hightGap,
                    Constants.windowWidth,
                    Constants.guessedRectangleHeight,
                    numberBalls,
                    guess,
                    answer));
            checkExceedPositioning();
            if(answer.blacks == numberBalls)
                generateRandom();
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

    public void generateRandom(){
        generatedCode = Code.createRandom(numberBalls);
        StdOut.println(generatedCode);
    }

    public static void run(int nBalls) {
        numberBalls = nBalls;
        PApplet.main("activities.UserGuess");
    }
}
