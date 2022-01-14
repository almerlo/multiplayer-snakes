package brains;

import java.util.List;

import edu.unlam.snake.brain.Brain;
import edu.unlam.snake.engine.Direction;
import edu.unlam.snake.engine.Point;

public class MyBrain extends Brain {

	public MyBrain() {
		super("IdLoom1 | IdLoom2 | IdLoom3");
	}

	public Direction getDirection(Point head, Direction previous) {
		//Frutas del mapa
		List<Point> fruits = info.getFruits();

		//Cuerpo de la serpiente
		List<Point> snake = info.getSnake();

		//Serpientes enemigas
		List<List<Point>> enemies = info.getEnemies();

		//Obstaculos del mapa
		List<Point> obstacles = info.getObstacles();

		// Point head = snake.get(0);
		Point myNextHead = head.clone();
		myNextHead.moveTo(previous);

		// Se pueden utilizar System.out para debuggear
		// Deben ser quitados para la entrega
		// System.out.println(myHead);
		// System.out.println(myNextHead);

		for (Point obstacle : obstacles) {
			if (myNextHead.equals(obstacle))
				return previous.turnRight();
		}

		return previous;
	}
}
