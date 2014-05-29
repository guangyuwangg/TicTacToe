package com.my.tictactoe;

import java.util.Observable;
import java.util.Observer;

import com.my.tictactoe.R;

import android.content.Context;
import android.util.*;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.ToggleButton;

public class ReadyScreen extends LinearLayout implements Observer {
	
	private Model model;
	private Button startButton;
	private ToggleButton toggleButton;
	private EditText p1name;
	private EditText p2name;
	
	public ReadyScreen(Context context, Model m) {
		super(context);
		
	    Log.d("TicTacToe", "Ready screen constructor");
		
		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.readyscreen, this);
		
		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);
		
		p1name = (EditText) findViewById(R.id.p1);
		p1name.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				model.setPlayer1(p1name.getText().toString());
				return false;
			}
		});
		p2name = (EditText) findViewById(R.id.p2);
		p2name.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				model.setPlayer2(p2name.getText().toString());
				return false;
			}
		});

		// get a reference to widgets to manipulate on update
		startButton = (Button) findViewById(R.id.startButton);
		
		// create a controller for the button
		startButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String p1 = p1name.getText().toString();
				String p2 = p2name.getText().toString();
				if(p1name.getText().toString().equals("")){
					model.setPlayer1("Player1");
				}
				else {
					model.setPlayer1(p1);
				}
				
				if(p2name.getText().toString().equals("")){
					model.setPlayer2("Player2");
				}
				else {
					model.setPlayer2(p2);
				}
				
				// do this each time the button is clicked
				model.setStage(4);
			}
		});
		
		toggleButton = (ToggleButton) findViewById(R.id.whofirst);
		
		toggleButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			    boolean on = ((ToggleButton) v).isChecked();
			    
			    if (on) {
			        model.setFirst(1);
			    } else {
			        model.setFirst(0);
			    }
			}
		});
		
		
	}
	


	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("TicTacToe", "update View1");
	    int first = model.whoFirst();
	    if (first == 0) {
			toggleButton.setChecked(false);
		}
	    else {
			toggleButton.setChecked(true);
		}
	    p1name.setText(model.getp1Name());
	    p2name.setText(model.getp2Name());
	    
	}
}
