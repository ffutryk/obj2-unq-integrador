package ar.edu.unq.po2.integrador;

public class Pair<T1, T2> {
	
    private T1 primerComponente;
    private T2 segundaComponente;

    public Pair(T1 unValor, T2 otroValor) {
        primerComponente = unValor;
        segundaComponente = otroValor;
    }

    public T1 first() {
        return primerComponente;
    }
    
    public T2 second() {
    	return segundaComponente;
    }
}
