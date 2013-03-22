package co.wtty.apps.learnmorse;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LetterStageFragment extends Fragment {
	private LetterStageListener _listener;
	
	public interface LetterStageListener {
		public void onNext();
		public void onShow();
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.i("STAGE TRACE", "CREATING");
		LinearLayout view = (LinearLayout) inflater.inflate(R.layout.letter_stage, container, false);
		
		Log.i("STAGE TRACE", "SETTING UP SHOW LETTER BUTTON");
		((Button) view.findViewById(R.id.show_letter_button)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((TextView) getActivity().findViewById(R.id.letter_textview)).setVisibility(View.VISIBLE);
				
				_listener.onShow();
			}
		});
		
		Log.i("STAGE TRACE", "SETTING UP PLAY BUTTON");
		((Button) view.findViewById(R.id.play_tone_button)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LetterPlayer _player = LetterPlayer.getInstance(getActivity().getApplicationContext());
				_player.play(Alphabet.getInstance().currentLetter());
				
			}
		});
		
		Log.i("STAGE TRACE", "SETTING UP NEXT BUTTON");
		((Button) view.findViewById(R.id.next_letter_button)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Alphabet abcs = Alphabet.getInstance();
				abcs.next();
				refreshLetter();
				
				_listener.onNext();
			}
		});
		
		return view;
	}
	
	void refreshLetter() {
		Log.i("STAGE TRACE", "REFRESHING LETTER");
		TextView _letter = (TextView) getActivity().findViewById(R.id.letter_textview);
		TextView _tones = (TextView) getActivity().findViewById(R.id.dit_dash_textview);
		
		Alphabet abcs = Alphabet.getInstance();
		_letter.setVisibility(View.INVISIBLE);
		Log.i("STAGE TRACE", "CURRENT LETTER: "+abcs.currentLetter());
		_letter.setText(abcs.currentLetter());
		_tones.setText(abcs.currentTones());
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i("STAGE TRACE", "ATTACHING");
		try {
			_listener = (LetterStageListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement LetterStageListener");
		}
	}
	
}
