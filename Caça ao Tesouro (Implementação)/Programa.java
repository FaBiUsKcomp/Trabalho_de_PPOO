public class Programa {

	public static void main(String[] args) {
		Jogo jogo = new Jogo();
		Tela interfaceGrafica = new Tela(jogo);
		interfaceGrafica.exibirTela();
	}

}
