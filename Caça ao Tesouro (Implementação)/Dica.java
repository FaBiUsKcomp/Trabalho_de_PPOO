import java.util.*;

/**
 * Classe Reponsável por Controlar as Dicas do Jogo
 * 
 * @author Modificado com o uso de Design de classes Por (Kaio Vinicius, Fábio Júnio, Otávio Lima, Otávio Resende)
 * @version 2019.11.13
 */

public class Dica extends Item {
    private String descricao;

    public Dica(String d){
        super();
        descricao = d;
    }

    public void setDescricao(HashMap<Integer, Comodo> comodos){
        Comodo c = (Comodo) comodos.get(setLocal(comodos));
        descricao = descricao + " " + c.getNome();
    }

    public void setDescricao(Comodo comodo){
        HashMap<String, Comodo> h = comodo.getComodos();
        Random r = new Random();
        int sorteio = r.nextInt(h.size());
        ArrayList<Comodo> c = new ArrayList<Comodo>(h.values());
        descricao = descricao + " " + (c.get(sorteio).getNome());
    }

    public String getDescricao(){
        return "<html><h2>" + descricao + "</h2></html>";
    }
}