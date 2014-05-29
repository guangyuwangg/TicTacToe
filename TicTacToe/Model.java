package com.my.tictactoe;

import java.util.Observable;
import java.util.Vector;

import android.R.string;
import android.view.ViewGroup;


public class Model extends Observable {
	// the data in the model, just a counter
	private int stage = 1; //1: ready to start, selecting whose move; 2:gaming; 3:Finished!
	//4: choose whoFirst, ready for game
	private int whoFirst = 0;
	private String curPlayer;
	private int moveCount = 0;
	private int emptySlot = 9;
	private String message = "0 move.";
	private Vector<Vector<String>>cells;	//used to track board
	private Vector<Integer>wonCells;	//integers used to display 
	private ReadyScreen gameOption;
	private GameBoard gb;
	private ViewGroup vg;
	private String player1name;
	private String player2name;
	private String winPos = "no";


	Model() {
		//initialize cells
		initCells();
        
		setChanged();
	}

	//set up a new game/reset everything
	public void setNew(){
		stage = 1;
		moveCount = 0;
		message = "0 move.";
		emptySlot = 9;
		wonCells.clear();
		winPos = "no";
		initCells();

		setChanged();
		notifyObservers();
	}
	
	public void setReadyScreen(ReadyScreen rs){
		gameOption = rs;
	}
	
	public void setGameBoard(GameBoard v2){
		gb = v2;
	}
	
	public void setViewGroup(ViewGroup vig){
		vg = vig;
	}
	
	public void initCells(){
		wonCells = new Vector<Integer>();
		cells = new Vector<Vector <String>>();
		cells.add(new Vector<String>());
		cells.add(new Vector<String>());
		cells.add(new Vector<String>());
        for(int i=0; i<cells.size(); i++){
        	cells.get(i).add(" ");
        	cells.get(i).add(" ");
        	cells.get(i).add(" ");
        }
	}
	public void setCells(Vector<Vector<String>>c){
		cells = c;
	}
	
	public int getMoveCount(){
		return moveCount;
	}
	public void setMoveCount(int c){
		moveCount = c;
	}
	
	public int getEmptySlot(){
		return emptySlot;
	}
	public void setEmptySlot(int s){
		emptySlot = s;
	}
	
	public String getCurPlayer(){
		return curPlayer;
	}
	public void setCurPlayer(String p){
		curPlayer = p;
	}
	
	public String getCell(int i, int j){
		return cells.get(i).get(j);
	}
	public void clickCell(int i,int j){
		if(stage == 1 || stage == 3){
			//havent select, or game ended
			return;
		}
		if(stage == 4){
			stage = 2;
			if(whoFirst == 0){
				curPlayer = "X";
			}
			else{
				curPlayer = "O";
			}
		}
		String cell = cells.get(i).get(j);
		if(cell.equals(" ")){	//empty, can be clicked
			cells.get(i).set(j, curPlayer);
			moveCount++;
			message = moveCount + " moves";
			
			//let the other player move
			if(curPlayer.equals("X")){
				curPlayer = "O";
			}
			else{
				curPlayer = "X";
			}
			emptySlot--;
			//check if anyone win or draw
			if(checkWin()){
				
			}
			else {
				if(emptySlot == 0){
					stage = 3;
					message = "  Game over\n  no winner";
				}
			}
		}
		else{	//illegal step!
			message = "Illegal move";
		}
		
		setChanged();
		notifyObservers();
	}
	
	public boolean checkWin(){
		String s1;
		String s2;
		String s3;
		for(int i = 0; i<cells.size(); i++){ //by row
			s1 = cells.get(i).get(0);
			s2 = cells.get(i).get(1);
			s3 = cells.get(i).get(2);
			if(s1.equals(s2) && s2.equals(s3) && !(s1.equals(" "))){
				stage = 3;
				message = s1+" Wins!";
				wonCells.add(new Integer(i));
				wonCells.add(new Integer(0));
				wonCells.add(new Integer(i));
				wonCells.add(new Integer(1));
				wonCells.add(new Integer(i));
				wonCells.add(new Integer(2));
				winPos = Integer.toString(i);
				return true;
			}
		}
		
		for(int i = 0; i<cells.size(); i++){ //by row
			s1 = cells.get(0).get(i);
			s2 = cells.get(1).get(i);
			s3 = cells.get(2).get(i);
			if(s1.equals(s2) && s2.equals(s3) && !(s1.equals(" "))){
				stage = 3;
				message = s1+" Wins!";
				wonCells.add(new Integer(0));
				wonCells.add(new Integer(i));
				wonCells.add(new Integer(1));
				wonCells.add(new Integer(i));
				wonCells.add(new Integer(2));
				wonCells.add(new Integer(i));
				winPos = Integer.toString(i+3);
				return true;
			}
		}
		
		s1 = cells.get(0).get(0);
		s2 = cells.get(1).get(1);
		s3 = cells.get(2).get(2);
		if(s1.equals(s2) && s2.equals(s3) && !(s1.equals(" "))){
			stage = 3;
			message = s1+" Wins!";
			wonCells.add(new Integer(0));
			wonCells.add(new Integer(0));
			wonCells.add(new Integer(1));
			wonCells.add(new Integer(1));
			wonCells.add(new Integer(2));
			wonCells.add(new Integer(2));
			winPos = Integer.toString(6);
			return true;
		}
		
		s1 = cells.get(0).get(2);
		s2 = cells.get(1).get(1);
		s3 = cells.get(2).get(0);
		if(s1.equals(s2) && s2.equals(s3) && !(s1.equals(" "))){
			stage = 3;
			message = s1+" Wins!";
			wonCells.add(new Integer(0));
			wonCells.add(new Integer(2));
			wonCells.add(new Integer(1));
			wonCells.add(new Integer(1));
			wonCells.add(new Integer(2));
			wonCells.add(new Integer(0));
			winPos = Integer.toString(7);
			return true;
		}
		
		return false;
		
	}
	public String getWinPos(){
		return winPos;
	}
	public void setWinPos(String wp){
		winPos = wp;
	}
	
	
	public void setPlayer1(String s){
		player1name = s;
	}
	public String getp1Name(){
		return player1name;
	}
	
	public void setPlayer2(String s){
		player2name = s;
	}
	public String getp2Name(){
		return player2name;
	}
	
	public Vector<Integer>getWon(){
		return wonCells;
	}
	public void setWon(Vector<Integer>v){
		wonCells = v;
	}

	//get the message to display
	public String getMsg(){
		return message;
	}
	public void setMsg(String m){
		message = m;
	}

	//manage stage 
	public void setStage(int i){
		stage = i;
		if (vg != null) {
			if (stage == 4) {
				vg.removeAllViews();
				vg.addView(gb);
			}
			else if(stage == 1){
				setNew();
				vg.removeAllViews();
				vg.addView(gameOption);
			}
		}
		
		setChanged();
		notifyObservers();
	}
	public int getStage(){
		return stage;
	}

	// used to track which button to gray out
	public void setFirst(int i){
		if(stage == 1 || stage == 4){
			whoFirst = i;
			message = "0 move.";
			if(stage == 1||stage == 4){
				setChanged();
				notifyObservers();
			}
		}
	}
	public int whoFirst(){
		return whoFirst;
	}

	@Override
	protected void setChanged() {
		super.setChanged();
	}

}
