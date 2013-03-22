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
	}
	
	private void buildMap() {
		for(int i = 0; i < letters.length; i++) {
			_letters_to_tones_map.put(letters[i], tones[i]);
			_tones_to_letters_map.put(tones[i], letters[i]);
		}
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
