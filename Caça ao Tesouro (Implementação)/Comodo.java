import java.util.HashMap;
public class Comodo 
{
    public String nome;
    public String descricao;
    private HashMap<String, Porta> saidas;

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
        saidas = new HashMap<String, Porta>();
    }

    /**
     * Define as saidas do Comodo. Cada direcao ou leva a um
     * outro Comodo ou eh null (nenhuma saida para la).
     * @param direcao Direção para onde jogador deseja ir.
     * @param comodo desejado.
     */
    public void ajustarSaidas(String direcao, Porta comodo) 
    {
        saidas.put(direcao, comodo);
    }

    /**
     * @return A descricao do Comodo.
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

    public Comodo getComodo(String nome){
        Porta aux = saidas.get(nome);

        return aux.getDestino();
    }
    
    public String getNome(){
		return nome;
	}
}
