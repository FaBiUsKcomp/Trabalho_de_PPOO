import java.util.HashMap;
import java.util.Random;
public class Dica extends Item{
    private String descricao;
    private boolean encontrado;

    public Dica(String d, HashMap comodos){
        super(comodos);
        descricao = d;
        encontrado = false;
    }

    @Override
    public int espalhar(HashMap comodos){
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

    public String getDescricao(){
        return descricao;
    }

    

}