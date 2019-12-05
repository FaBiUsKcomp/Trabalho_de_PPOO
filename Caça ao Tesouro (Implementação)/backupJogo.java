import java.util.*;
import java.io.*;

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
        Random r = new Random();
        tentativas = r.nextInt(31) + 20;
        comodos = new HashMap<Integer, Comodo>();
        chave = new ChaveMestra();
        tesouro = new Tesouro(); 
        dica1 = new Dica("O tesouro não está no(a)");
        dica2 = new Dica("O tesouro está próximo ao(à)");
        criarComodos();
        chave.setLocal(comodos);
        tesouro.setLocal(comodos);
        dica1.setDescricao(comodos);
        dica2.setLocal(comodos);
        dica2.setDescricao((comodos.get(tesouro.getLocal())));

        System.out.println();
        System.out.println("Chave " + chave.getLocal());
        System.out.println("Dica1 " + dica1.getLocal());
        System.out.println("Dica2 " + dica2.getLocal());
        System.out.println("Tesouro " + tesouro.getLocal());
        System.out.println();

        salvaDados("BancoDeDados");
        
    }

    public boolean verificaChave(){
        return chave.getEncontrado();
    }

    public boolean verificaDica1(){
        return dica1.getEncontrado();
    }

    public boolean verificaDica2(){
        return dica2.getEncontrado();
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
        quarto2 = new Comodo("Quarto2");
        quarto3 = new Comodo("Quarto3");
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
        verificaItem();
    }

    public int getTentativas(){
        return tentativas;
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public String jogar(Comando comando) 
    {            
        processarComando(comando);
        return obterLocalizacaoAtual();
    }

    /*Método que imprime/apresenta as saídas disponíveis do lugar em que se encontra o jogador*/
    private String obterLocalizacaoAtual(){

        //Variavel que guarda a localização atual para interação com GUI
        return "<html>" + comodoAtual.getDescricao() + "<br/>" + "Saidas: " + comodoAtual.getSaidas() + "</html>";
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private void processarComando(Comando comando) 
    {

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
        } else {

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
                System.exit(0);
            }
            else if (palavraDeComando.equals("explodir")) {
                //querSair = explodir(comando);
            }

        }
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
        //return "<html>Voce esta perdido. Voce esta sozinho. Voce caminha pela casa mal assombrada.<br/>Suas palavras de comando sao:<br/>   ir sair ajuda explodir</html>";
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo Comodo, caso contrario imprime mensagem de erro.
     */
    private String irParaComodo(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            
            return ("Ir pra onde?");
        }

        String comodo = comando.getSegundaPalavra();

        // Tenta sair do Comodo atual
        Comodo proximoComodo = null;
        proximoComodo = comodoAtual.getComodo(comodo);

        if(proximoComodo == null){
            alteraTela("Nao ha passagem!");
        } else {
            /*if (chave.getEncontrado() && chave.getVidaUtil() > 0){
                System.out.println("Deseja usar a chave mestra (sim/nao) ?");
                Scanner ent = new Scanner(System.in);
                String resp = ent.nextLine();
                if (resp.equals("sim")){
                    comodoAtual = proximoComodo;
                    chave.usarChave();
                } else {
                    irDireto(proximoComodo);
                }
            } else {
                irDireto(proximoComodo);
            }*/
            String path = irDireto(proximoComodo);

            obterLocalizacaoAtual();
            verificaItem();
        }
    }

    /*Método observar*/
    private void observar(){
        obterLocalizacaoAtual();
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
                System.out.println(tesouro.getMensagem());
            } else {
                System.out.println("Game Over: tesouro não encontrado!");
            }
        }
        return true;  // sinaliza a explosao
    }

    private boolean temTentativas(){
        return tentativas > 0 ? true : false;
    }

    private String irDireto(Comodo proximoComodo){
        if (temTentativas()){
            boolean estadoPorta = comodoAtual.porta();
            tentativas--;
            if (estadoPorta){
                 comodoAtual = proximoComodo;
                return ("Porta funcionando corretamente");
            } else {
                return ("Porta emperrada !!!");
            }
        }
        return null;
    }

    private void verificaItem(){
        if (comodoAtual.getItem() != null && !comodoAtual.getItem().getEncontrado()){
            if(comodoAtual.getItem() instanceof ChaveMestra){
                System.out.println("Parabens voce achou uma chave mestra!");             
            } else if (comodoAtual.getItem() instanceof Dica) {
                if (comodoAtual.getItem() == dica1){
                    System.out.println(dica1.getDescricao());
                } else {
                    System.out.println(dica2.getDescricao());
                }
            }
            (comodoAtual.getItem()).setEncontrado(true);   
        }
    }

    private void salvaDados(String nomeArquivo){
        try{
            FileWriter arquivo = new FileWriter(nomeArquivo);
            arquivo.write("Posição da Chave: " + comodos.get(chave.getLocal()).getNome()  + "\n" +
                           "Posicão da Dica (Não Esta): " + comodos.get(dica1.getLocal()).getNome() + "\n" + 
                           "Posição da Dica (Esta próximo): " + comodos.get(dica2.getLocal()).getNome() + "\n" +
                           "Posição do Tesouro: " + comodos.get(tesouro.getLocal()).getNome());
            arquivo.close();
        }
        catch(IOException e){
            System.out.println("Falha ao Salvar em Arquivo " + nomeArquivo);
        }
    }

    public ChaveMestra getChaveMestra(){
        return chave;
    }
}
