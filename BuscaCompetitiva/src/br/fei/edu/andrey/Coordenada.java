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
	 * Encapsulamento publico para acesso direto via chamada do próprio atributo da classe
	 */
	public int x,y;
	
	/***
	 * Método construtor com parâmetros
	 * 
	 * @param x
	 * @param y
	 */
	public Coordenada(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/***
	 * Método construtor sem parâmetros
	 */
	public Coordenada() {
		
	}
	
	/***
	 * Método sobrescrito que retorna o texto formatado do objeto criado
	 * 
	 * Auxilia na visualização da estrutura criada
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
}
