import java.util.HashMap;

public class Item {
    private int local;

    public Item(HashMap comodos){
        local = espalhar(comodos);
    }

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

}