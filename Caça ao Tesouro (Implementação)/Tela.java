import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;

public class Tela {

	private JFrame telaPrincipal;

	/* Definindo Lado esquerdo da parte superior */
	private JLabel textoTentativas;
	private JLabel textoDurabilidadeChave;
	private JLabel quantidadeTentativas;
	private JLabel durabilidadeChaveTexto;

	/* Definindo Centro da parte superior */
	private JLabel imagemCampo;
	private ImageIcon imagem;

	/* Definindo Lado direito da parte superior */
	private JLabel textoDicasEncontradas;
	private JLabel dica1;
	private JLabel dica2;

	/* Definindo Parte Inferior */
	private JLabel textoBemVindo;
	private JLabel textoInformaJogo;
	private JLabel textoAjuda;
	private JLabel textoLocalAtual;
	private JLabel setinha;
	private JTextField entradaDeDados;

	private String comandoAtual;

	private Analisador analisador;

	private Jogo jogo;

	/* Construtor da Interface Gráfica */
	public Tela(Jogo j) {
		jogo = j;
		/* Parte esquerda Superior */
		telaPrincipal = new JFrame("Casa Mal Assombrada");
		textoTentativas = new JLabel("<html><h2>Número de Tentativas Restantes:</h2></html>");
		textoDurabilidadeChave = new JLabel("<html><h2> Durabilidade da chave mestra:</h2></html>");
		quantidadeTentativas = new JLabel("<html><h2>" + Integer.toString(jogo.getTentativas()) + "</h2></html>");
		durabilidadeChaveTexto = new JLabel("<html><h2>8</h2></html>");
		analisador = new Analisador();
		/* Parte central Superior */
		imagemCampo = new JLabel();
		imagem = new ImageIcon(
				new ImageIcon("img/mapa-casa.png").getImage().getScaledInstance(650, 400, Image.SCALE_DEFAULT));

		/* Parte direita Superior */
		textoDicasEncontradas = new JLabel("<html><h2>Dicas encontradas:</h2></html>");
		dica1 = new JLabel("<html><br><strong><h3>O tesouro não está no escritório</h3></strong><br></html>");
		dica2 = new JLabel("<html><br><strong><h3>O tesouro não está no quarto 1</h3></strong><br></html>");

		/* Parte Inferior */
		textoBemVindo = new JLabel("Bem-Vindo ao Caça ao Tesouro!");
		textoInformaJogo = new JLabel("Caça ao Tesouro é um novo jogo de aventura, incrivelmente bacana.");
		textoAjuda = new JLabel("Digite 'ajuda' se você precisar de ajuda.");
		textoLocalAtual = new JLabel(
				"<html>Você esta na Sala de Tv da casa mal assombrada <br/> Saídas: Escritório, SalaDeJantar, Jardim</html>");
		setinha = new JLabel(">");
		entradaDeDados = new JTextField();
		entradaDeDados.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					Comando comando = analisador.pegarComando(entradaDeDados.getText());
					String textoPrincipal = jogo.jogar(comando);
					setaTela(textoPrincipal);
				}
			}
		});

		montarTela();
		telaPrincipal.pack();
	}

	/* Método Responsável por montar o layout da Interface Gráfica */
	private void montarTela() {
		// telaPrincipal.setSize(1000, 500);
		telaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		telaPrincipal.setLayout(new BorderLayout());

		/* Configurando Lado Esquerdo Superior */
		JPanel campoEsquerdoSuperior = new JPanel();
		campoEsquerdoSuperior.setPreferredSize(new Dimension(250, 350));
		// Aribuindo borda
		campoEsquerdoSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
		campoEsquerdoSuperior.setLayout(new BoxLayout(campoEsquerdoSuperior, BoxLayout.Y_AXIS));
		campoEsquerdoSuperior.setBackground(Color.WHITE);
		// Setando formato de texto tentativas
		textoTentativas.setFont(new Font("Courier New", Font.BOLD, 18));
		campoEsquerdoSuperior.add(textoTentativas);
		// Setando formato de texto quantidades tentativas
		quantidadeTentativas.setBorder(new EmptyBorder(0, 10, 0, 0));
		campoEsquerdoSuperior.add(quantidadeTentativas);
		// Setando formato de texto durabilidade chave
		textoDurabilidadeChave.setVisible(false);
		campoEsquerdoSuperior.add(textoDurabilidadeChave);
		durabilidadeChaveTexto.setBorder(new EmptyBorder(0, 10, 0, 0));
		durabilidadeChaveTexto.setVisible(false);
		campoEsquerdoSuperior.add(durabilidadeChaveTexto);

		/* Configurando Lado central Superior */
		JPanel campoCentralSuperior = new JPanel();
		campoCentralSuperior.setPreferredSize(new Dimension(500, 350));
		campoCentralSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
		campoCentralSuperior.setLayout(new BorderLayout());
		campoCentralSuperior.setBackground(Color.WHITE);
		imagemCampo.setIcon(imagem);
		imagemCampo.setHorizontalAlignment(SwingConstants.CENTER);
		imagemCampo.setVerticalAlignment(SwingConstants.CENTER);
		campoCentralSuperior.add(imagemCampo, BorderLayout.CENTER);

		/* Configurando Lado Direito Superior */
		JPanel campoDireitoSuperior = new JPanel();
		campoDireitoSuperior.setPreferredSize(new Dimension(250, 350));
		campoDireitoSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
		campoDireitoSuperior.setLayout(new BoxLayout(campoDireitoSuperior, BoxLayout.Y_AXIS));
		campoDireitoSuperior.setBackground(Color.WHITE);
		campoDireitoSuperior.add(textoDicasEncontradas);
		// Setando formato de dicas
		dica1.setBorder(new EmptyBorder(0, 10, 0, 0));
		campoDireitoSuperior.add(dica1);
		dica2.setBorder(new EmptyBorder(0, 10, 0, 0));
		campoDireitoSuperior.add(dica2);

		/* Configurando parte inferior */
		JPanel campoInferior = new JPanel();
		JPanel campoInferiorUltimaLinha = new JPanel();
		campoInferior.setPreferredSize(new Dimension(1000, 150));
		JPanel ultimoEspaco = new JPanel();
		ultimoEspaco.setPreferredSize(new Dimension(1000, 10));
		ultimoEspaco.setBackground(Color.WHITE);
		campoInferior.setLayout(new BoxLayout(campoInferior, BoxLayout.Y_AXIS));
		campoInferiorUltimaLinha.setPreferredSize(new Dimension(1000, 30));
		campoInferiorUltimaLinha.setLayout(new FlowLayout());
		campoInferiorUltimaLinha.setBackground(Color.WHITE);
		campoInferiorUltimaLinha.add(setinha);
		campoInferiorUltimaLinha.add(entradaDeDados);

		campoInferior.setBorder(BorderFactory.createLineBorder(Color.black));

		campoInferior.setBackground(Color.WHITE);
		textoBemVindo.setBorder(new EmptyBorder(7, 10, 0, 0));
		textoBemVindo.setFont(new Font("Courier New", Font.ITALIC, 14));
		textoInformaJogo.setBorder(new EmptyBorder(0, 10, 0, 0));
		textoInformaJogo.setFont(new Font("Courier New", Font.ITALIC, 14));
		textoAjuda.setBorder(new EmptyBorder(0, 10, 10, 0));
		textoAjuda.setFont(new Font("Courier New", Font.ITALIC, 14));
		textoLocalAtual.setBorder(new EmptyBorder(5, 10, 0, 0));
		textoLocalAtual.setFont(new Font("Courier New", Font.BOLD, 14));

		entradaDeDados.setBorder(BorderFactory.createLineBorder(Color.white));
		entradaDeDados.setPreferredSize(new Dimension(1200, 30));

		campoInferior.add(textoBemVindo);
		campoInferior.add(textoInformaJogo);
		campoInferior.add(textoAjuda);
		campoInferior.add(textoLocalAtual);
		campoInferior.add(campoInferiorUltimaLinha);
		campoInferior.add(ultimoEspaco);

		telaPrincipal.add(campoEsquerdoSuperior, BorderLayout.WEST);
		telaPrincipal.add(campoCentralSuperior, BorderLayout.CENTER);
		telaPrincipal.add(campoDireitoSuperior, BorderLayout.EAST);
		telaPrincipal.add(campoInferior, BorderLayout.SOUTH);
		telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Definindo icone da aplicação
		ImageIcon imgTituloJanela = new ImageIcon("img/mapa-casa.png");
		telaPrincipal.setIconImage(imgTituloJanela.getImage());
	}

	/* Método responsável por deixar a janela visível */
	public void exibirTela() {
		telaPrincipal.setVisible(true);
	}

	public void setChave(int quant) {
		durabilidadeChaveTexto = new JLabel("<html><h2>" + quant + "</h2></html>");
	}

	public String getComandoAtual() {
		return comandoAtual;
	}

	private void setaTela(String t) {
		quantidadeTentativas.setText("<html><h2>" + Integer.toString(jogo.getTentativas()) + "</h2></html>");
		// setaChave();
		textoLocalAtual.setText(t);
		entradaDeDados.setText("");
	}

	public void setaChave() {
		textoDurabilidadeChave.setVisible(true);
		durabilidadeChaveTexto.setVisible(true);
	}
}