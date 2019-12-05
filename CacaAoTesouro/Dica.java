import java.util.*;

/**
 * Essa eh a classe Dica da aplicacao "Caça ao Tesouro".
 *  "Caça ao Tesouro "se consiste em um jogo simples em que existe uma casa mal 
 *  assombrada com vários comodos em que o objetivo é encontrar
 *  um tesouro em um dos comodos da mesma, uma vez que as portas 
 *  podem estar emperradas e em que o jogador possui jogadas limitadas 
 *  para cada entrada nas portas.
 *  Esse jogo é baseado na Implementação do Jogo "World of Zuul"
 * 
 * Classe Reponsável por Controlar as Dicas do Jogo
 * 
 * @author Modificado com o uso de Design de classes Por (Kaio Vinicius, Fábio Júnio, Otávio Lima, Otávio Resende)
 * @version 2019.11.13
 */

public class Dica extends Item {
    private String descricao;

    /**
     * Construtor da Classe Dica
     * 
     * @param d String que se refere a descrição da Dica.
     */
    public Dica(String d){
        super();
        descricao = d;
    }

    /**
     * Método reponsável por configurar a descrição da Dica 1
     * 
     * @param comodos Hash de Comodos para ser usado na hora do sorteio.
     */
    public void setDescricao(HashMap<Integer, Comodo> comodos){
        Comodo c = (Comodo) comodos.get(setLocal(comodos));
        descricao = descricao + " " + c.getNome();
    }

    /**
     * Método reponsável por configurar a descrição da Dica 2
     * 
     * @param comodos Hash de Comodos para ser usado na hora do sorteio.
     */
    public void setDescricao(Comodo comodo){
        HashMap<String, Comodo> h = comodo.getComodos();
        Random r = new Random();
        int sorteio = r.nextInt(h.size());
        ArrayList<Comodo> c = new ArrayList<Comodo>(h.values());
        descricao = descricao + " " + (c.get(sorteio).getNome());
    }

    /**
     * Método reponsável por retornar a descrição da Dica
     * 
     * @return String que se refere a descrição da Dica
     */
    public String getDescricao(){
        return "<html><h2>" + descricao + "</h2></html>";
    }
}