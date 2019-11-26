import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.sound.midi.Soundbank;
import javax.sound.sampled.SourceDataLine;
import javax.xml.bind.SchemaOutputResolver;

/**
 *  Essa eh a classe principal da aplicacao "World of Zull".
 *  "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *  Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 *  precisa ser estendido para fazer algo interessante!
 * 
 *  Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 *  "jogar".
 * 
 *  Essa classe principal cria e inicializa todas as outras: ela cria os
 *  Comodos, cria o analisador e comeca o jogo. Ela tambeme avalia e 
 *  executa os comandos que o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 * 
 * @author Modificado com o uso de Design de classes Por (Kaio Vinicius, Fábio Júnio, Otávio Lima, Otávio Resende)
 * @version 2019.11.13
 */

public class Jogo 
{
    private Analisador analisador;
    private Comodo comodoAtual;
    private int tentativas;
    private HashMap <Integer, Comodo> comodos;
    private ChaveMestra chave;
    private Tesouro tesouro;
    private Dica dica1;
    private Dica dica2;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() 
    {
        
        analisador = new Analisador();
        Random r = new Random();
        tentativas = r.nextInt(31) + 20;
        comodos = new HashMap<Integer, Comodo>();
        chave = new ChaveMestra();
        tesouro = new Tesouro();
        criarComodos();
        chave.espalhar(comodos);
        tesouro.espalhar(comodos);
    }

