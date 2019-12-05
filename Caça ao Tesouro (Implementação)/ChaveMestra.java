import java.util.*;

/**
 * Classe Reponsável por Controlar a Chave mestra
 * 
 * @author Modificado com o uso de Design de classes Por (Kaio Vinicius, Fábio Júnio, Otávio Lima, Otávio Resende)
 * @version 2019.11.13
 */
public class ChaveMestra extends Item {
    private int vidaUtil;

    public ChaveMestra(){
        super();
        Random r = new Random();
        vidaUtil = r.nextInt(12) + 1;
    }

    /**
     * @return the vidaUtil
     */
    public int getVidaUtil() {
        return vidaUtil;
    }

    public void usarChave(){
        vidaUtil--;
    }
}