import java.util.*;

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
}