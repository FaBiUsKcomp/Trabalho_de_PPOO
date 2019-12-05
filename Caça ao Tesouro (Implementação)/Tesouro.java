/**
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