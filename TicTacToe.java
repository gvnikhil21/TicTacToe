package com.bridgelabs.tictactoe;

import java.util.Scanner;

public class TicTacToe {

	public static final int HEAD = 1;

	private static Scanner sc = new Scanner(System.in);
	private static char computerLetter;
	private static char playerLetter;

	public static void main(String[] args) {
		// welcome message
		System.out.println("Welcome to Tic-Tac-Toe Game!");

		// creates board
		TicTacToe ticTacToe = new TicTacToe();
		char board[] = ticTacToe.creatBoard();

		// decides letter for player
		ticTacToe.decideLetterByPlayer();

		// decides first turn by toss
		int tossResult = ticTacToe.tossDecideFirstTurn();
		if (tossResult == HEAD) {
			System.out.println("Player plays first");
			ticTacToe.makeMoveUser(board);
		} else {
			System.out.println("Computer plays first");
			ticTacToe.makeMoveForComputer(board);
		}
	}

	// create board of length 10 & initialize indices except 0th-index with space
	private char[] creatBoard() {
		char board[] = new char[10];
		for (int indexBoard = 1; indexBoard < board.length; indexBoard++)
			board[indexBoard] = ' ';
		return board;
	}

	// deciding 'X' or 'O' for the player and computer
	private void decideLetterByPlayer() {
		playerLetter = acceptLetterFromThePlayer();
		computerLetter = (playerLetter == 'X') ? 'O' : 'X';
		System.out.println("Player letter is: " + playerLetter);
		System.out.println("Computer letter is: " + computerLetter);
	}

	// accepting 'X' or 'O' from the player
	private char acceptLetterFromThePlayer() {
		char letterChosen = ' ';
		do {
			System.out.println("Enter 'X' or 'O' to choose the letter for playing: ");
			letterChosen = sc.next().charAt(0);
			if (letterChosen == 'X' || letterChosen == 'O')
				break;
			System.out.println("Enter valid letter either X or O");
		} while (letterChosen != 'X' && letterChosen != 'O');
		return letterChosen;
	}

	// displaying current board
	private static void showBoard(char[] board) {
		System.out.println("\n" + board[1] + " | " + board[2] + " | " + board[3]);
		System.out.println("----------");
		System.out.println(board[4] + " | " + board[5] + " | " + board[6]);
		System.out.println("----------");
		System.out.println(board[7] + " | " + board[8] + " | " + board[9]);
	}

	// making move for user
	private void makeMoveUser(char[] board) {
		int position = 0;
		do {
			System.out.println("Enter the position you want to move to: ");
			position = sc.nextInt();
			if (position > 0 && position < 10 && isPositionFree(board, position)) {
				moveToPosition(position, board, playerLetter);
				break;
			} else
				System.out.println("Position should be empty and between 1 and 9(including 1 and 9)");
		} while (position < 1 || position > 9 || isPositionFree(board, position) == false);
	}

	// making move for computer
	private void makeMoveForComputer(char[] board) {
		int position = (int) Math.floor(Math.random() * 9) + 1;
		do {
			if (position > 0 && position < 10 && isPositionFree(board, position)) {
				moveToPosition(position, board, computerLetter);
				break;
			}
		} while (position < 1 || position > 9 || isPositionFree(board, position) == false);
	}

	// move to position provided
	private void moveToPosition(int position, char[] board, char letter) {
		board[position] = letter;
		showBoard(board);
	}

	// check if position is free or occupied
	private boolean isPositionFree(char[] board, int position) {
		return board[position] == ' ' ? true : false;
	}

	// toss to decide first turn
	private int tossDecideFirstTurn() {
		int tossResult = (int) Math.floor(Math.random() * 10) % 2;
		return tossResult;
	}
}
