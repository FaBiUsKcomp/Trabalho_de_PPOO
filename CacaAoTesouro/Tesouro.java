/**
 * Essa eh a classe Tesouro da aplicacao "Caça ao Tesouro".
 *  "Caça ao Tesouro "se consiste em um jogo simples em que existe uma casa mal 
 *  assombrada com vários comodos em que o objetivo é encontrar
 *  um tesouro em um dos comodos da mesma, uma vez que as portas 
 *  podem estar emperradas e em que o jogador possui jogadas limitadas 
 *  para cada entrada nas portas.
 *  Esse jogo é baseado na Implementação do Jogo "World of Zuul"
 * 
 * Classe Reponsável por Controlar o Tesouro do Jogo
 * 
 * @author Modificado com o uso de Design de classes Por (Kaio Vinicius, Fábio Júnio, Otávio Lima, Otávio Resende)
 * @version 2019.11.13
 */
public class Tesouro extends Item {
    /**
     * Construtor da Classe Tesouro
     */
    public Tesouro(){
        super();
    }
    
    /**
     * Método resposável por restornar a mensagem de tesouro encontrado
     * @return String com a Descrição da Mensagem do Encontro do Tesouro.
     */
    public String getMensagem(){
        return "Você encontrou o tesouro!!!";
    }
}