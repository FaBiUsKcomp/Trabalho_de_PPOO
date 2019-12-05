import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;

/**
 * Classe que representa a estruturação GUI do Jogo Caça ao Tesouro
 * 
 * @author Modificado com o uso de Design de classes Por (Kaio Vinicius, Fábio Júnio, Otávio Lima, Otávio Resende)
 * @version 2019.11.13
 */
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
	private JLabel mensagem;
	private JLabel setinha;
	private JTextField entradaDeDados;

	/*Setando analisador para tratar comandos */
	private Analisador analisador;
	/*Uso de Padrão Singleton*/
	private static Tela instanciaTela;

	private Jogo jogo;

	/**
	 * Construtor da Interface Gráfica
	 *  */ 
	private Tela() {
		jogo = new Jogo();
		/* Parte esquerda Superior */
		telaPrincipal = new JFrame("Casa Mal Assombrada");
		textoTentativas = new JLabel("<html><h2>Número de Tentativas Restantes:</h2></html>");
		textoDurabilidadeChave = new JLabel("<html><h2> Durabilidade da chave mestra:</h2></html>");
		quantidadeTentativas = new JLabel("<html><h2>" + Integer.toString(jogo.getTentativas()) + "</h2></html>");
		durabilidadeChaveTexto = new JLabel();
		analisador = new Analisador();
		/* Parte central Superior */
		imagemCampo = new JLabel();
		imagem = new ImageIcon(new ImageIcon("img/mapa-casa.png").getImage().getScaledInstance(650, 400, Image.SCALE_DEFAULT));

		/* Parte direita Superior */
		textoDicasEncontradas = new JLabel("<html><h2>Dicas encontradas:</h2></html>");
		dica1 = new JLabel();
		dica2 = new JLabel();

		/* Parte Inferior */
		textoBemVindo = new JLabel("Bem-Vindo ao Caça ao Tesouro!");
		textoInformaJogo = new JLabel("Caça ao Tesouro é um novo jogo de aventura, incrivelmente bacana.");
		textoAjuda = new JLabel("Digite 'ajuda' se você precisar de ajuda.");
		textoLocalAtual = new JLabel(
				"<html>Você está no(a) SalaTv da casa mal assombrada <br/> Saídas: Escritório, SalaJantar, Jardim</html>");
		mensagem = new JLabel();
		setinha = new JLabel(">");
		entradaDeDados = new JTextField();
		//Evento responsável por tratar a tecla "Enter" do teclado
		entradaDeDados.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					setMensagem("");
					Comando comando = analisador.pegarComando(entradaDeDados.getText());
					String textoPrincipal = jogo.jogar(comando);
					setTela(textoPrincipal);
				}
			}
		});

		montarTela();
		telaPrincipal.pack();
	}

	/**
	 * Esse método permite uma única instanciação de um único objeto da classe e o retorna
	 * 
	 * @return Tela
	 */
	public static Tela getInstance(){
		if(instanciaTela == null){
			instanciaTela = new Tela();
		}
		return instanciaTela;
	}

	/**
	 * Método Responsável por montar o layout da Interface Gráfica
	 *  */ 
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
		/*Setando a parte da entrada de dados (Terminal)*/
		campoInferiorUltimaLinha.add(setinha);
		campoInferiorUltimaLinha.add(entradaDeDados);
		/*Setando a borda do campo inferior*/
		campoInferior.setBorder(BorderFactory.createLineBorder(Color.black));
		/*Setando os textos da interface */
		campoInferior.setBackground(Color.WHITE);
		textoBemVindo.setBorder(new EmptyBorder(0, 10, 0, 0));
		textoBemVindo.setFont(new Font("Courier New", Font.ITALIC, 14));
		textoInformaJogo.setBorder(new EmptyBorder(0, 10, 0, 0));
		textoInformaJogo.setFont(new Font("Courier New", Font.ITALIC, 14));
		textoAjuda.setBorder(new EmptyBorder(0, 10, 10, 0));
		textoAjuda.setFont(new Font("Courier New", Font.ITALIC, 14));
		textoLocalAtual.setBorder(new EmptyBorder(5, 10, 0, 0));
		textoLocalAtual.setFont(new Font("Courier New", Font.BOLD, 14));
		mensagem.setBorder(new EmptyBorder(0, 10, 0, 0));
		mensagem.setFont(new Font("Courier New", Font.BOLD, 14));
		/*Estilizando campo input da tela*/
		entradaDeDados.setBorder(BorderFactory.createLineBorder(Color.white));
		entradaDeDados.setPreferredSize(new Dimension(1200, 30));
		/*Construíndo campo inferior da tela*/
		campoInferior.add(textoBemVindo);
		campoInferior.add(textoInformaJogo);
		campoInferior.add(textoAjuda);
		campoInferior.add(textoLocalAtual);
		campoInferior.add(mensagem);
		campoInferior.add(campoInferiorUltimaLinha);
		campoInferior.add(ultimoEspaco);
		/*construindo tela principal*/
		telaPrincipal.add(campoEsquerdoSuperior, BorderLayout.WEST);
		telaPrincipal.add(campoCentralSuperior, BorderLayout.CENTER);
		telaPrincipal.add(campoDireitoSuperior, BorderLayout.EAST);
		telaPrincipal.add(campoInferior, BorderLayout.SOUTH);
		telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Definindo icone da aplicação
		ImageIcon imgTituloJanela = new ImageIcon("img/mapa-casa.png");
		telaPrincipal.setIconImage(imgTituloJanela.getImage());
	}

	/**
	 * Método responsável por deixar a janela visível
	 *  */  
	public void exibirTela() {
		telaPrincipal.setVisible(true);
	}

	/**
	 * Método que seta texto quantidade de tentativas da tela principal e seta localização atual
	 * 
	 * @param t uma String que contém a atual descrição da localização do usuário no Game
	 */
	private void setTela(String t) {
		quantidadeTentativas.setText("<html><h2>" + Integer.toString(jogo.getTentativas()) + "</h2></html>");
		//Texto que gera descrição da atual localização
		textoLocalAtual.setText(t);
		entradaDeDados.setText("");
	}

	/**
	 * Método que configura os dados da chave mestra caso a mesma for encontrada
	 * 
	 * @param quant indica a durabilidade da chave mestra
	 */
	public void setChave(int quant) {
		durabilidadeChaveTexto.setText("<html><h2>" + quant + "</h2></html>");
		textoDurabilidadeChave.setVisible(true);
		durabilidadeChaveTexto.setVisible(true);
	}

	/**
	 * Método que verifica a opção do usuário de utilizar ou não a chave mestra
	 * 
	 * @param s texto relativo a pergunta relacionada a decisão ou não de usar a chave mestra
	 * @return String que retorna a opção escolhida pelo usuário (sim/nao)
	 */
	public String verificaChave(String s){
		String[] opcoes = {"sim", "nao"};
		int option = JOptionPane.showOptionDialog(telaPrincipal, s, "Usar Chave", JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);
		if(option == 0){
			return "sim";
		}
		return "nao";
	}

	/**
	 * Método que configura o último status do útimo comando digitado
	 * 
	 * @param s texto do útimo status descrito
	 */
	public void setMensagem(String s){
		mensagem.setText(s);
	}

	/**
	 * Método que que configura a descrição da primeira dica encontrada
	 * 
	 * @param s descrição da dica encontrada
	 */
	public void setDica1(String s){
		dica1.setText(s);
	}

	/**
	 * Método que que configura a descrição da segunda dica encontrada
	 * 
	 * @param s descrição da dica encontrada
	 */
	public void setDica2(String s){
		dica2.setText(s);
	}

	/**
	 * Método que encerra o Jogo
	 * 
	 * @param text texto relativo ao status do útimo comando
	 */
	public void sair(String text){
		JOptionPane.showMessageDialog(telaPrincipal, text, "Fim de Jogo", JOptionPane.WARNING_MESSAGE);
		System.exit(0);
	}

	/**
	 * Método que retorna informações de auxílio para o jogador
	 * 
	 * @param ajuda descrição do auxílio fornecido
	 */
	public void getAjuda(String ajuda){
		JOptionPane.showMessageDialog(telaPrincipal, ajuda, "Ajuda", JOptionPane.WARNING_MESSAGE);
	}
}