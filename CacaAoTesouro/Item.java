import java.util.*;

/**
 * Essa eh a classe Item da aplicacao "Caça ao Tesouro".
 *  "Caça ao Tesouro "se consiste em um jogo simples em que existe uma casa mal 
 *  assombrada com vários comodos em que o objetivo é encontrar
 *  um tesouro em um dos comodos da mesma, uma vez que as portas 
 *  podem estar emperradas e em que o jogador possui jogadas limitadas 
 *  para cada entrada nas portas.
 *  Esse jogo é baseado na Implementação do Jogo "World of Zuul"
 * 
 * Classe Reponsável por Controlar os Itens do Jogo (Dica, Tesouro, Chave)
 * 
 * @author Modificado com o uso de Design de classes Por (Kaio Vinicius, Fábio Júnio, Otávio Lima, Otávio Resende)
 * @version 2019.11.13
 */

public class Item {
    private int local;
    private boolean encontrado;
    
    /**
     * Construtor da Classe Item.
     */
    public Item(){
        local = 0;
        encontrado = false;
    }

    /**
     * Método que define o item como encontrado.
     * 
     * @param e booleano que seta o atributo encontrado como true/false.
     */
    public void setEncontrado(boolean e){
        encontrado = e;
    }

    /**
     * Método que retorna o estado do atributo Encontrado.
     * 
     * @return Booleano referente ao atributo encontrado.
     */
    public boolean getEncontrado(){
        return encontrado;
    }

    /**
     * Método resposável por espalhar o item nos comodos da Casa.
     * 
     * @param comodos hash de Comodos para ser usado no sorteio do método.
     * @return Inteiro que se refere ao sorteio do método.
     */
    private int espalhar(HashMap<Integer, Comodo> comodos){
        Random r = new Random();
        Integer sorteio = r.nextInt(comodos.size()) + 1;

        Comodo c = (Comodo)comodos.get(sorteio);
        while (c.getItem() != null){
            sorteio = r.nextInt(comodos.size()+ 1);
            c = (Comodo)comodos.get(sorteio);
        }

        c.setItem(this);

        return sorteio;

    }

    /**
     * Método resposável por definir a posição do Item no mapa da casa.
     * 
     * @param comodos Hash de Comodos para servir na hora do sorteio.
     * @return inteiro referente ao local do Item no mapa.
     */
    public int setLocal(HashMap<Integer, Comodo> comodos){
        local = espalhar(comodos);
        return local;
    }

    /**
     * Método que retorna o local no Item
     * 
     * @return Inteiro referente ao local do Item na casa.
     */
    public int getLocal(){
        return local;
    }

}