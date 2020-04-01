package br.fei.edu.andrey;

/***
 * 
 * @author Andrey Ferreira de Almeida
 * 
 * Responsabilidade: Representa as coordenadas na matriz do jogo da velha
 *
 */
public class Coordenada {

	/***
	 * Atributos que representam o eixo "x" e "y"
	 * 
	 * Encapsulamento publico para acesso direto via chamada do pr�prio atributo da classe
	 */
	public int x,y;
	
	/***
	 * M�todo construtor com par�metros
	 * 
	 * @param x
	 * @param y
	 */
	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/***
	 * M�todo construtor sem par�metros
	 */
	public Coordenada() {
		
	}
	
	/***
	 * M�todo sobrescrito que retorna o texto formatado do objeto criado
	 * 
	 * Auxilia na visualiza��o da estrutura criada
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
}
