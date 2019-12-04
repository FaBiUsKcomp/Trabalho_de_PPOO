import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;


public class Tela {

	private JFrame telaPrincipal;

	/*Definindo Lado esquerdo da parte superior*/
	private JLabel textoTentativas;
	private JLabel textoDurabilidadeChave;
	private JLabel quantidadeTentativas;
	private JLabel durabilidadeChaveTexto;

	/*Definindo Centro da parte superior*/
	private JLabel imagemCampo;
	private ImageIcon imagem;

	/*Definindo Lado direito da parte superior*/
	private JLabel textoDicasEncontradas;
	private JLabel dica1;
	private JLabel dica2;

	/*Definindo Parte Inferior*/
	private JLabel textoBemVindo;
	private JLabel textoInformaJogo;
	private JLabel textoAjuda;
	private JTextField entradaDeDados;
	
	private String comandoAtual;

	private Analisador analisador;

	private Jogo jogo;

	/*Construtor da Interface Gráfica*/
	public Tela(Jogo j){
		/*Parte esquerda Superior*/
		telaPrincipal = new JFrame("Casa Mal Assombrada");
		textoTentativas = new JLabel("<html><h2>Número de Tentativas Restantes:</h2></html>");
		textoDurabilidadeChave = new JLabel("<html><h2> Durabilidade da chave mestra:</h2></html>");
		quantidadeTentativas = new JLabel("<html><h2>35</h2></html>");
		durabilidadeChaveTexto = new JLabel("<html><h2>8</h2></html>");
		jogo = j;
		analisador = new Analisador();
		/*Parte central Superior*/
		imagemCampo = new JLabel();
		imagem = new ImageIcon(new ImageIcon("img/mapa-casa.png").getImage().getScaledInstance(650, 400, Image.SCALE_DEFAULT));

		/*Parte direita Superior*/
		textoDicasEncontradas = new JLabel("<html><h2>Dicas encontradas:</h2></html>");
		dica1 = new JLabel("<html><br><strong><h3>O tesouro não está no escritório</h3></strong><br></html>");
		dica2 = new JLabel("<html><br><strong><h3>O tesouro não está no quarto 1</h3></strong><br></html>");

		/*Parte Inferior*/
		textoBemVindo = new JLabel("Bem-Vindo ao Caça ao Tesouro!");
		textoInformaJogo = new JLabel("Caça ao Tesouro é um novo jogo de aventura, incrivelmente bacana.");
		textoAjuda = new JLabel("Digite 'ajuda' se você precisar de ajuda.");
		entradaDeDados = new JTextField();
		entradaDeDados.addKeyListener(new KeyAdapter(){
			@Override
    	public void keyPressed(KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
					Comando comando = analisador.pegarComando(entradaDeDados.getText());
					jogo.jogar(comando);

				}
			}
		});

		montarTela();
		telaPrincipal.pack();
	}

	/*Método Responsável por montar o layout da Interface Gráfica*/
	private void montarTela(){
		//telaPrincipal.setSize(1000, 500);
		telaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		telaPrincipal.setLayout(new BorderLayout());

		/*Configurando Lado Esquerdo Superior*/
		JPanel campoEsquerdoSuperior = new JPanel();
		campoEsquerdoSuperior.setPreferredSize(new Dimension(250,370));
		//Aribuindo borda
		campoEsquerdoSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
		campoEsquerdoSuperior.setLayout(new BoxLayout(campoEsquerdoSuperior, BoxLayout.Y_AXIS));
		campoEsquerdoSuperior.setBackground(Color.WHITE);
		//Setando formato de texto tentativas
		textoTentativas.setFont(new Font("Courier New", Font.BOLD,18));
		campoEsquerdoSuperior.add(textoTentativas);
		//Setando formato de texto quantidades tentativas
		quantidadeTentativas.setBorder(new EmptyBorder(0,10,0,0));
		campoEsquerdoSuperior.add(quantidadeTentativas);
		//Setando formato de texto durabilidade chave
		campoEsquerdoSuperior.add(textoDurabilidadeChave);
		durabilidadeChaveTexto.setBorder(new EmptyBorder(0,10,0,0));
		campoEsquerdoSuperior.add(durabilidadeChaveTexto);

		/*Configurando Lado central Superior*/
		JPanel campoCentralSuperior = new JPanel();
		campoCentralSuperior.setPreferredSize(new Dimension(500,370));
		campoCentralSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
		campoCentralSuperior.setLayout(new BorderLayout());
		campoCentralSuperior.setBackground(Color.WHITE);
		imagemCampo.setIcon(imagem);
		imagemCampo.setHorizontalAlignment(SwingConstants.CENTER);
		imagemCampo.setVerticalAlignment(SwingConstants.CENTER);
		campoCentralSuperior.add(imagemCampo, BorderLayout.CENTER);

		/*Configurando Lado Direito Superior*/
		JPanel campoDireitoSuperior = new JPanel();
		campoDireitoSuperior.setPreferredSize(new Dimension(250,370));
		campoDireitoSuperior.setBorder(BorderFactory.createLineBorder(Color.black));
		campoDireitoSuperior.setLayout(new BoxLayout(campoDireitoSuperior, BoxLayout.Y_AXIS));
		campoDireitoSuperior.setBackground(Color.WHITE);
		campoDireitoSuperior.add(textoDicasEncontradas);
		//Setando formato de dicas
		dica1.setBorder(new EmptyBorder(0,10,0,0));
		campoDireitoSuperior.add(dica1);
		dica2.setBorder(new EmptyBorder(0,10,0,0));
		campoDireitoSuperior.add(dica2);

		/*Configurando parte inferior*/
		JPanel campoInferior = new JPanel();
		campoInferior.setPreferredSize(new Dimension(1000, 130));
		campoInferior.setBorder(BorderFactory.createLineBorder(Color.black));
		campoInferior.setLayout(new BoxLayout(campoInferior, BoxLayout.Y_AXIS));
		campoInferior.setBackground(Color.WHITE);
		campoInferior.add(textoBemVindo);
		campoInferior.add(textoInformaJogo);
		campoInferior.add(textoAjuda);
		campoInferior.add(entradaDeDados);

		telaPrincipal.add(campoEsquerdoSuperior, BorderLayout.WEST);
		telaPrincipal.add(campoCentralSuperior, BorderLayout.CENTER);
		telaPrincipal.add(campoDireitoSuperior, BorderLayout.EAST);
		telaPrincipal.add(campoInferior, BorderLayout.SOUTH);
		telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Definindo icone da aplicação
		ImageIcon imgTituloJanela = new ImageIcon("img/mapa-casa.png");
		telaPrincipal.setIconImage(imgTituloJanela.getImage());
	}

	/*Método responsável por deixar a janela visível*/
	public void exibirTela(){
		telaPrincipal.setVisible(true);
	}

	public void setChave(int quant){
		durabilidadeChaveTexto = new JLabel("<html><h2>" + quant +"</h2></html>");
	}

	public String getComandoAtual(){
		return comandoAtual;
	}

	public void setComandoAtual(){
		comandoAtual = "nenhum";
	}
}