    /**
     * Cria todos os Comodos e liga as saidas deles
     */
    private void criarComodos()
    {
        Comodo salaTv, salaJantar, escritorio, jardim, cozinha, corredor, quarto1, quarto2, quarto3,
               quarto4, banheiro1, banheiro2;
        
        salaTv = new Comodo("SalaTv");
        salaJantar = new Comodo("SaladeJantar");
        escritorio = new Comodo("Escritorio");
        jardim = new Comodo("Jardim");
        cozinha = new Comodo("Cozinha");
        corredor = new Comodo("Corredor");
        quarto1 = new Comodo("Quarto1");
        quarto2 = new Comodo("Qaurto2");
        quarto3 = new Comodo("Qaurto3");
        quarto4 = new Comodo("Quarto4");
        banheiro1 = new Comodo("Banheiro1");
        banheiro2 = new Comodo("Banheiro2");

        // adcionar no hash map

        comodos.put(1, salaTv);
        comodos.put(2, escritorio);
        comodos.put(3, jardim);
        comodos.put(4, cozinha);
        comodos.put(5, salaJantar);
        comodos.put(6, corredor);
        comodos.put(7, quarto1);
        comodos.put(8, quarto2);
        comodos.put(9, quarto3);
        comodos.put(10, quarto4);
        comodos.put(11, banheiro1);
        comodos.put(12, banheiro2);

        // ajustar saidas

        salaTv.ajustarSaidas("Escritorio", escritorio);
        salaTv.ajustarSaidas("SalaJantar", salaJantar);
        salaTv.ajustarSaidas("Jardim", jardim);

        salaJantar.ajustarSaidas("SalaTV", salaTv);
        salaJantar.ajustarSaidas("Cozinha", cozinha);
        salaJantar.ajustarSaidas("Corredor", corredor);

        jardim.ajustarSaidas("Cozinha", cozinha);
        jardim.ajustarSaidas("SalaTv", salaTv);

        escritorio.ajustarSaidas("SalaTv", salaTv);

        cozinha.ajustarSaidas("Jardim", jardim);
        cozinha.ajustarSaidas("SalaJantar", salaJantar);

        corredor.ajustarSaidas("Quarto1", quarto1);
        corredor.ajustarSaidas("Quarto2", quarto2);
        corredor.ajustarSaidas("Quarto3", quarto3);
        corredor.ajustarSaidas("Quarto4", quarto4);
        corredor.ajustarSaidas("Banheiro1", banheiro1);
        corredor.ajustarSaidas("SalaJantar", salaJantar);

        quarto1.ajustarSaidas("Corredor", corredor);

        quarto2.ajustarSaidas("Corredor", corredor);

        quarto4.ajustarSaidas("Corredor", corredor);

        banheiro1.ajustarSaidas("Corredor", corredor);

        quarto3.ajustarSaidas("Corredor", corredor);
        quarto3.ajustarSaidas("Banheiro2", banheiro2);

        banheiro2.ajustarSaidas("Quarto3", quarto3);

        comodoAtual = salaTv;  // o jogo comeca do lado de fora
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() 
    {            
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.
                
        boolean terminado = false;
        boolean explosao = false;
        
        while (! terminado && !explosao && temTentativas()) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
            explosao = processarComando(comando);
            if (!temTentativas()){
                System.out.println("Game Over: tentativas esgotadas");
            }
            if(explosao){
                if(tesouro.getEncontrado()){
                    System.out.println("Você encontrou o tesouro!!!");
                } else {
                    System.out.println("Game Over: tesouro não encontrado");
                }
            }
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas()
    {
        System.out.println();
        System.out.println("Bem-vindo ao World of Zuul!");
        System.out.println("World of Zuul eh um novo jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();
        
        imprimeLocalizacaoAtual();
    }

    /*Método que imprime/apresenta as saídas disponíveis do lugar em que se encontra o jogador*/
    private void imprimeLocalizacaoAtual(){
        System.out.println("Voce esta" + comodoAtual.getDescricao());
        System.out.println("Saidas: ");
        System.out.print(comodoAtual.getSaidas());
        System.out.println();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) 
    {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaComodo(comando);
        }
        else if(palavraDeComando.equals("observar")){
            observar();
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }
        else if (palavraDeComando.equals("explodir")) {
            querSair = explodir(comando);
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de 
     * palavras de comando
     */
    private void imprimirAjuda() 
    {
        System.out.println("Voce esta perdido. Voce esta sozinho. Voce caminha");
        System.out.println("pela casa mal assombrada.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println("   ir sair ajuda explodir");
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo Comodo, caso contrario imprime mensagem de erro.
     */
    private void irParaComodo(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        String comodo = comando.getSegundaPalavra();

        // Tenta sair do Comodo atual
        Comodo proximoComodo = null;
        proximoComodo = comodoAtual.getComodo(comodo);

        if(proximoComodo == null){
            System.out.println("Não há passagem!");
        } else {
            if (comodoAtual.getItem() != null){
                if( comodoAtual.getItem() instanceof ChaveMestra){
                    System.out.println("Parabens voce achou uma chave mestra!");
                    chave.setEncontrado(true);
                    
                } else if ( comodoAtual.getItem() instanceof Dica) {
                    if (comodoAtual.getItem() == dica1){
                        System.out.println(dica1.getDescricao());
                    } else {
                        System.out.println(dica2.getDescricao());
                    }
                }
                comodoAtual.setItem(null);
            }
            if (chave.getEncontrado()){
                System.out.println("Deseja usar a chave mestra (sim/nao) ?");
                Scanner ent = new Scanner(System.in);
                String resp = ent.nextLine();
                if (resp.equals("sim")){
                    comodoAtual = proximoComodo;
                } else {
                irDireto(comodoAtual, proximoComodo);
                }
            } else {
                irDireto(comodoAtual, proximoComodo);
            
            }
            imprimeLocalizacaoAtual();
        }
    }

    /*Método observar*/
    private void observar(){
        imprimeLocalizacaoAtual();
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nos queremos sair
        }
    }

    private boolean explodir(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            System.out.println("Explodir o que?");
            return false;
        }
        else {
            if(comodoAtual.getItem()!= null && comodoAtual.getItem() instanceof Tesouro){
                tesouro.setEncontrado(true);
            }
            return true;  // sinaliza a explosao
        }
    }

    private boolean temTentativas(){
        return tentativas > 0 ? true : false;
    }

    private void irDireto(Comodo comodoAtual, Comodo proximoComodo){
        if (temTentativas()){
            boolean estadoPorta = comodoAtual.porta();
            if (estadoPorta){
                System.out.println("Porta funcionando corretamente");
                comodoAtual = proximoComodo;
            } else {
                System.out.println("Porta emperrada !!");
            }
            tentativas--;
        }
    }

}
