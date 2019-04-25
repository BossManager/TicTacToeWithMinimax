package main;

public class Board {
	private char board[][];
	private int cont = 0;

	public Board() {
		board = new char[3][3];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j]=' ';
			}
		}
	}
	public int getCont() {
		return cont;
	}
	public void setCont(int cont) {
		this.cont = cont;
	}
	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public void setSquad(int x, int y, char s) {
		if(board[x][y]==' ') {
			board[x][y] = s;
			cont++;			
		}else {
			System.out.println("HUMANO TRAPACEIRO!!!! NÃO VAI BUGAR MEU BELO PROGRAMA!!!!");
			System.out.println("JOGO TERMINADO!!!! HUMANO DESCLASSIFICADO ==> MAQUINA VENCE");
			System.exit(0);
		}
	}

	public void showBoard() {
		System.out.println("_|0 1 2");
		for (int i = 0; i < board.length; i++) {
			System.out.print(i + "|");
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int someoneWin(boolean vez) {
		if (cont == 9 && !checkWin(vez)) {
			return 1;// EMPATE
		}else {
			return checkWin(vez)?2:0;//vitoria:em andamento
		}
	}

	private boolean checkWin(boolean vez) {
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
}
