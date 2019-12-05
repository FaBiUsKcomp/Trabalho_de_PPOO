import java.util.*;

/**
 * Essa eh a classe Chave Mestra da aplicacao "Caça ao Tesouro".
 *  "Caça ao Tesouro "se consiste em um jogo simples em que existe uma casa mal 
 *  assombrada com vários comodos em que o objetivo é encontrar
 *  um tesouro em um dos comodos da mesma, uma vez que as portas 
 *  podem estar emperradas e em que o jogador possui jogadas limitadas 
 *  para cada entrada nas portas.
 *  Esse jogo é baseado na Implementação do Jogo "World of Zuul"
 * 
 * @author Modificado com o uso de Design de classes Por (Kaio Vinicius, Fábio Júnio, Otávio Lima, Otávio Resende)
 * @version 2019.11.13
 */
public class ChaveMestra extends Item {
    private int vidaUtil;

    /**
     * Contrutor da Classe Chave Mestra
     */
    public ChaveMestra(){
        super();
        Random r = new Random();
        vidaUtil = r.nextInt(12) + 1;
    }

    /**
     * @return the vidaUtil
     */
    public int getVidaUtil() {
        return vidaUtil;
    }   

    /**
     * Método que refere ao uso da Chave Mestra
     */
    public void usarChave(){
        vidaUtil--;
    }
}