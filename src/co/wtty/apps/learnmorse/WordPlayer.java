package co.wtty.apps.learnmorse;

import android.content.Context;

public class WordPlayer {
	private static WordPlayer _wordPlayer;
	
	private LetterPlayer _letterPlayer;
	
	public static WordPlayer getInstance(Context context) {
		if(_wordPlayer == null) {
			_wordPlayer = new WordPlayer(context);
		}
		
		return _wordPlayer;
	}
	
	protected WordPlayer(Context context) {
		// Constructor
		_letterPlayer = LetterPlayer.getInstance(context);
	}
	
	void play(String word) {
		word = word.toUpperCase();
		for(int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			if(letter == ' ') {
				try {
					Thread.sleep(500);
				} catch(Exception e) {
					// Probably should handle this nicely.
				}
			} else {
				_letterPlayer.play(String.valueOf(letter));
			}
			
		}
	}
	
	
}
