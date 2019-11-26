import java.util.HashMap;
import java.util.Random;

public class ChaveMestra extends Item {
    private int vidaUtil;
    private boolean encontrado;

    public ChaveMestra(HashMap comodos){
        super(comodos);
        Random r = new Random();
        vidaUtil = r.nextInt(12) + 1;
        encontrado = false;
    }

    /**
     * @return the vidaUtil
     */
    public int getVidaUtil() {
        return vidaUtil;
    }

    /**
     * @param encontrado the encontrado to set
     */
    public final void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public final boolean getEncontrado(){
        return encontrado;
    }

}