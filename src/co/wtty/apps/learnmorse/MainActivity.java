package co.wtty.apps.learnmorse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button _button = (Button) findViewById(R.id.play_tone_button);
		_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LetterPlayer _player = LetterPlayer.getInstance(getApplicationContext());
				_player.play("S");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	

}
