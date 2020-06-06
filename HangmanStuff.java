import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class HangmanStuff extends ConsoleProgram {
	private RandomGenerator rgen = new RandomGenerator();

	public void run() {
		boolean playAgain = true;
		while (playAgain) {
			playAgain = false;
			String randomWord = genRandomWord();
			int wordLength = randomWord.length();

			int guessesLeft = 8;
			int charactersLeft = randomWord.length();
			int x = 0;

			String hiddenWord = generateDashes(wordLength);
			printWelcomeMessages(guessesLeft, hiddenWord);

			playGame(randomWord, wordLength, guessesLeft, charactersLeft, hiddenWord, playAgain);
			playAgain = playAgain(x);
		}
	}

	public void playGame(String randomWord, int wordLength, int guessesLeft, int charactersLeft, String hiddenWord,
			boolean playAgain) {
		while (guessesLeft > 0 && charactersLeft > 0) {
			int correctLetters = 0;
			String guess = getGuess();
			correctLetters = checkGuess(wordLength, randomWord, correctLetters, guess, hiddenWord);
			charactersLeft = checkCorrectCharacters(wordLength, randomWord, charactersLeft, guess, hiddenWord);
			hiddenWord = changeHiddenWord(wordLength, randomWord, guess, hiddenWord);
			guessesLeft = messagesAfterGuess(correctLetters, guessesLeft, randomWord, charactersLeft, guess, hiddenWord,
					playAgain);

		}
	}

	public String genRandomWord() {
		int randomX = rgen.nextInt(0, 10);
		if (randomX == 0) {
			return "BUOY";
		} else if (randomX == 1) {
			return "COMPUTER";
		} else if (randomX == 2) {
			return "CONNOISSEUR";
		} else if (randomX == 3) {
			return "DEHYDRATE";
		} else if (randomX == 4) {
			return "FUZZY";
		} else if (randomX == 5) {
			return "HUBBUB";
		} else if (randomX == 6) {
			return "KEYHOLE";
		} else if (randomX == 7) {
			return "QUAGMIRE";
		} else if (randomX == 8) {
			return "SLITHER";
		} else if (randomX == 9) {
			return "ZIRCON";
		} else {
			return "DROSOPHILA";
		}
	}

	public String generateDashes(int wordLength) {
		String genDashes = "";
		for (int i = 0; i < wordLength; i++) {
			genDashes = genDashes + "-";
		}
		return genDashes;
	}

	public void printWelcomeMessages(int guessesLeft, String hiddenWord) {

		println("Welcome to Hangman!");
		println("Your word looks like: " + hiddenWord);
		println("You have " + guessesLeft + " guesses left.");
	}

	public String getGuess() {

		String guess2 = readLine("What do you want to guess?: ");
		guess2 = guess2.toUpperCase();
		return guess2;
	}

	public int checkGuess(int wordLength, String randomWord, int correctLetters, String guess, String hiddenWord) {

		for (int i = 0; i < wordLength; i++) {
			if (hiddenWord.charAt(i) == '-') {
				for (int j = i; j < i + 1; j++) {
					if (guess.length() == wordLength) {
						if (guess.charAt(j) == randomWord.charAt(j)) {
							correctLetters++;
						}
					} else {
						if (guess.charAt(0) == randomWord.charAt(j)) {
							correctLetters++;
						}
					}

				}
			}
		}
		return correctLetters;
	}

	public int checkCorrectCharacters(int wordLength, String randomWord, int charactersLeft, String guess,
			String hiddenWord) {

		for (int i = 0; i < wordLength; i++) {
			if (hiddenWord.charAt(i) == '-') {
				for (int j = i; j < i + 1; j++) {
					if (guess.length() == wordLength) {
						if (guess.charAt(j) == randomWord.charAt(j)) {
							charactersLeft--;
						}
					} else {
						if (guess.charAt(0) == randomWord.charAt(j)) {
							charactersLeft--;
						}
					}
				}
			}
		}
		return charactersLeft;
	}

	public String changeHiddenWord(int wordLength, String randomWord, String guess, String hiddenWord) {

		for (int i = 0; i < wordLength; i++) {
			if (hiddenWord.charAt(i) == '-') {
				for (int j = i; j < i + 1; j++) {
					if (guess.length() == wordLength) {
						if (guess.charAt(j) == randomWord.charAt(j)) {
							hiddenWord = hiddenWord.substring(0, i)
									+ (guess.charAt(j) + hiddenWord.substring(i + 1, wordLength));
						}
					} else {
						if (guess.charAt(0) == randomWord.charAt(j)) {
							hiddenWord = hiddenWord.substring(0, i)
									+ (guess.charAt(0) + hiddenWord.substring(i + 1, wordLength));
						}
					}
				}
			}
		}
		return hiddenWord;
	}

	public int messagesAfterGuess(int correctLetters, int guessesLeft, String randomWord, int charactersLeft,
			String guess, String hiddenWord, boolean playAgain) {
		if (correctLetters == 0) {
			guessesLeft--;
			println();
			println("There are no " + guess + "'s in the word. :(");
			println("Your word still looks like this: " + hiddenWord);
			println("You have " + guessesLeft + " guesses left.");
			if (guessesLeft == 0) {
				println("Oh no! You are completely hung. Yikes. The word was " + randomWord);
			}

		} else if (correctLetters != 0 && charactersLeft != 0) {
			println();
			println("Your guess is correct! Congrats! :)");
			println("Your word now looks like this: " + hiddenWord);
			println("You have " + guessesLeft + " guesses left.");

		} else if (charactersLeft == 0) {
			println();
			println("You got " + randomWord + "!");
			println("Great Job! You guessed the entire word! You are a genius!");
		}

		return guessesLeft;
	}

	public boolean playAgain(int x) {

		x = readInt("Type 1 to play again or 2 to stop! ");
		if (x == 1) {
			println();
			return true;
		} else {
			print("OK. Have a good day!");
			return false;
		}
	}
}
