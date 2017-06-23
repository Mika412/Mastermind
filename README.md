Mastermind
==========
Mastermind game writen in Java and Processing for GUI.

#### Required additional library:
	processing.core
  
There are 3 modes for this program, computer guesses, user guesses and test algorithm.

## Test mode
<p align="center">
<img src="https://user-images.githubusercontent.com/5073663/27485764-f5f2eb1a-5825-11e7-9047-6ac80a382f02.png" />
</p>
To run in test mode, the program has three additional options from which the mode is required, [program name] test [number balls] [number tests]. 
		If the user doesn't specify the number of balls or tests, the program will use the default values.
	
		EXAMPLES: "mastermind test 4 3", "mastermind test 6", "mastermind test"
    
## Computer mode

<p align="center">
<img src="https://user-images.githubusercontent.com/5073663/27485800-1e353f9c-5826-11e7-815a-2093534ceb45.png"  width="500" />
</p>

Computer mode is when the computer tries to guess your number. It shows you a guess and you select black, white or grey dots, which is your answer. Blacks is that the color is correct and in the correct position, white correct color but incorrect position, grey incorrect color. When the answer is four balls, the computer will reset it’s guesses.
		To run in computer mode, the program has three additional options from which the mode is required, [program name] computer [number balls] 
		If the user doesn't specify the number of balls, the program will use the default values.

		EXAMPLES: "mastermind computer 6", "mastermind computer"

## User mode

<p align="center">
<img src="https://user-images.githubusercontent.com/5073663/27485803-1fb7864a-5826-11e7-9f78-3250f99c4215.png" width="500"/>
</p>

User mode is when the computer generates random colors, and user needs to guess. Below there are all possible colors and few empty grey slots, user has to click on color and paint the empty slot. When all empty slots are painted the button will light up blue. If you click on the blue button, the painted slots will be reset to grey and your guess will appear on top, with the computers black/white/grey answer on the right. The answer is follows the same rule as in computer mode. If your guess is correct, the computer will generate another random set of colours, and you can try guessing again.

		EXAMPLES: "mastermind user 6", "mastermind user"
