package brains;

import edu.unlam.snake.brain.Brain;
import game.GameDifficulty;
import game.GameMode;
import game.GameStudent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Brain> players = Arrays.asList(
                new MyBrain()
        );

        GameStudent.start(GameMode.NORMAL, 3, 2, GameDifficulty.NORMAL, new ArrayList<>(), (Brain[]) players.toArray());
        // Pueden probar multiples copias de su Brain o varias copias distintas enviado
        // un array en vez del objeto
    }
}

/**
 * Comandos:
 * 1 -> Aumenta movimientos / seg
 * 2 -> Disminuye movimientos / seg
 * 3 -> 20 movimientos / seg
 * 4 -> 4 movimientos / seg
 * 5 -> 1 movimiento / seg
 * P -> Pausa
 * R -> Siguiente ronda
 * + -> Aumentar zoom
 * - -> Disminuir zoom
 * <p>
 * Nota:
 * El juego empieza pausado (P)
 * Para salir de pausa deberán tocar (3), (4) o (5)
 */
