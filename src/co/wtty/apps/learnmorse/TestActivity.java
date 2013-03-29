package co.wtty.apps.learnmorse;

import co.wtty.apps.learnmorse.LetterStageFragment.LetterStageListener;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends Activity implements LetterStageListener {
	Context _context;
	Boolean _cheated = false;
	
	int _goodCount = 0;
	int _badCount = 0;
	int _cheatCount = 0;
	int _attemptCount = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		_context = this;
		Log.i("TRACE", "LOADING TEST ACTIVITY");
		
		Alphabet.getInstance().next();
		((LetterStageFragment) getFragmentManager().findFragmentById(R.id.letter_stage)).refreshLetter();
		
		Spinner _letter_list = (Spinner) findViewById(R.id.choice_list);
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Alphabet.getInstance().letters);
		_letter_list.setAdapter(spinnerArrayAdapter);
		Log.i("TRACE", "POPULATED SPINNER");
		
		((Button) findViewById(R.id.choice_select_button)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Spinner _letter_list = (Spinner) findViewById(R.id.choice_list);
				Alphabet abcs = Alphabet.getInstance();
				String guessed_letter = abcs.letters[(int)_letter_list.getSelectedItemId()];
				Log.i("TRACE", "GUESSED LETTER IS: "+guessed_letter);
				updateAttempts();
				if(abcs.currentLetter().equals(guessed_letter)) {
					// correct answer!
					if(!_cheated) {
						Toast.makeText(_context, "Good answer, that's right!", Toast.LENGTH_LONG).show();
						updateCorrect();
					}
			        

				} else {
					// that's cute... BUT ITS WRONG!
					if(!_cheated) {
						Toast.makeText(_context, "Incorrect, try again.", Toast.LENGTH_LONG).show();
						updateWrong();
					}
				}
			}
		});
		
	}
	
	public void updateAttempts() {
		_attemptCount++;
		((TextView) findViewById(R.id.attempt_count)).setText(String.valueOf(_attemptCount));
	}
	
	public void updateWrong() {
		_badCount++;
		((TextView) findViewById(R.id.wrong_count)).setText(String.valueOf(_badCount));
	}
	
	public void updateCorrect() {
		_goodCount++;
		((TextView) findViewById(R.id.correct_count)).setText(String.valueOf(_goodCount));
	}
	
	public void updateCheated() {
		_cheatCount++;
		((TextView) findViewById(R.id.cheat_count)).setText(String.valueOf(_cheatCount));
	}

	@Override
	public void onNext() {
		// TODO Auto-generated method stub
		_cheated = false;
	}

	@Override
	public void onShow() {
		// TODO Auto-generated method stub
		if(_cheated == false) {
			updateCheated();
		}
		_cheated = true;
	}
}
