package auxiliar;

public enum TipoUsuario {
	
	CoordenacaoTSI(0),
	ProfessorEstagio(1);
	
	private final int value;
	
    private TipoUsuario(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
