import java.util.*;

/**
 * Essa eh a classe Comodo da aplicacao "Caça ao Tesouro".
 *  "Caça ao Tesouro "se consiste em um jogo simples em que existe uma casa mal 
 *  assombrada com vários comodos em que o objetivo é encontrar
 *  um tesouro em um dos comodos da mesma, uma vez que as portas 
 *  podem estar emperradas e em que o jogador possui jogadas limitadas 
 *  para cada entrada nas portas.
 *  Esse jogo é baseado na Implementação do Jogo "World of Zuul"
 * 
 * Classe Reponsável por Controlar os Comodos da casa
 * 
 * @author Modificado com o uso de Design de classes Por (Kaio Vinicius, Fábio Júnio, Otávio Lima, Otávio Resende)
 * @version 2019.11.13
 */

public class Comodo 
{
    public String nome;
    public String descricao;
    private HashMap<String, Comodo> saidas;
    private Item item;

    /**
     * Cria um Comodo com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * @param nome O nome do comodo.
     */
    public Comodo(String nome) 
    {
        this.nome = nome;
        descricao = "Você está no(a) " + nome + " da casa mal assombrada.";  
        saidas = new HashMap<String, Comodo>();
        item = null;
    }

    /**
     * Define as saidas do Comodo. Cada direcao ou leva a um
     * outro Comodo ou eh null (nenhuma saida para la).
     * @param nomeComodo Direção para onde jogador deseja ir.
     * @param comodo desejado.
     */
    public void ajustarSaidas(String nomeComodo, Comodo comodo) 
    {
        saidas.put(nomeComodo, comodo);
    }

    /**
     * @return A descricao do Comodo.
     */
    public String getDescricao()
    {
        return descricao;
    }

    /**
     * Método que retorna a lista de Saidas
     * 
     * @return String com todas as saídas de um comodo da casa.
     */
    public String getSaidas(){
        String direcoes = "";
        for(String d : saidas.keySet()){
            direcoes = direcoes + d + ", ";
        }
        return direcoes;
    }

    /**
     * Método que retorna um comodo da casa
     * 
     * @param nome String que serve de chave para o retorno do Método
     * @return Comodo resferente a chave do parâmetro.
     */
    public Comodo getComodo(String nome){
        return (Comodo)saidas.get(nome);
    }
    
    /**
     * Método para retorna o nome do comodo
     * 
     * @return String que se refere ao nome do Comodo da casa.
     */
    public String getNome(){
		return nome;
    }
    
    /**
     * Método que configura o estado da Porta do comodo da casa
     * 
     * @return Booleano - estado atual da Porta (0/1)
     */
    public boolean porta(){
        Random r = new Random();
        return r.nextBoolean();
    }

    /**
     * Método que seta o Item do comodo
     * 
     * @param i Item que é encontrado durante a execução do Jogo
     */
    public final void setItem(Item i){
        item = i;
    }

    /**
     * Método que retorna o Item do Comodo
     * 
     * @return Item que está no comodo
     */
    public Item getItem(){
        return item;
    }

    /**
     * Método que Retorna o Hash de Comodos da casa.
     * 
     * @return Hash de todos os comodos da casa
     */
    public HashMap<String, Comodo> getComodos(){
        return saidas;
    }
}
