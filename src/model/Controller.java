package model;

import java.util.Random;

public class Controller {
    private Equipo[] equipos;
    private Arbitro[] arbitros;
    private final int CANTIDAD_EQUIPOS = 4;
    private final int CANTIDAD_ARBITROS = 4;
    private int contadorEquipos = 0;
    private Random random = new Random();

    /**
     * Constructor de la clase Controller para inicializar variables globales.
     * @pre No se requieren precondiciones específicas.
     * @post Se crea una instancia de Controller con un arreglo de personas vacío.
     */
    public Controller() {
        equipos = new Equipo[CANTIDAD_EQUIPOS];
        arbitros = new Arbitro[CANTIDAD_ARBITROS];
        arbitros[0] = new ArbitroPrincipal("Arbitro Principal 1", 40);
        arbitros[1] = new ArbitroPrincipal("Arbitro Principal 2", 42);
        arbitros[2] = new JuezDeLinea("Juez de Linea 1", 35);
        arbitros[3] = new JuezDeLinea("Juez de Linea 2", 36);
    }

    /**
     * Método para crear un equipo.
     * @param nombreEquipo Nombre del equipo.
     * @return true si el equipo fue creado exitosamente, false si ya se alcanzó el
     *         límite de equipos.
     */
    public boolean crearEquipo(String nombreEquipo) {
        if (contadorEquipos < CANTIDAD_EQUIPOS) {
            equipos[contadorEquipos] = new Equipo(nombreEquipo);
            contadorEquipos++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo para agregar un jugador a un equipo específico.
     * @param equipoIndex Índice del equipo al que se agregará el jugador.
     * @param jugador     El jugador a agregar.
     * @return true si el jugador fue agregado exitosamente, false si el índice de
     *         equipo es inválido.
     */
    public boolean agregarJugadorAEquipo(int equipoIndex, JugadorHockey jugador) {
        if (equipoIndex >= 0 && equipoIndex < contadorEquipos) {
            equipos[equipoIndex].agregarJugador(jugador);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo para verificar si hay suficientes jugadores en un equipo.
     * Deben haber al menos tres jugadores para poder generar un simulador de gol. 
     * @param equipo El equipo a verificar.
     * @return true si hay al menos tres jugadores activos en el equipo.
     */
    private boolean haySuficientesJugadores(Equipo equipo) {
        int jugadoresActivos = 0;
        for (JugadorHockey jugador : equipo.getJugadores()) {
            if (jugador != null) {
                jugadoresActivos++;
            }
        }
        return jugadoresActivos >= 3; 
    }

    /**
     * Método para crear el fixture de los partidos entre equipos.
     * @return un String con los partidos generados.
     */
    public String fixture() {
        String fixture = "";
        int equipo1 = random.nextInt(contadorEquipos);
        int equipo2;
        do {
            equipo2 = random.nextInt(contadorEquipos);
        } while (equipo2 == equipo1);

        fixture += "Partido 1: Equipo " + equipos[equipo1].getNombreEquipo() + " vs Equipo " + equipos[equipo2].getNombreEquipo();
        fixture += "\n";

        do {
            equipo1 = random.nextInt(contadorEquipos);
            equipo2 = random.nextInt(contadorEquipos);
        } while (equipo2 == equipo1);

        fixture += "Partido 2: Equipo " + equipos[equipo1].getNombreEquipo() + " vs Equipo " + equipos[equipo2].getNombreEquipo();
        return fixture;
    }

    public void simularJugada() {
        if (contadorEquipos == 0) {
            System.out.println("No hay equipos creados. Por favor, crea al menos un equipo primero.");
            return;
        }

        Equipo equipo = equipos[0]; 
        if (!haySuficientesJugadores(equipo)) {
            System.out.println("No hay suficientes jugadores en el equipo para simular una jugada de gol. Necesitas al menos 3 jugadores.");
            return;
        }

        JugadorHockey[] jugadores = equipo.getJugadores();
        int pasesRealizados = 0;
        JugadorHockey jugadorActual = jugadores[random.nextInt(jugadores.length)];
        JugadorHockey siguienteJugador;

        while (pasesRealizados < 5) {
            siguienteJugador = jugadores[random.nextInt(jugadores.length)];
            if (siguienteJugador != null && siguienteJugador != jugadorActual) {
                System.out.println(jugadorActual.pasarDisco(siguienteJugador));
                desplazarArbitro();
                jugadorActual = siguienteJugador;
                pasesRealizados++;
            }
        }
        System.out.println("Jugador numero " + jugadorActual.getNumero() + " entra el disco en la red. ¡Gol!");
    }

    private void desplazarArbitro() {
        Arbitro arbitro = arbitros[random.nextInt(arbitros.length)];
        arbitro.desplazarse();
    }
}
