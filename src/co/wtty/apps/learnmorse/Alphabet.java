package co.wtty.apps.learnmorse;

import java.util.HashMap;
import java.util.Random;

public class Alphabet {
	private static Alphabet _alphabet;
	
	String[] letters = {
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
		"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
	};
	String[] tones = {
		".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", 
		"-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."	
	};
	
	HashMap<String, String> _letters_to_tones_map = new HashMap<String, String>();
	HashMap<String, String> _tones_to_letters_map = new HashMap<String, String>();
	
	private String _currentLetter = "";
	Random _rng = new Random();
	
	public static Alphabet getInstance() {
		if(_alphabet == null) {
			_alphabet = new Alphabet();
		}
		
		return _alphabet;
	}
	
	protected Alphabet() {
		// Constructor
		buildMap();
		_currentLetter = randomLetter();
	}
	
	private void buildMap() {
		for(int i = 0; i < letters.length; i++) {
			_letters_to_tones_map.put(letters[i], tones[i]);
			_tones_to_letters_map.put(tones[i], letters[i]);
		}
	}
	
	String currentLetter() {
		return _currentLetter;
	}
	
	String currentTones() {
		return getTones(_currentLetter);
	}
	
	void setCurrentLetter(String letter) {
		_currentLetter = letter;
	}
	
	String next() {
		_currentLetter = randomLetter();
		return _currentLetter;
	}
	
	String getTones(String letter) {
		return _letters_to_tones_map.get(letter);
	}
	
	String getLetter(String tones) {
		return _tones_to_letters_map.get(tones);
	}
	
	String randomLetter() {
		return letters[_rng.nextInt(26)];
	}
	
	String randomTones() {
		return tones[_rng.nextInt(26)];
	}
	
	
}
