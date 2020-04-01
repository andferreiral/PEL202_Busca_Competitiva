package br.fei.edu.andrey;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/***
 * 
 * @author Andrey Ferreira de Almeida
 * 
 * Responsabilidade: Cria a interface em JFrame com as regras do jogo da velha
 *
 */
public class JogoDaVelhaUI extends JFrame {

	/***
	 * Hash (padrão SHA) do Java criado para identificação da classe
	 */
	private static final long serialVersionUID = 1L;

	/***
	 * Implementação do design pattern View Holder {@link #ViewHolder}
	 * 
	 */
	private ViewHolder viewHolder = new ViewHolder();

	/***
	 * Atributos da classe
	 */
	private boolean vencedor;
	private String jogadorAtual;
	private static final String jogadorHumano = "X";
	private static final String jogadorMaquina = "O";
	public Coordenada peca;

	/***
	 * Método construtor que configura os elementos da interface JFrame
	 */
	public JogoDaVelhaUI() {
		super();
		this.viewHolder.painel = getContentPane();
		this.viewHolder.painel.setLayout(new GridLayout(3, 3));
		this.viewHolder.campos = new JButton[3][3];

		setTitle("PEL202 - Jogo da Velha");
		setSize(620, 620);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

		jogadorAtual = jogadorHumano;
		vencedor = false;

		criaCampos();

	}

