import java.util.Random;

public class Porta {
    private Comodo destino;
    private Boolean estado;

    public Porta (Comodo destino){
        this.destino = destino;
        estado = new Random(2);
    }

    public String getSaida(){
        return destino.getNome();
    }

    public Boolean getEstado(){
        return estado;
    }

    public Boolean setEstado(){
        estado = new Random(2);
    }

    public String getMensagemPorta(){
        return getEstado() ? "Funcionando corretamente" : "Emperrada";
    }

    public Comodo getDestino(){
        return destino;
    }
}
