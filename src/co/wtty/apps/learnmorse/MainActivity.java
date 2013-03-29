package co.wtty.apps.learnmorse;

import co.wtty.apps.learnmorse.LetterStageFragment.LetterStageListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements LetterStageListener {
	
	String _currentLetter = "A";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("TRACE", "CREATING MAIN");
		
		Spinner _letter_list = (Spinner) findViewById(R.id.letter_list);
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Alphabet.getInstance().letters);
		_letter_list.setAdapter(spinnerArrayAdapter);
		Log.i("TRACE", "POPULATED SPINNER");
		
		((LetterStageFragment) getFragmentManager().findFragmentById(R.id.letter_stage)).refreshLetter();

		((Button) findViewById(R.id.change_letter_button)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Spinner _letter_list = (Spinner) findViewById(R.id.letter_list);
				Alphabet abcs = Alphabet.getInstance();
				_currentLetter = abcs.letters[(int)_letter_list.getSelectedItemId()];
				Log.i("TRACE", "SETTING LETTER TO "+_currentLetter);
				abcs.setCurrentLetter(_currentLetter);
				((LetterStageFragment) getFragmentManager().findFragmentById(R.id.letter_stage)).refreshLetter();
			}
		});
		

		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i("TRACE", "SELECTED SOMETHING");
		Log.i("TRACE", String.valueOf(item.getItemId()));
		switch (item.getItemId()) {
	        case R.id.menu_letter_test:
	        	Log.i("TRACE", "GOING TO TEST ACTIVITY");
	        	Intent intent = new Intent(this, TestActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            Log.i("TRACE", "STARTING ACTIVITY");
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onNext() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShow() {
		// TODO Auto-generated method stub
		
	}
	
	

}
