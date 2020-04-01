package br.fei.edu.andrey;

import javax.swing.SwingUtilities;

/***
 * 
 * @author Andrey Ferreira de Almeida
 * 
 * Responsabilidade: Método Main
 *
 */
public class Principal {

	public static void main(String[] args) {
		
		/***
		 * Instancía a interface em JFrame do jogo da velha numa nova Thread que não é a principal
		 */
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new JogoDaVelhaUI();				
			}
		});
	}

}
