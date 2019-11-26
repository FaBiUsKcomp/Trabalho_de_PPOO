import java.util.*;

public class Tesouro extends Item{
    boolean encontrado;

    public Tesouro(HashMap comodos){
        super(comodos);
        encontrado = false;
    }

    public void setEncontrado(boolean e){
        encontrado = e;
    }

    public boolean getEncontrado(){
        return encontrado;
    }

}