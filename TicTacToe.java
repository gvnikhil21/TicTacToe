package com.bridgelabs.tictactoe;

import java.util.Scanner;

public class TicTacToe {

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

		// displays board
		showBoard(board);
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
		System.out.println("\n" + board[1] + "  | " + board[2] + " |  " + board[3]);
		System.out.println("-----------");
		System.out.println(board[4] + "  | " + board[5] + " |  " + board[6]);
		System.out.println("-----------");
		System.out.println(board[7] + "  | " + board[8] + " |  " + board[9]);
	}
}
