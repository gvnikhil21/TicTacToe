package com.bridgelabs.tictactoe;

import java.util.Scanner;

public class TicTacToe {

	public static final int HEAD = 1;

	private static Scanner sc = new Scanner(System.in);
	private static boolean isGameActive = true;
	private static char board[];
	private static char computerLetter;
	private static char playerLetter;
	private static int isPlayerTurn;

	public static void main(String[] args) {
		System.out.println("Welcome to Tic-Tac-Toe Game!");

		TicTacToe ticTacToe = new TicTacToe();
		while (isGameActive == true)
			ticTacToe.playGame();
		System.out.println("Thanks for playing the game!");
	}

	private void playGame() {
		// create board of length 10 & initialize indices except 0th-index with space
		board = creatBoard();

		// decides 'X' or 'O' for the player and computer
		decideLetterByPlayer();

		// isPlayerTurn equals 1 implies player turn, else computer turn
		isPlayerTurn = tossDecideFirstTurn();

		// calls move for user or computer till game is over and asks for another game
		makeMove();
	}

	private char[] creatBoard() {
		char boardArray[] = new char[10];
		for (int indexBoard = 1; indexBoard < boardArray.length; indexBoard++)
			boardArray[indexBoard] = ' ';
		return boardArray;
	}

	private void decideLetterByPlayer() {
		char letterChosen = ' ';
		do {
			System.out.println("Enter 'X' or 'O' to choose the letter for playing: ");
			letterChosen = sc.next().charAt(0);
			if (letterChosen == 'X' || letterChosen == 'O')
				break;
			System.out.println("Enter valid letter either X or O");
		} while (letterChosen != 'X' && letterChosen != 'O');
		playerLetter = letterChosen;
		computerLetter = (playerLetter == 'X') ? 'O' : 'X';
		System.out.println("Player letter is: " + playerLetter);
		System.out.println("Computer letter is: " + computerLetter);
	}

	private void makeMove() {
		int boardFillCount = 0;
		while (boardFillCount < 9 && isWinner() == false) {
			if (isPlayerTurn == 1) {
				System.out.println("Player's turn: ");
				makeMoveUser();
				isPlayerTurn = 0;
			} else {
				System.out.println("Computer's turn: ");
				makeMoveForComputer();
				isPlayerTurn = 1;
			}
			if (isWinner() && isPlayerTurn == 1) {
				System.out.println("Computer has Won!");
				break;
			} else if (isWinner() && isPlayerTurn == 0) {
				System.out.println("Player has Won!");
				break;
			}
			boardFillCount++;
		}
		if (isWinner() == false)
			System.out.println("It's a Draw! No one has won");
		System.out.println("Do you want to play again?(Y/N): ");
		isGameActive = (sc.next().toUpperCase().charAt(0) == 'Y') ? true : false;
	}

	// making move for user
	private void makeMoveUser() {
		int position = 0;
		do {
			System.out.println("Enter the position you want to move to: ");
			position = sc.nextInt();
			if (position > 0 && position < 10 && isPositionFree(position)) {
				moveToPosition(position, playerLetter);
				break;
			} else
				System.out.println("Position should be empty and between 1 and 9(including 1 and 9)");
		} while (position < 1 || position > 9 || isPositionFree(position) == false);
	}

	// making move for computer
	private void makeMoveForComputer() {
		int position = checkCompMove();
		if (position == -1) {
			do {
				position = (int) Math.floor(Math.random() * 9) + 1;
				if (position > 0 && position < 10 && isPositionFree(position)) {
					moveToPosition(position, computerLetter);
					break;
				}
			} while (position < 1 || position > 9 || isPositionFree(position) == false);
		} else
			moveToPosition(position, computerLetter);
	}

	// move to position provided
	private void moveToPosition(int position, char letter) {
		board[position] = letter;
		showBoard();
	}

	// check if position is free or occupied
	private boolean isPositionFree(int position) {
		return board[position] == ' ' ? true : false;
	}

	// toss to decide first turn, HEAD: Player plays first else computer
	private int tossDecideFirstTurn() {
		int tossResult = (int) Math.floor(Math.random() * 10) % 2;
		if (tossResult == HEAD)
			System.out.println("Player plays first");
		else
			System.out.println("Computer plays first");
		return (tossResult == HEAD) ? 1 : 0;
	}

	private int checkCompMove() {
		int position = -1;
		// checks if computer can win
		for (int indexBoard = 1; indexBoard < board.length; indexBoard++) {
			if (board[indexBoard] == ' ') {
				board[indexBoard] = computerLetter;
				if (isWinner()) {
					board[indexBoard] = ' ';
					return indexBoard;
				}
				board[indexBoard] = ' ';
			}
		}
		// checks if player can win
		if (position == -1) {
			for (int indexBoard = 1; indexBoard < board.length; indexBoard++) {
				if (board[indexBoard] == ' ') {
					board[indexBoard] = playerLetter;
					if (isWinner()) {
						board[indexBoard] = ' ';
						return indexBoard;
					}
					board[indexBoard] = ' ';
				}
			}
		}
		// checks available corners
		if (position == -1) {
			if (board[1] == ' ')
				position = 1;
			else if (board[3] == ' ')
				position = 3;
			else if (board[7] == ' ')
				position = 7;
			else if (board[9] == ' ')
				position = 9;
		}
		// check available center
		if (position == -1) {
			if (board[5] == ' ')
				position = 5;
		}
		return position;
	}

	// check winner
	private boolean isWinner() {
		return ((board[1] == board[2] && board[2] == board[3] && board[1] != ' ')
				|| (board[4] == board[5] && board[5] == board[6] && board[4] != ' ')
				|| (board[7] == board[8] && board[8] == board[9] && board[7] != ' ')
				|| (board[1] == board[4] && board[4] == board[7] && board[1] != ' ')
				|| (board[2] == board[5] && board[5] == board[8] && board[2] != ' ')
				|| (board[3] == board[6] && board[6] == board[9] && board[3] != ' ')
				|| (board[1] == board[5] && board[5] == board[9] && board[1] != ' ')
				|| (board[3] == board[5] && board[5] == board[7] && board[3] != ' '));
	}

	// displaying current board
	private static void showBoard() {
		System.out.println("\n" + board[1] + " | " + board[2] + " | " + board[3]);
		System.out.println("----------");
		System.out.println(board[4] + " | " + board[5] + " | " + board[6]);
		System.out.println("----------");
		System.out.println(board[7] + " | " + board[8] + " | " + board[9]);
	}
}