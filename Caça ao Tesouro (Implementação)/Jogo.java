

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
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() 
    {
        criarComodos();
        analisador = new Analisador();
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

        // inicializa portas 

        Porta porta1, porta2, porta3, porta4, porta5, porta6, porta7, porta8, porta9, porta10, porta11, porta12,
              porta13, porta14, porta15, porta16, porta17, porta18, porta19, porta20, porta21, porta22, porta23, porta24;
        
        porta1 = new Porta(escritorio);
        porta2 = new Porta(salaJantar);
        porta3 = new Porta(jardim);
        salaTv.ajustarSaidas("Escritorio", porta1);
        salaTv.ajustarSaidas("SalaJantar", porta2);
        salaTv.ajustarSaidas("Jardim", porta3);

        porta4 = new Porta(salaTv);
        escritorio.ajustarSaidas("SalaTv", porta4);

        porta5 = new Porta(salaTv);
        porta6 = new Porta(cozinha);
        porta7 = new Porta(corredor);
        salaJantar.ajustarSaidas("SalaTv", porta5);
        salaJantar.ajustarSaidas("Cozinha", porta6);
        salaJantar.ajustarSaidas("Corredor", porta7);

        porta8 = new Porta(salaJantar);
        porta9 = new Porta(jardim);
        cozinha.ajustarSaidas("SalaJantar", porta8);
        cozinha.ajustarSaidas("Jardim", porta9);

        porta10 = new Porta(salaTv);
        porta11 = new Porta(cozinha);
        jardim.ajustarSaidas("SalaTv", porta10);
        jardim.ajustarSaidas("Cozinha", porta11);

        porta12 = new Porta(salaJantar);
        porta13 = new Porta(quarto1);
        porta14 = new Porta(quarto2);
        porta15 = new Porta(quarto3);
        porta16 = new Porta(quarto4);
        porta17 = new Porta(banheiro1);
        corredor.ajustarSaidas("SalaJantar", porta12);
        corredor.ajustarSaidas("Quarto1", porta13);
        corredor.ajustarSaidas("Quarto2", porta14);
        corredor.ajustarSaidas("Quarto3", porta15);
        corredor.ajustarSaidas("Quarto4", porta16);
        corredor.ajustarSaidas("Banheiro1", porta17);

        porta18 = new Porta(corredor);
        quarto1.ajustarSaidas("Corredor", porta18);

        porta19 = new Porta(corredor);
        quarto2.ajustarSaidas("Corredor", porta19);

        porta20 = new Porta(corredor);
        porta24 = new Porta(banheiro2);
        quarto3.ajustarSaidas("Corredor", porta20);
        quarto3.ajustarSaidas("Banheiro2", porta24);

        porta21 = new Porta(corredor);
        quarto4.ajustarSaidas("Corredor", porta21);

        porta22 = new Porta(corredor);
        banheiro1.ajustarSaidas("Corredor", porta22);

        porta23 = new Porta(quarto3);
        banheiro2.ajustarSaidas("Quarto3", porta23);

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
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
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
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println("   ir sair ajuda");
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

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do Comodo atual
        Comodo proximoComodo = null;
        proximoComodo = comodoAtual.getComodo(direcao);

        if(proximoComodo == null){
            System.out.println("Não há passagem!");
        } else {
            comodoAtual = proximoComodo;
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
}
