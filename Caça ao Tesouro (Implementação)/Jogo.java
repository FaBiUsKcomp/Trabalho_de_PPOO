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

        /*Salva dados em arquivo texto*/
        salvaDados("BancoDeDados");
        
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

    /**
     * Método que retorna o numero de Tentativas que o Jogador ainda Possui
     * 
     * @return Inteiro referente ao numero de tentativas que o jogador possui
     */
    public int getTentativas(){
        return tentativas;
    }

    /**
     * Rotina Principal do Jogo (Por onde o comando é processado)
     * 
     * @param comando Comando que originado da GUI
     * @return String que se refere a localização atual no mapa da Casa
     */
    public String jogar(Comando comando) 
    {            
        processarComando(comando);
        if(getTentativas() == 0){
            Tela.getInstance().sair("Número de Tentativas esgotadas");
        }
        return obterLocalizacaoAtual();
    }

    /**
     * Método que imprime/apresenta as saídas disponíveis do lugar em que se encontra o jogador*
     * 
     * @return String que refere a saidas disponiveis
     */
    private String obterLocalizacaoAtual(){

        //Variavel que guarda a localização atual para interação com GUI
        return "<html>" + comodoAtual.getDescricao() + "<br/>" + "Saidas: " + comodoAtual.getSaidas() + "</html>";
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private void processarComando(Comando comando) 
    {

        if(comando.ehDesconhecido()) {
            Tela.getInstance().setMensagem("Último status: Eu nao entendi o que voce disse...");
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
                boolean sair = sair(comando);
                if(sair){
                    System.exit(0);
                }
            }
            else if (palavraDeComando.equals("explodir")) {
                explodir(comando);
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
        String frase = "<html>Você está perdido. Você está sozinho. Você caminha pela casa mal assombrada.<br/>Suas palavras de comando são:<br/>   ir sair ajuda explodir</html>";
        Tela.getInstance().getAjuda(frase);
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saida entra no 
     * novo Comodo, caso contrario imprime mensagem de erro.
     */
    private void irParaComodo(Comando comando) 
    {
        if(!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            Tela.getInstance().setMensagem("Último status: Ir pra onde?");
        }

        String comodo = comando.getSegundaPalavra();

        // Tenta sair do Comodo atual
        Comodo proximoComodo = null;
        proximoComodo = comodoAtual.getComodo(comodo);

        if(proximoComodo == null){
            Tela.getInstance().setMensagem("Último status: Não há passagem!");
        } else {
            if (chave.getEncontrado() && chave.getVidaUtil() > 0){
                String resp = Tela.getInstance().verificaChave("Deseja usar a chave mestra?");
                System.out.println(resp);
                if (resp.equals("sim")){
                    comodoAtual = proximoComodo;
                    chave.usarChave();
                    Tela.getInstance().setChave(chave.getVidaUtil());
                } else {
                    irDireto(proximoComodo);
                }
            } else {
                irDireto(proximoComodo);
            }
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
            Tela.getInstance().setMensagem("Último status: Sair o que?");
            return false;
        }
        return true;  // sinaliza que nos queremos sair
    }

    private boolean explodir(Comando comando) 
    {
        if(comando.temSegundaPalavra()) {
            Tela.getInstance().setMensagem("Último status: Explodir o que?");
            return false;
        }
        else {
            if(comodoAtual.getItem()!= null && comodoAtual.getItem() instanceof Tesouro){
                tesouro.setEncontrado(true);
                Tela.getInstance().sair(tesouro.getMensagem());
            } else {
                Tela.getInstance().sair("Game Over: tesouro não encontrado!");
            }
        }
        return true;  // sinaliza a explosao
    }

    /**
     * Método para verificar se o jogador possui tentativas
     * 
     * @return Booleano verifica a atual situação do Jogador em relação a tentativas.
     */
    private boolean temTentativas(){
        return tentativas > 0 ? true : false;
    }

    /**
     * Método para mudar que altera o comodo atual em que o jogador se encontra na casa
     * 
     * @param proximoComodo Comodo de destino que o jogador irá.
     */
    private void irDireto(Comodo proximoComodo){
        if (temTentativas()){
            boolean estadoPorta = comodoAtual.porta();
            tentativas--;
            if (estadoPorta){
                comodoAtual = proximoComodo;
                Tela.getInstance().setMensagem("Último status: Porta funcionando corretamente");
            } else {
                Tela.getInstance().setMensagem("Último status: Porta emperrada !!!");
            }
        }
    }

    /**
     * Método para verificar se o comodo tem item.
     */
    private void verificaItem(){
        if (comodoAtual.getItem() != null && !comodoAtual.getItem().getEncontrado()){
            if(comodoAtual.getItem() instanceof ChaveMestra){
                Tela.getInstance().setMensagem("Último status: Parabéns, você achou uma chave mestra!");
                Tela.getInstance().setChave(chave.getVidaUtil());             
            } else if (comodoAtual.getItem() instanceof Dica) {
                if (comodoAtual.getItem() == dica1){
                    Tela.getInstance().setDica1(dica1.getDescricao());
                } else {
                    Tela.getInstance().setDica2(dica2.getDescricao());
                }
            }
            (comodoAtual.getItem()).setEncontrado(true);   
        }
    }

    /**
     * Método para Salvar dados no Arquivo (Posições dos Itens)
     * 
     * @param nomeArquivo String que será o nome do Arquivo 
     */
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

    /**
     * Método para retornar Chave mestra
     * 
     * @return Chave mestra do jogo.
     */
    public ChaveMestra getChaveMestra(){
        return chave;
    }
}
