import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.RandomGenerator;

public class HangmanDictionary {
	ArrayList<String> dictionary;
	private RandomGenerator rgen = new RandomGenerator();
	int[] letterCount;
	String computerGuesses;

	public HangmanDictionary() {
		dictionary = new ArrayList<String>();
	}

	// gets randomWord and counts the number characters of the words so that it can
	// guess them
	public String getRandomWord(String randomWord) {

		try {
			BufferedReader rd = new BufferedReader(new FileReader("1000WordDictionary.txt"));
			String line = rd.readLine();
			dictionary.add(line);
			letterCount = new int[26];
			line = rd.readLine();
			while (line != null) {
				for (int i = 0; i < line.length(); i++) {
					int diff = (line.substring(i, i + 1).compareTo("a"));
					letterCount[diff]++;
				}
				dictionary.add(line);
				line = rd.readLine();
			}

			int randomWordLine = rgen.nextInt(1, dictionary.size());
			randomWord = dictionary.get(randomWordLine);

			rd.close();

		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return randomWord;
	}

	// finds what letter appears the most, making sure that it hasn't already been
	// guessed
	public String genComputerGuess() {
		int largest = letterCount[25];
		int indexOfLargest = 16;
		for (int i = 0; i < 26; i++) {
			if (letterCount[i] > largest && computerGuesses.indexOf((char) ('a' + i)) == -1) {
				largest = letterCount[i];
				indexOfLargest = i;
			}
		}
		char guess = (char) ('a' + indexOfLargest);
		computerGuesses += (char) ('a' + indexOfLargest);
		String guess1 = "" + guess;

		return guess1;
	}

}
