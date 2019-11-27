public class Tesouro extends Item {
    
    private boolean encontrado;

    public Tesouro(){
        super();
        encontrado = false;
    }

    public void setEncontrado(boolean e){
        encontrado = e;
    }

    public boolean getEncontrado(){
        return encontrado;
    }
}