package com.andresmontoya.app;

import com.andresmontoya.domain.Jugadores;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Guayabita {

    public static void main(String[] args) {


        String[] opcionesInicio = {"Jugar", "Ver instrucciones"};
        ImageIcon icono1 = new ImageIcon("src/main/java/com/andresmontoya/img/Guayabita.png",
                "Imagen de inicio");
        ArrayList<Jugadores> jugadores = new ArrayList<>();
        int cantidadJugadores;
        String nombreJugador;
        int dineroInicial;
        int pote = 0;
        int apuesta;
        int numeroImagen;
        int dado = 0;
        int dado2 = 0;
        String rutaImagen = null;
        Jugadores jugadorActual = null;


        int opcion = JOptionPane.showOptionDialog(null,
                "Bienvenido al juego de la guayabita, que desea hacer?",
                "Guayabita", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icono1,
                opcionesInicio, opcionesInicio[0]);

        if (opcion == 1) {
            String instrucciones = "Las instrucciones del juego son las siguientes:\n\n" +
                    "1. El juego se juega con un dado.\n" +
                    "2. Cada jugador debe apostar una cantidad de dinero.\n" +
                    "3. El jugador que saque el numero mas alto gana la apuesta.\n" +
                    "4. Si el jugador saca un 1 o un 6, su turno se salta.\n" +
                    "5. El juego termina cuando un jugador se queda sin dinero.\n";
            JOptionPane.showConfirmDialog(null, instrucciones);
            opcion = JOptionPane.showOptionDialog(null,
                    "Desea volver al menu principal?",
                    "Guayabita", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icono1,
                    opcionesInicio, opcionesInicio[0]);

            if (opcion == 0) {
            } else {
                System.exit(0);
            }
        }

            cantidadJugadores = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de jugadores: "));
            for (int i = 1; i <= cantidadJugadores; i++) {
                do {
                    nombreJugador = JOptionPane.showInputDialog(null, "Ingrese su nombre: Jugador" + i,
                            "Guayabita", JOptionPane.INFORMATION_MESSAGE);
                } while (!esValidoNombre(nombreJugador));

                do {
                    dineroInicial = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su dinero: Jugador " + nombreJugador,
                            "Guayabita", JOptionPane.INFORMATION_MESSAGE));
                } while (dineroInicial <= 0);
                jugadores.add(new Jugadores(nombreJugador, dineroInicial));
            }


        do {
            try {
                pote = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el pote del juego: "));
                if (pote <= 0 || pote < 500) {
                    throw new Exception("El pote debe ser mayor a 500 y no negativo");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } while (pote <= 0 || pote < 500);

        for (Jugadores jugador : jugadores) {
            jugador.setDinero(jugador.getDinero() - pote);
        }
        pote = pote * cantidadJugadores;


            boolean juegoTerminado = false;
            while (!juegoTerminado) {
                for (Jugadores jugador : jugadores) {
                    numeroImagen = new Random().nextInt(6) + 1;

                    switch (numeroImagen) {
                        case 1:
                            rutaImagen = "dado1.png";
                            dado = 1;
                            break;
                        case 2:
                            rutaImagen = "dado2.png";
                            dado = 2;
                            break;
                        case 3:
                            rutaImagen = "dado3.png";
                            dado = 3;
                            break;
                        case 4:
                            rutaImagen = "dado4.png";
                            dado = 4;
                            break;
                        case 5:
                            rutaImagen = "dado5.png";
                            dado = 5;
                            break;
                        case 6:
                            rutaImagen = "dado6.png";
                            dado = 6;
                            break;
                    }
                    ImageIcon imagen = new ImageIcon("src/main/java/com/andresmontoya/img/" + rutaImagen);
                    JOptionPane.showMessageDialog(null, "El jugador " + jugador.getNombre() + " saco este numero", "Guayabita", JOptionPane.INFORMATION_MESSAGE, imagen);

                    if (rutaImagen.equals("dado1.png") || rutaImagen.equals("dado6.png")){
                        JOptionPane.showMessageDialog(null, "El jugador: " + jugador.getNombre() + " pierde el turno");
                    }else {
                        apuesta = Integer.parseInt(JOptionPane.showInputDialog("Cuanto desea apostar?" + "Su presupuesto es de: " + jugador.getDinero() + " El pote esta en: " + pote));

                        int numeroImagen2 = new Random().nextInt(6) + 1;

                        switch (numeroImagen2) {
                            case 1:
                                rutaImagen = "dado1.png";
                                dado2 = 1;
                                break;
                            case 2:
                                rutaImagen = "dado2.png";
                                dado2 = 2;
                                break;
                            case 3:
                                rutaImagen = "dado3.png";
                                dado2 = 3;
                                break;
                            case 4:
                                rutaImagen = "dado4.png";
                                dado2 = 4;
                                break;
                            case 5:
                                rutaImagen = "dado5.png";
                                dado2 = 5;
                                break;
                            case 6:
                                rutaImagen = "dado6.png";
                                dado2 = 6;
                                break;
                        }
                        ImageIcon imagen2 = new ImageIcon("src/main/java/com/andresmontoya/img/" + rutaImagen);
                        JOptionPane.showMessageDialog(null, "El jugador " + jugador.getNombre() + " volvio a sacar este número", "Guayabita", JOptionPane.INFORMATION_MESSAGE, imagen2);
                        if (dado2 > dado){
                            jugador.setDinero(jugador.getDinero() + pote);
                            juegoTerminado = true;

                        }else{
                            pote += apuesta;
                            jugador.setDinero(jugador.getDinero() - apuesta);
                            JOptionPane.showMessageDialog(null, "El pote se lleva tu apuesta este es el nuevo pote: " + pote , "Guayabita", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    if (jugador.getDinero() == 0) {
                        juegoTerminado = true;
                        break;
                    }
                }
                if (pote == 0){
                    juegoTerminado = true;
                }
            }
            if (juegoTerminado) {
                int ganador = 0;
                for (Jugadores jugador : jugadores) {
                    if (jugador.getDinero() > jugadores.get(ganador).getDinero()) {
                        ganador = jugadores.indexOf(jugador);
                    }
                }

                JOptionPane.showMessageDialog(null, "El ganador es el jugador " + jugadores.get(ganador).getNombre());
            }
    }
    public static boolean esValidoNombre(String nombre) {
        return nombre != null && nombre.matches("[a-zA-Z ]+");
    }
}




