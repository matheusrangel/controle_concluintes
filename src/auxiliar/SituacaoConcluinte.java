package auxiliar;

public enum SituacaoConcluinte {
	Aberto(0),
	Finalizado(1),
	Desistente(2);
	
	private final int value;
	
    private SituacaoConcluinte(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
