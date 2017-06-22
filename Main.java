import activities.ComputerGuesses;
import activities.TestWorks;
import activities.UserGuess;
import utils.Constants;


public class Main {

    public static void main(String[] args) {
        if(args[0].equals("user")){
            if(args.length > 1)
                UserGuess.run(Math.min(Integer.parseInt(args[1]), Constants.maxNumberBalls));
            else
                UserGuess.run(Constants.defaultNumberBalls);
        }else if(args[0].equals("computer")){
            if(args.length > 1)
                ComputerGuesses.run(Math.min(Integer.parseInt(args[1]), Constants.maxNumberBalls));
            else
                ComputerGuesses.run(Constants.defaultNumberBalls);
        }else if(args[0].equals("test")){
            if(args.length == 3)
                TestWorks.run(Math.min(Integer.parseInt(args[1]), Constants.maxNumberBalls), Integer.parseInt(args[2]));
            else if(args.length == 2)
                TestWorks.run(Math.min(Integer.parseInt(args[1]), Constants.maxNumberBalls), Constants.defaultNumberTests);
            else
                TestWorks.test(Constants.defaultNumberBalls);
        }else {
            throw new IllegalArgumentException("Invalid option.");
        }
    }
}
