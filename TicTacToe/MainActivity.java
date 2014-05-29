package com.my.tictactoe;

import java.util.ArrayList;
import java.util.Vector;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.my.tictactoe.R;

public class MainActivity extends Activity {
	Model model;
	ViewGroup ms;
	ReadyScreen readyscreen;
	GameBoard gb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("TicTacToe", "onCreate");

		// load the base UI (has places for the views)
		setContentView(R.layout.mainactivity);

		// Setup model
		model = new Model();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		Log.d("TicTacToe", "onPostCreate");
		// can only get widgets by id in onPostCreate for activity xml res

		// create the views and add them to the main activity
		readyscreen = new ReadyScreen(this, model);
		ms = (ViewGroup) findViewById(R.id.mainactivity);

		gb = new GameBoard(this, model);
		
		model.setReadyScreen(readyscreen);
		model.setGameBoard(gb);
		model.setViewGroup(ms);
		
		if (model.getStage() == 1) {
			ms.addView(readyscreen);
		}
		else {
			ms.addView(gb);
		}
//		ViewGroup v2 = (ViewGroup) findViewById(R.id.mainactivity_2);
//		ms.addView(view2);

		
		// initialize views
		model.setChanged();
		model.notifyObservers();

	}

	// save and restore state (need to do this to support orientation change)
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d("TicTacToe", "save state");
		outState.putInt("Stage", model.getStage());
		outState.putInt("whoFirst", model.whoFirst());
		outState.putString("curPlayer", model.getCurPlayer());
		outState.putInt("moveCount", model.getMoveCount());
		outState.putInt("emptySlot", model.getEmptySlot());
		outState.putString("1", model.getCell(0, 0));
		outState.putString("2", model.getCell(0, 1));
		outState.putString("3", model.getCell(0, 2));
		outState.putString("4", model.getCell(1, 0));
		outState.putString("5", model.getCell(1, 1));
		outState.putString("6", model.getCell(1, 2));
		outState.putString("7", model.getCell(2, 0));
		outState.putString("8", model.getCell(2, 1));
		outState.putString("9", model.getCell(2, 2));
		outState.putString("message", model.getMsg());
		ArrayList<Integer> wonCells = new ArrayList<Integer>();
		Vector<Integer> cells = model.getWon();
		for (int i = 0; i < cells.size(); i++) {
			wonCells.add(cells.get(i));
		}
		outState.putIntegerArrayList("wonCells", wonCells);
		
		outState.putString("player1name", model.getp1Name());
		outState.putString("player2name", model.getp2Name());
		outState.putString("winPos", model.getWinPos());
		
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.d("TicTacToe", "restore state");
		super.onRestoreInstanceState(savedInstanceState);
//		int stage = savedInstanceState.getInt("Stage");
//		if (stage == 4) {
//			ms.removeAllViews();
//			ms.addView(gb);
//		}
//		else if(stage == 1){
//			ms.removeAllViews();
//			ms.addView(readyscreen);
//		}
		model.setStage(savedInstanceState.getInt("Stage"));
		model.setFirst(savedInstanceState.getInt("whoFirst"));
		model.setCurPlayer(savedInstanceState.getString("curPlayer"));
		model.setMoveCount(savedInstanceState.getInt("moveCount"));
		model.setEmptySlot(savedInstanceState.getInt("emptySlot"));
		Vector<Vector<String>>cells = new Vector<Vector<String>>();
		cells.add(new Vector<String>());
		cells.add(new Vector<String>());
		cells.add(new Vector<String>());
		Vector<String>entry = cells.get(0);
		entry.add(savedInstanceState.getString("1"));
		entry.add(savedInstanceState.getString("2"));
		entry.add(savedInstanceState.getString("3"));
		entry = cells.get(1);
		entry.add(savedInstanceState.getString("4"));
		entry.add(savedInstanceState.getString("5"));
		entry.add(savedInstanceState.getString("6"));
		entry = cells.get(2);
		entry.add(savedInstanceState.getString("7"));
		entry.add(savedInstanceState.getString("8"));
		entry.add(savedInstanceState.getString("9"));
		model.setCells(cells);
		model.setMsg(savedInstanceState.getString("message"));
		
		ArrayList<Integer> wonCells = savedInstanceState.getIntegerArrayList("wonCells");
		Vector<Integer> wcells = new Vector<Integer>();
		for (int i = 0; i < wonCells.size(); i++) {
			wcells.add(wonCells.get(i));
		}
		model.setWon(wcells);
		
//		model.setViewGroup(ms);
//		model.setReadyScreen(readyscreen);
//		model.setGameBoard(gb);
		
		model.setPlayer1(savedInstanceState.getString("player1name"));
		model.setPlayer2(savedInstanceState.getString("player2name"));
		model.setWinPos(savedInstanceState.getString("winPos"));
	}

}

