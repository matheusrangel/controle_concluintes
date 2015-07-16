package auxiliar;

public enum SituacaoConcluinte {
	Novo(0),
	Aberto(1),
	Finalizado(2),
	Desistente(3);
	
	private final int value;
	
    private SituacaoConcluinte(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    
}
