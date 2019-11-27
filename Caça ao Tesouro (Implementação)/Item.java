import java.util.*;

public abstract class Item {
    private int local;
    private boolean encontrado;

    public Item(){
        local = 0;
        encontrado = false;
    }

    public void setEncontrado(boolean e){
        encontrado = e;
    }

    public boolean getEncontrado(){
        return encontrado;
    }

    private int espalhar(HashMap comodos){
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

    public int setLocal(HashMap comodos){
        local = espalhar(comodos);
        return local;
    }

    public int getLocal(){
        return local;
    }

}