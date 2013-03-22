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
import android.widget.Toast;

public class TestActivity extends Activity implements LetterStageListener {
	Context _context;
	Boolean _cheated = false;
	
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
				if(abcs.currentLetter().equals(guessed_letter)) {
					// correct answer!
					if(_cheated) {
						Toast.makeText(_context, "Shutup, no one likes cheaters!", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(_context, "Good answer, that's right!", Toast.LENGTH_SHORT).show();
					}
			        

				} else {
					// that's cute... BUT ITS WRONG!
					if(_cheated) {
						Toast.makeText(_context, "Cheated and still got it wrong!", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(_context, "That's cute... BUT ITS WRONG!", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
	}

	@Override
	public void onNext() {
		// TODO Auto-generated method stub
		_cheated = false;
	}

	@Override
	public void onShow() {
		// TODO Auto-generated method stub
		_cheated = true;
	}
}
