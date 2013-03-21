package co.wtty.apps.learnmorse;

import java.util.HashMap;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class LetterPlayer {
	
	private static LetterPlayer _letterPlayer;
	
	String[] alphabet = {
		"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", 
		"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
	};
	String[] morse_alphabet = {
		".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", 
		"-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."	
	};
	
	HashMap<String, String> _morse_map = new HashMap<String, String>();
	
	private TonePlayer _tonePlayer;
	private int _pause = 100;
	
	public static LetterPlayer getInstance(Context context) {
		if(_letterPlayer == null) {
			_letterPlayer = new LetterPlayer(context);
		}
		
		return _letterPlayer;
	}
	
	protected LetterPlayer(Context context) {
		// Constructor
		_tonePlayer = TonePlayer.getInstance(context);
		buildMap();
	}
	
	private void buildMap() {
		for(int i = 0; i < alphabet.length; i++) {
			_morse_map.put(alphabet[i], morse_alphabet[i]);
		}
	}
	
	void playPattern(String pattern) {
		for(int i = 0; i < pattern.length(); i++) {
			switch (pattern.charAt(i)) {
			case '-':
				Log.i("LETTER PLAYER", "-");
				_tonePlayer.dah();
				break;
			case '.':
				Log.i("LETTER PLAYER", ".");
				_tonePlayer.dit();
				break;
			default:
				Log.i("LETTER PLAYER", "X");
				break;
			}
		}
		
		try {
			// pause between letters
			Thread.sleep(_pause);
		} catch(Exception e) {
			// Probably should handle this nicely.
		}
	}
	
	void play(String letter) {
		playPattern(_morse_map.get(letter));
	}
}
