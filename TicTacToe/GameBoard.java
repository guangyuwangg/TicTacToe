package com.my.tictactoe;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import com.my.tictactoe.R;

import android.content.Context;
import android.util.*;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameBoard extends LinearLayout implements Observer {
	
	private Model model;
	private Vector<Vector<Button>> cells;
	private Button restart;
	private Button p1icon;
	private Button p2icon;
	private TextView p1name;
	private TextView p2name;
	private TextView message;
	private ImageView hb1;
	private ImageView hb2;
	private ImageView hb3;
	private ImageView vb1;
	private ImageView vb2;
	private ImageView vb3;
	private ImageView zb;
	private ImageView mb;


	public GameBoard(Context context, Model m) {
		super(context);
		
	    Log.d("TicTacToe", "View2 constructor");

		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.gameboard, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);
		
		p1name = (TextView) findViewById(R.id.n1);
		p2name = (TextView) findViewById(R.id.name2);
		p1icon = (Button)findViewById(R.id.button1);
		p2icon = (Button)findViewById(R.id.button2);
		message = (TextView)findViewById(R.id.message);

		// get a reference to widgets to manipulate on update
		cells = new Vector<Vector<Button>>();
		Vector<Button>row1 = new Vector<Button>();
		row1.add((Button)findViewById(R.id.cell1));
		row1.add((Button)findViewById(R.id.cell2));
		row1.add((Button)findViewById(R.id.cell3));
		cells.add(row1);
		row1.get(0).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				model.clickCell(0, 0);
			}
		});
		row1.get(1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				model.clickCell(0, 1);
			}
		});
		row1.get(2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				model.clickCell(0, 2);
			}
		});
		
		Vector<Button>row2 = new Vector<Button>();
		row2.add((Button)findViewById(R.id.cell4));
		row2.add((Button)findViewById(R.id.cell5));
		row2.add((Button)findViewById(R.id.cell6));
		cells.add(row2);
		row2.get(0).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				model.clickCell(1, 0);
			}
		});
		row2.get(1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				model.clickCell(1, 1);
			}
		});
		row2.get(2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				model.clickCell(1, 2);
			}
		});
		Vector<Button>row3 = new Vector<Button>();
		row3.add((Button)findViewById(R.id.cell7));
		row3.add((Button)findViewById(R.id.cell8));
		row3.add((Button)findViewById(R.id.cell9));
		cells.add(row3);
		row3.get(0).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				model.clickCell(2, 0);
			}
		});
		row3.get(1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				model.clickCell(2, 1);
			}
		});
		row3.get(2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// do this each time the button is clicked
				model.clickCell(2, 2);
			}
		});
		
		restart = (Button)findViewById(R.id.restart);
		restart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				model.setStage(1);
				
			}
		});
		
		hb1 = (ImageView) findViewById(R.id.hb1);
		hb2 = (ImageView) findViewById(R.id.hb2);
		hb3 = (ImageView) findViewById(R.id.hb3);
		vb1 = (ImageView) findViewById(R.id.vb1);
		vb2 = (ImageView) findViewById(R.id.vb2);
		vb3 = (ImageView) findViewById(R.id.vb3);
		zb = (ImageView) findViewById(R.id.z45b);
		mb = (ImageView) findViewById(R.id.m45b);
		hb1.setVisibility(INVISIBLE);
		hb2.setVisibility(INVISIBLE);
		hb3.setVisibility(INVISIBLE);
		vb1.setVisibility(INVISIBLE);
		vb2.setVisibility(INVISIBLE);
		vb3.setVisibility(INVISIBLE);
		zb.setVisibility(INVISIBLE);
		mb.setVisibility(INVISIBLE);
		

	}

	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("TicTacToe", "update View2");
	    
	    
	    if (model.getStage() == 3) {
	    	message.setText(model.getMsg());
	    	String winPos = model.getWinPos();
	    	if (!winPos.equals("no")) {
				if (winPos.equals("0")) {
					hb3.setVisibility(VISIBLE);
				}else if (winPos.equals("1")) {
					hb2.setVisibility(VISIBLE);
				}
				else if (winPos.equals("2")) {
					hb1.setVisibility(VISIBLE);
				}
				else if (winPos.equals("3")) {
					vb3.setVisibility(VISIBLE);
				}
				else if (winPos.equals("4")) {
					vb2.setVisibility(VISIBLE);
				}
				else if (winPos.equals("5")) {
					vb1.setVisibility(VISIBLE);
				}else if (winPos.equals("6")) {
					zb.setVisibility(VISIBLE);
				}else if (winPos.equals("7")) {
					mb.setVisibility(VISIBLE);
				}
			}
		}
	    else {
	    	String winPos = model.getWinPos();
			if (winPos.equals("no")) {
				hb1.setVisibility(INVISIBLE);
				hb2.setVisibility(INVISIBLE);
				hb3.setVisibility(INVISIBLE);
				vb1.setVisibility(INVISIBLE);
				vb2.setVisibility(INVISIBLE);
				vb3.setVisibility(INVISIBLE);
				zb.setVisibility(INVISIBLE);
				mb.setVisibility(INVISIBLE);
			}
		}
	   
	    if (model.getStage() == 2 || model.getStage() == 4) {
	    	message.setText(model.getMsg());
	    	
	    	String p1 = model.getp1Name();
	    	String p2 = model.getp2Name();
		    p1name.setText(p1);
		    p2name.setText(p2);
		    
		    if (model.getStage() == 4) {
				int first = model.whoFirst();
				if (first == 0) {
					p1icon.setBackgroundResource(R.drawable.p1cross);
					p2icon.setBackgroundResource(R.drawable.p2circleg);
				}
				else {
					p1icon.setBackgroundResource(R.drawable.p1crossg);
					p2icon.setBackgroundResource(R.drawable.p2circle);
				}
			}else{
				String cp = model.getCurPlayer();
			    if (cp.equals("X")) {
					p1icon.setBackgroundResource(R.drawable.p1cross);
					p2icon.setBackgroundResource(R.drawable.p2circleg);
				}
			    else{
					p1icon.setBackgroundResource(R.drawable.p1crossg);
					p2icon.setBackgroundResource(R.drawable.p2circle);
				}
			}
		}
	    
	  //set the display icon
	    if ( cells.size()== 3) {
	    	for(int i=0; i<cells.size(); i++){

	  			for(int j=0; j<cells.get(i).size(); j++){
	  				String cell = model.getCell(i, j);
	  				if(cell.equals(" ")){
	  					cells.get(i).get(j).setBackgroundResource(R.drawable.wood);
	  				}
	  				else if(cell.equals("X")){
	  					cells.get(i).get(j).setBackgroundResource(R.drawable.cross);
	  				}
	  				else{
	  					cells.get(i).get(j).setBackgroundResource(R.drawable.circle);
	  				}
	  			}
	  		}
		}
	}
}
