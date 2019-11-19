public class Porta {
    private Comodo destino;
    private Boolean estado;

    public Porta (Comodo destino){
        this.destino = destino;
        estado = true;
    }

    public String getSaida(){
        return destino.getNome();
    }

    public String getEstado(){
        if (estado){
            return "Funcionando corretamente";
        } else {
            return "Emperrada";
        }
    }

    public Comodo getDestino(){
        return destino;
    }

}
