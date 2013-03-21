package co.wtty.apps.learnmorse;

import java.util.HashMap;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class LetterPlayer {
	
	private static LetterPlayer _letterPlayer;
	
	private TonePlayer _tonePlayer;
	private Alphabet _alphabet;
	
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
		_alphabet = Alphabet.getInstance();
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
		playPattern(_alphabet.getTones(letter));
	}
}
