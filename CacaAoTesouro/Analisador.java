import java.util.Scanner;

/**
 * Essa eh a classe Analisador da aplicacao "Caça ao Tesouro".
 *  "Caça ao Tesouro "se consiste em um jogo simples em que existe uma casa mal 
 *  assombrada com vários comodos em que o objetivo é encontrar
 *  um tesouro em um dos comodos da mesma, uma vez que as portas 
 *  podem estar emperradas e em que o jogador possui jogadas limitadas 
 *  para cada entrada nas portas.
 *  Esse jogo é baseado na Implementação do Jogo "World of Zuul"
 *
 * O analisador tem um conjunto de palavras de comando conhecidas. Ele compara
 * a entrada do usuario com os comandos conhecidos, e se a entrada nao eh um
 * dos comandos conhecidos, ele retorna um objeto comando que eh marcado como
 * um comando desconhecido.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 * 
 * @author Modificado com o uso de Design de classes Por (Kaio Vinicius, Fábio Júnio, Otávio Lima, Otávio Resende)
 * @version 2019.11.13
 */
public class Analisador 
{
    private PalavrasComando palavrasDeComando;  // guarda todas as palavras de comando validas
    //private Scanner entrada;         // origem da entrada de comandos

    /**
     * Cria um analisador para ler do terminal.
     */
    public Analisador() 
    {
        palavrasDeComando = new PalavrasComando();
        //entrada = new Scanner(System.in);
    }

    /**
     * @return O proximo comando do usuario
     */
    public Comando pegarComando(String comando) 
    {
        //String linha;   // guardara uma linha inteira
        String palavra1 = null;
        String palavra2 = null;

        System.out.print(comando);     // imprime o prompt

        // Tenta encontrar ate duas palavras na linha
        Scanner tokenizer = new Scanner(comando);
        if(tokenizer.hasNext()) {
            palavra1 = tokenizer.next();      // pega a primeira palavra
            if(tokenizer.hasNext()) {
                palavra2 = tokenizer.next();      // pega a segunda palavra
                // obs: nos simplesmente ignoramos o resto da linha.
            }
        }
        tokenizer.close();
        // Agora verifica se esta palavra eh conhecida. Se for, cria um
        // com ela. Se nao, cria um comando "null" (para comando desconhecido)
        if(palavrasDeComando.ehComando(palavra1)) {
            return new Comando(palavra1, palavra2);
        }
        else {
            return new Comando(null, palavra2); 
        }
    }
}
