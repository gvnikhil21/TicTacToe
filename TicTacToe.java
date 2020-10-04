package com.bridgelabs.tictactoe;

import java.util.Scanner;

public class TicTacToe {

	public static final int HEAD = 1;

	private static Scanner sc = new Scanner(System.in);
	private static char computerLetter;
	private static char playerLetter;

	public static void main(String[] args) {
		// variables
		int isPlayerTurn;
		char board[];
		// welcome message
		System.out.println("Welcome to Tic-Tac-Toe Game!");

		// creates board
		TicTacToe ticTacToe = new TicTacToe();
		board = ticTacToe.creatBoard();

		// decides letter for player
		ticTacToe.decideLetterByPlayer();

		/* decides first turn by toss 
		 * HEAD: Player plays first, else computer plays
		 * first isPlayerTurn=1->player turn, else computer turn
		 */
		int tossResult = ticTacToe.tossDecideFirstTurn();
		if (tossResult == HEAD) {
			System.out.println("Player plays first");
			isPlayerTurn = 1;
		} else {
			System.out.println("Computer plays first");
			isPlayerTurn = 0;
		}
		ticTacToe.makeMove(isPlayerTurn, board);
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

	// making move which decided and calls move for user or computer
	private void makeMove(int isPlayerTurn, char[] board) {
		int boardFilled = 0;
		while (boardFilled < 9 && isWinner(board) == false) {
			if (isPlayerTurn == 1) {
				makeMoveUser(board);
				isPlayerTurn = 0;
			} else {
				makeMoveForComputer(board);
				isPlayerTurn = 1;
			}
			if (isWinner(board) && isPlayerTurn == 1) {
				System.out.println("Computer has Won!");
				break;
			} else if (isWinner(board) && isPlayerTurn == 0) {
				System.out.println("Player has Won!");
				break;
			}
			boardFilled++;
		}
		if (isWinner(board) == false)
			System.out.println("It's a Draw! No one has won");
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
		int position = checkCompWin(board);
		if (position == -1)
			position = checkPlayerWin(board);
		if (position == -2)
			position = checkAvailableCorners(board);
		if (position == -3) {
			do {
				position = (int) Math.floor(Math.random() * 9) + 1;
				if (position > 0 && position < 10 && isPositionFree(board, position)) {
					moveToPosition(position, board, computerLetter);
					break;
				}
			} while (position < 1 || position > 9 || isPositionFree(board, position) == false);
		} else
			moveToPosition(position, board, computerLetter);
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

	// checks if computer can win
	private int checkCompWin(char[] board) {
		int position = -1;
		for (int indexBoard = 1; indexBoard < board.length; indexBoard++) {
			if (board[indexBoard] == ' ') {
				board[indexBoard] = computerLetter;
				if (isWinner(board)) {
					position = indexBoard;
					break;
				}
				board[indexBoard] = ' ';
			}
		}
		return position;
	}

	// checks if player can win
	private int checkPlayerWin(char[] board) {
		int position = -2;
		for (int indexBoard = 1; indexBoard < board.length; indexBoard++) {
			if (board[indexBoard] == ' ') {
				board[indexBoard] = playerLetter;
				if (isWinner(board)) {
					position = indexBoard;
					break;
				}
				board[indexBoard] = ' ';
			}
		}
		return position;
	}

	// checks available corners
	private int checkAvailableCorners(char[] board) {
		int position = -3;
		if (board[1] == ' ')
			position = 1;
		else if (board[3] == ' ')
			position = 3;
		else if (board[7] == ' ')
			position = 7;
		else if (board[9] == ' ')
			position = 9;
		return position;
	}

	// check winner
	private boolean isWinner(char[] board) {
		return ((board[1] == board[2] && board[2] == board[3] && board[1] != ' ')
				|| (board[4] == board[5] && board[5] == board[6] && board[4] != ' ')
				|| (board[7] == board[8] && board[8] == board[9] && board[7] != ' ')
				|| (board[1] == board[4] && board[4] == board[7] && board[1] != ' ')
				|| (board[2] == board[5] && board[5] == board[8] && board[2] != ' ')
				|| (board[3] == board[6] && board[6] == board[9] && board[3] != ' ')
				|| (board[1] == board[5] && board[5] == board[9] && board[1] != ' ')
				|| (board[3] == board[5] && board[5] == board[7] && board[3] != ' '));
	}
}
