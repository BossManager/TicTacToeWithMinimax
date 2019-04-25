package main;

import java.util.Random;
import java.util.Scanner;

import IA.Minimax;

public class Aplicacao {
	public static int flipCoin() {
		Random rand = new Random();
		return rand.nextInt(2);
	}
	public static void main(String[] args) {
		Board tictactoe = new Board();
		Scanner sc = new Scanner(System.in);
		Minimax IA = new Minimax();
		System.out.println("BEM VINDO AO JOGO DA VELHA");
		System.out.println("Instruções:");
		System.out.println("X é a linha.");
		System.out.println("Y é a coluna.");

		boolean vez = false;//humano=false;maquina=true. Para definir quem começa primeiro
		if(flipCoin()==1)
			vez = true;
		
		if(vez)
			System.out.println("MODO DE JOGO:Maquina X Humano");
		else
			System.out.println("MODO DE JOGO:Humano X Maquina");
			
		int cod,x,y;
		while((cod =tictactoe.someoneWin(!vez))==0) {
			if(!vez) {
				tictactoe.showBoard();
				System.out.println("Sua jogada:");
				System.out.print("X:");
				x = sc.nextInt();
				System.out.print("Y:");
				y = sc.nextInt();
				System.out.println();
				tictactoe.setSquad(x, y, vez?'O':'X');
			}else {
				IA.calcularMovimento(tictactoe,vez);
			}
			vez = !vez;
		}
		
		if(cod == 1) {
			tictactoe.showBoard();
			System.out.println("EMPATE");
		}else{
			tictactoe.showBoard();
			System.out.println(!vez?"Maquina venceu!!!!!!!!!!":"Humano venceu!!!!!!!!!!");
		}
	}
}