	/***
	 * Método que reinicia os campos do jogo e os valores das principais variáveis utilizadas
	 */
	public void reiniciaCampos() {
		vencedor = false;
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				this.viewHolder.campos[a][b].setText("");
			}
		}
		if(jogadorAtual == jogadorMaquina) {
			trocaJogador();
		}
	}

	/***
	 * Método que configura as propriedades dos objetos na tela
	 * 
	 * Adiciona os elementos criados no painel do frame
	 * 
	 * Implementa um listener que ouve às ações de clique do usuário na interface
	 * 
	 * Contém a chamada do algoritmo minimax {@link #minimax(int, String)}
	 */
	public void criaCampos() {
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				JButton botao = new JButton();
				botao.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
				botao.setBackground(Color.WHITE);
				Border borda = new LineBorder(Color.BLACK, 3);

				botao.setBorder(borda);
				botao.setSize(140, 140);
				this.viewHolder.campos[a][b] = botao;
				botao.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (((JButton) e.getSource()).getText().equals("")) {
							botao.setText(jogadorAtual);

							if (jogadorAtual == jogadorHumano) {
								botao.setForeground(Color.BLUE);
								minimax(0, jogadorMaquina);
								System.out.println("Computador escolheu a posição: " + peca);
								fazJogada(peca, jogadorMaquina);
								trocaJogador();
							} else {
								botao.setForeground(Color.GREEN);
							}
							trocaJogador();
						}
					}
				});

				this.viewHolder.painel.add(botao);
			}
		}
	}

	/***
	 *Método que troca a vez da jogada dos jogadores "X" e "O"
	 *
	 * Quando o jogador atual for "X", troca para "O" e vice-versa
	 */
	public void trocaJogador() {
		verificaVencedor();
		if (jogadorAtual == jogadorHumano) {
			jogadorAtual = jogadorMaquina;
		} else {
			jogadorAtual = jogadorHumano;
		}
	}

	/***
	 * Método que verifica se na matriz bidimensional de tamanho 3x3 foram preenchidas todas as condições para informar a vitória de um jogador
	 * 
	 * Quando esse valor for verdadeiro, informa quem é o vencedor baseado nos valores configurados nos textos dos botões da interface
	 * 
	 * @return vencedor
	 */
	public boolean verificaVencedor() {
		for (int i = 0; i < 3; ++i) {
			if (this.viewHolder.campos[0][i].getText().equals(jogadorAtual)
					&& this.viewHolder.campos[1][i].getText().equals(jogadorAtual)
					&& this.viewHolder.campos[2][i].getText().equals(jogadorAtual)) {
				return informaVencedor();
			} else if (this.viewHolder.campos[i][0].getText().equals(jogadorAtual)
					&& this.viewHolder.campos[i][1].getText().equals(jogadorAtual)
					&& this.viewHolder.campos[i][2].getText().equals(jogadorAtual)) {
				return informaVencedor();
			}
		}

		if ((this.viewHolder.campos[0][0].getText().equals(jogadorAtual)
				&& this.viewHolder.campos[1][1].getText().equals(jogadorAtual)
				&& this.viewHolder.campos[2][2].getText().equals(jogadorAtual))
				|| (this.viewHolder.campos[0][2].getText().equals(jogadorAtual)
						&& this.viewHolder.campos[1][1].getText().equals(jogadorAtual)
						&& this.viewHolder.campos[2][0].getText().equals(jogadorAtual))) {
			return informaVencedor();
		} else if (!vencedor) {
			return informaVelha();
		} else {
			return vencedor;
		}
	}

	/***
	 * Método que informa que não há vencedor quando todos os campos da interface foram preenchidos e nenhum deles satisfez condição para vitória
	 * 
	 * As condições para vitória são descritas no método {@link #verificaVencedor()}
	 * 
	 * @return vencedor
	 */
	public boolean informaVelha() {
		int camposPreenchidos = 0;
		for (int a = 0; a < 3; a++) {
			for (int b = 0; b < 3; b++) {
				if (!this.viewHolder.campos[a][b].getText().equals("")) {
					camposPreenchidos += 1;
				}
			}
		}

		if (camposPreenchidos == 9) {
			JOptionPane.showMessageDialog(null, "Deu velha e ninguém ganhou o jogo!!! xD", "Deu Velha",
					JOptionPane.ERROR_MESSAGE);
			vencedor = true;
			jogadorAtual = jogadorMaquina;
			reiniciaCampos();

			return vencedor;
		}

		return vencedor;
	}

	/***
	 * Método que informa quem é o vencedor do jogo, ou seja, quando algum jogador satisfez condição para vitória
	 * 
	 * As condições para vitória são descritas no método {@link #verificaVencedor()}
	 * 
	 * @return vencedor
	 */
	public boolean informaVencedor() {

		if (jogadorAtual == jogadorHumano) {
			JOptionPane.showMessageDialog(null, "Você venceu o jogo!!! :D", "Humano Venceu",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Você perdeu o jogo para a IA!!! ", "IA Venceu",
					JOptionPane.WARNING_MESSAGE);

		}
		vencedor = true;
		jogadorAtual = jogadorMaquina;
		reiniciaCampos();
		return vencedor;
	}

	/***
	 * Implementação do design pattern View Holder para melhor organização das alterações dos elementos da interface
	 * 
	 * @author Andrey Ferreira de Almeida
	 *
	 * Sub-classe responsabilidade: Organizar os atributos que serão utilizados na interface 
	 *
	 */
	public class ViewHolder {
		private Container painel;
		private JButton[][] campos;
	}
	
	/***
	 * Método que verifica todos os campos vazios da matriz bidimensional de tamanho 3x3, a partir do valor do campo da interface
	 * 
	 * @return lista de campos vazios na matriz
	 */
	private ArrayList<Coordenada> getCamposVazios() {
		ArrayList<Coordenada> camposDisponiveis = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.viewHolder.campos[i][j].getText().contentEquals("")) {
					camposDisponiveis.add(new Coordenada(i, j));

				}
			}
		}

		return camposDisponiveis;
	}

	/***
	 * Método semelhante ao {@link #verificaVencedor()}
	 * 
	 * A diferença é que nesse método verificamos para apenas um jogador específico se ele preencheu as condições para vitória.
	 * 
	 * @param jogador
	 * @return True/False
	 */
	public boolean jogadorVenceu(String jogador) {
		if ((this.viewHolder.campos[0][0].getText().equals(this.viewHolder.campos[1][1].getText())
				&& this.viewHolder.campos[0][0].getText().equals(this.viewHolder.campos[2][2].getText())
				&& this.viewHolder.campos[0][0].getText().equals(jogador))
				|| (this.viewHolder.campos[0][2].getText().equals(this.viewHolder.campos[1][1].getText())
						&& this.viewHolder.campos[0][2].getText().equals(this.viewHolder.campos[2][0].getText())
						&& this.viewHolder.campos[0][2].getText().equals(jogador))) {
			return true;
		}

		for (int i = 0; i < 3; i++) {
			if ((this.viewHolder.campos[i][0].getText().contentEquals(this.viewHolder.campos[i][1].getText())
					&& this.viewHolder.campos[i][0].getText().equals(this.viewHolder.campos[i][2].getText())
					&& this.viewHolder.campos[i][0].getText().equals(jogador))
					|| (this.viewHolder.campos[0][i].getText().equals(this.viewHolder.campos[1][i].getText())
							&& this.viewHolder.campos[0][i].getText().equals(this.viewHolder.campos[2][i].getText())
							&& this.viewHolder.campos[0][i].getText().equals(jogador))) {
				return true;
			}
		}

		return false;
	}

	/***
	 * Método que configura uma jogada de um jogador específico a partir das coordenadas x e y
	 * 
	 * @param coordenada
	 * @param jogador
	 * @return True/False
	 */
	public boolean fazJogada(Coordenada coordenada, String jogador) {
		if (!this.viewHolder.campos[coordenada.x][coordenada.y].getText().equals(""))
			return false;
		this.viewHolder.campos[coordenada.x][coordenada.y].setText(jogador);
		this.viewHolder.campos[coordenada.x][coordenada.y].setForeground(Color.GREEN);
		this.viewHolder.campos[coordenada.x][coordenada.y].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
		return true;
	}

	/***
	 * Algoritmo minimax que retorna a próxima possível melhor jogada a partir dos campos disponíveis na matriz 3x3
	 * 
	 * Referências:
	 * RUSSELL, Stuart J.; NORVIG, Peter. Busca Competitiva. In: ARTIFICIAL Intelligence: A Modern Approach. 3ª. ed. [S. l.]: Prentice Hall, 2009. cap. 5, p. 161 - 201. ISBN 0-13-604259-7.
	 * CODEBYTES. Minimax Algorithm Tic Tac Toe AI In Java [Minimax][Full tree Search][Artificial Intelligence][Java]. [S. l.], 25 ago. 2014. Disponível em: http://www.codebytes.in/2014/08/minimax-algorithm-tic-tac-toe-ai-in.html. Acesso em: 28 mar. 2020.
	 * 
	 * @param profundidade
	 * @param jogador
	 * @return min/max
	 */
	public int minimax(int profundidade, String jogador) {
		if (jogadorVenceu(jogadorHumano))
			return 1;
		if (jogadorVenceu(jogadorMaquina))
			return -1;

		ArrayList<Coordenada> camposDisponiveis = getCamposVazios();

		if (camposDisponiveis.isEmpty())
			return 0;

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < camposDisponiveis.size(); i++) {
			Coordenada item = camposDisponiveis.get(i);

			if (jogador == jogadorHumano) {
				fazJogada(item, jogador);
				int pontuacao = minimax(profundidade + 1, jogadorMaquina);
				max = Math.max(pontuacao, max);

				if (profundidade == 0)
					System.out
							.println("Pontuação atual do computador para a posição atual " + item + " = " + pontuacao);

				if (pontuacao >= 0)
					if (profundidade == 0)
						peca = item;

				if (pontuacao == 1) {
					this.viewHolder.campos[item.x][item.y].setText("");
					break;
				}

				if (i == getCamposVazios().size() - 1 && max < 0)
					if (profundidade == 0)
						peca = item;

			} else if (jogador == jogadorMaquina) {
				fazJogada(item, jogador);
				int pontuacaoAtual = minimax(profundidade, jogadorHumano);

				min = Math.min(pontuacaoAtual, min);

				if (min == -1) {
					this.viewHolder.campos[item.x][item.y].setText("");
					break;
				}
			}

			this.viewHolder.campos[item.x][item.y].setText("");

		}

		return jogador == jogadorHumano ? max : min;

	}

}
