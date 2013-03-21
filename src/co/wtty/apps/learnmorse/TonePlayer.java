package co.wtty.apps.learnmorse;

import android.content.Context;

class TonePlayer {
	private static TonePlayer _tonePlayer;
	
	private SoundManager _soundManager;
	
	private int _dit;
	private int _dah;
	private static int _pause = 100;
	
	public static TonePlayer getInstance(Context context) {
		if(_tonePlayer == null) {
			_tonePlayer = new TonePlayer(context);
		}
		
		return _tonePlayer;
	}
	
	protected TonePlayer(Context context) {
		// Constructor
		_soundManager = new SoundManager(context);
		setDefaults();
		load_tones();
	}
	
	private void setDefaults() {
		_soundManager.setVolume(1.0f);
		_soundManager.setBalance(1.0f);
		_soundManager.setSpeed(1.0f);
	}
	
	private void load_tones() {
		_dit = _soundManager.load(R.raw.dit);
		_dah = _soundManager.load(R.raw.dah);
	}
	
	void dit() {
		_soundManager.play(_dit);
		try {
			Thread.sleep(100 + _pause);
		} catch(Exception e) {
			// Probably should handle this nicely.
		}
	}
	
	void dah() {
		_soundManager.play(_dah);
		try {
			Thread.sleep(350 + _pause);
		} catch(Exception e) {
			// Probably should handle this nicely.
		}
	}
}
