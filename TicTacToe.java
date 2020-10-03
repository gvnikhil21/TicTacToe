package com.bridgelabs.tictactoe;

public class TicTacToe {

	public static void main(String[] args) {
		// welcome message
		System.out.println("Welcome to Tic-Tac-Toe Game!");

		TicTacToe ticTacToe = new TicTacToe();
		char board[] = ticTacToe.creatBoard();
	}

	// create board of length 10 & initialize indices except 0th-index with space
	private char[] creatBoard() {
		char board[] = new char[10];
		for (int indexBoard = 0; indexBoard < board.length; indexBoard++)
			board[indexBoard] = ' ';
		return board;
	}
}
