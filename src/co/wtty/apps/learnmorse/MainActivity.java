package co.wtty.apps.learnmorse;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	String _currentLetter = "A";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button _button = (Button) findViewById(R.id.play_tone_button);
		_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LetterPlayer _player = LetterPlayer.getInstance(getApplicationContext());
				_player.play(_currentLetter);
				
			}
		});
		
		Spinner _letter_list = (Spinner) findViewById(R.id.letter_list);
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Alphabet.getInstance().letters);
		_letter_list.setAdapter(spinnerArrayAdapter);
		
		
		Button _change_letter_button = (Button) findViewById(R.id.change_letter_button);
		_change_letter_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Spinner _letter_list = (Spinner) findViewById(R.id.letter_list);
				_currentLetter = Alphabet.getInstance().letters[(int)_letter_list.getSelectedItemId()];
				updateLetterDisplay();
				showLetter();
			}
		});
		
		((Button) findViewById(R.id.show_letter_button)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showLetter();
				
			}
		});
		
		((Button) findViewById(R.id.next_letter_button)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				_currentLetter = Alphabet.getInstance().randomLetter();
				updateLetterDisplay();
				
			}
		});
	}
	
	void showLetter() {
		((TextView) findViewById(R.id.letter_textview)).setVisibility(View.VISIBLE);
	}
	
	void updateLetterDisplay() {
		TextView _letter = (TextView) findViewById(R.id.letter_textview);
		TextView _tones = (TextView) findViewById(R.id.dit_dash_textview);
		
		Alphabet abcs = Alphabet.getInstance();
		_letter.setVisibility(View.INVISIBLE);
		_letter.setText(_currentLetter);
		_tones.setText(abcs.getTones(_currentLetter));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	

}
