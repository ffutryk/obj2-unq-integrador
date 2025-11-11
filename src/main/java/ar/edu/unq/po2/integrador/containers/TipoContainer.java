package ar.edu.unq.po2.integrador.containers;

public enum TipoContainer {
	DRY("Dry"),
	REFEER("Refeer"),
	TANQUE("Tanque");
	
    private final String texto;

    TipoContainer(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }
}