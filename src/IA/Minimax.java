package IA;

import java.util.ArrayList;

import main.Board;

public class Minimax {

	public void calcularMovimento(Board tictactoe,boolean player) {
		minimax(tictactoe.getBoard(),tictactoe.getCont(),player);
		tictactoe.setCont(tictactoe.getCont()+1);
	}

	// minimax
	private int minimax(char board[][], int maxDepht, boolean player) {
		if(maxDepht++ == 9 || checkWin(player, board)|| checkWin(!player, board)) {
			return whoWin(board);
		}
		
		if (player) {
			return max(board, maxDepht, player);
		} else {
			return min(board, maxDepht, player);
		}
	}

	private char[][] cloneBoard(char[][] board) {
		char[][] clone = new char[3][3];
		for (int i = 0; i < clone.length; i++) {
			for (int j = 0; j < clone.length; j++) {
				clone[i][j] = board[i][j];
			}
		}
		return clone;
	}

	private int max(char board[][], int maxDepht, boolean player) {
		int best_move = -1;
		int best_score = Integer.MIN_VALUE;

		for (Integer move : possiblesMoves(board)) {
			char[][] board_variation = cloneBoard(board);
			board_variation[getX(move)][getY(move)] = player?'O':'X';
			int score = minimax(board_variation, maxDepht, !player);

			if(score*(10-maxDepht)>=best_score) {
				best_move = move;
				best_score = score;
				
			}
		}
		board[getX(best_move)][getY(best_move)] =  player?'O':'X';
		return best_score;
		
	}
	private int min(char board[][], int maxDepht, boolean player) {
		int best_move = -1;
		int best_score = Integer.MAX_VALUE;
	
		for (Integer move : possiblesMoves(board)) {
			char[][] board_variation = cloneBoard(board);
			board_variation[getX(move)][getY(move)] =  player?'O':'X';

			int score = minimax(board_variation, maxDepht, !player);

			if(score*maxDepht<=best_score) {
				best_move = move;
				best_score = score;
				
			}
		}
		board[getX(best_move)][getY(best_move)] =  player?'O':'X';
		return best_score;
	}
	private int getX(int move) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(move==i*3+j)
					return i;
			}
		}
		return 0;
	}
	private int getY(int move) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(move==i*3+j)
					return j;
			}
		}
		return 0;
	}

	private ArrayList<Integer> possiblesMoves(char[][] board) {
		ArrayList<Integer> moves = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == ' ')
					moves.add(i*3 + j);
			}
		}
		return moves;
	}
	public int whoWin(char[][] board) {
		if(checkWin(true, board)) {
			return 1;
		}else if(checkWin(false, board)){
			return -1;
		}else {
			return 0;
		}

	}
	private boolean checkWin(boolean vez,char[][] board) {
		char c = vez ? 'O' : 'X';
		// y-axis
		for (int i = 0; i < board.length; i++) {
			if (board[0][i] == c && board[1][i] == c && board[2][i] == c)
				return true;
		}
		// x-axis
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] == c && board[i][1] == c && board[i][2] == c)
				return true;
		}
		// main diagonal
		if (board[0][0] == c && board[1][1] == c && board[2][2] == c)
			return true;
		// secundary diagonal
		if (board[0][2] == c && board[1][1] == c && board[2][0] == c)
			return true;
		return false;
	}
	public void showBoard(char[][] board) {
		System.out.println("_|0 1 2");
		for (int i = 0; i < board.length; i++) {
			System.out.print(i + "|");
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		Board b = new Board();
		b.setSquad(0, 1, 'X');//  0 1 2
		b.setSquad(1, 1, 'O');//0 X X
		b.setSquad(0, 0, 'X');//1   O 
							  //2     

		Minimax IA = new Minimax();
		IA.calcularMovimento(b, true);
		IA.showBoard(b.getBoard());
	}

}
