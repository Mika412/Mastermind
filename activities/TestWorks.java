package activities;

import strategy.Answer;
import strategy.Code;
import strategy.IStrategy;
import strategy.KnuthStrategy;

/**
 * Created by mykha on 15/04/2017.
 */
public class TestWorks {

    static IStrategy STRATEGY;

    static Code secretCode;

    public static void run(int size, int tries){
        for (int i = 0; i < tries; i++) {
            test(size);
        }
    }

    public static void test(int size) {
        STRATEGY = new KnuthStrategy(size);
        Code guess = STRATEGY.reset();
        secretCode = Code.createRandom(size);

        boolean guessed = false;
        int i = 0;
        while (!guessed && i < 20) {
            Answer answer = guess.compare(secretCode);
            System.out.println(answer.toString());
            if (answer.blacks == size)
                guessed = true;
            else {
                guess = STRATEGY.guess(answer);
                i++;
            }
        }
        System.out.println("Correct guess is " + guess.toString() + " which was guessed in " + i + " tries");
    }
}
