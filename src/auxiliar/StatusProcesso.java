package auxiliar;

public enum StatusProcesso {
	
	Aberto(0),
	ChegouNaCoordenacao(1),
	EnviadoParaOrientador(2),
	RecebidoDoOrientador(3),
	EnviadoParaCoordEstagio(4),
	RecebidoDaCoordEstagio(5),
	EnviadoParaCCA(6);
	
	private final int value;
	
    private StatusProcesso(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
