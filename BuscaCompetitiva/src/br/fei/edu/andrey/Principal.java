package br.fei.edu.andrey;

import javax.swing.SwingUtilities;

/***
 * 
 * @author Andrey Ferreira de Almeida
 * 
 * Responsabilidade: M�todo Main
 *
 */
public class Principal {

	public static void main(String[] args) {
		
		/***
		 * Instanc�a a interface em JFrame do jogo da velha numa nova Thread que n�o � a principal
		 */
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new JogoDaVelhaUI();				
			}
		});
	}

}
