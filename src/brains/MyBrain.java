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
		List<Point> fruits = info.getFruits();
		List<Point> snake = info.getSnake();
		List<List<Point>> enemies = info.getEnemies();
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
