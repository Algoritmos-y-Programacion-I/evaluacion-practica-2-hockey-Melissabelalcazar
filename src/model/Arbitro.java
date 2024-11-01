package model;

public abstract class Arbitro extends Persona implements IDesplazarseEnPistaSinPalo {
    public Arbitro(String nombre, int edad) {
        super(nombre, edad);
    }

    @Override
    public String toString() {
        return nombre + " se desplaza en la pista como árbitro.";
    }

    @Override
    public void desplazarse() {
        System.out.println(nombre + "se desplaza en la pista.");
    }
}
