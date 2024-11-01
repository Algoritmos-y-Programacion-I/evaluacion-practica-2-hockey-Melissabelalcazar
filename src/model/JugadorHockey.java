package model;

public class JugadorHockey extends Persona implements IDesplazarseEnPistaConPalo {
    protected String nombre;
    protected int numero;
    protected Posicion posicion;

    public JugadorHockey(String nombre, int edad, int numero, Posicion posicion) {
        super(nombre, edad);
        this.numero = numero;
        this.posicion = posicion;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String desplazarseConPalo() {
        if (posicion != Posicion.PORTERO) {
            return "Jugador numero " + numero + " se desplaza en la pista con el palo.";
        } else {
            return "Jugador numero " + numero + " es el portero y no se desplaza más allá de media pista.";
        }
    }

    // Método para pasar el disco a otro jugador
    public String pasarDisco(JugadorHockey receptor) {
        return "Jugador numero " + this.numero + " se la pasa a Jugador numero " + receptor.getNumero();
    }
}
