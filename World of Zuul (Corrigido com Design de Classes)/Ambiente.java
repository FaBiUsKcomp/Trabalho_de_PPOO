/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 * 
 * @author Modificado com o uso de Design de classes Por (Kaio Vinicius, Fábio Júnio, Otávio Lima, Otávio Resende)
 * @version 2019.11.13
 */

import java.util.HashMap;
public class Ambiente 
{
    public String descricao;
    private HashMap<String, Ambiente> saidas;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao) 
    {
        this.descricao = descricao;
        saidas = new HashMap<String, Ambiente>();
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     * @param direcao Direção para onde jogador deseja ir.
     * @param ambiente Ambiente desejado.
     */
    public void ajustarSaidas(String direcao, Ambiente ambiente) 
    {
        saidas.put(direcao, ambiente);
    }

    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao()
    {
        return descricao;
    }

    public String getSaidas(){
        String direcoes = "";
        for(String d : saidas.keySet()){
            direcoes = direcoes + d + " ";
        }
        return direcoes;
    }
}
