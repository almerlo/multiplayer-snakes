package brains;

import java.util.ArrayList;
import java.util.Arrays;
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

		//Cuerpos de las otras serpientes
		List<List<Point>> enemies = info.getEnemies();

		//Lista de obstaculos
		List<Point> obstacles = info.getObstacles();

//		List<Point> fruits = getInfo().getFruits();
//		List<List<Point>> snakes = getInfo().getSnakes();
//		List<Point> obstacles = getInfo().getObstacles();

		Point nextPoint = resolveNextPoint(head, previous);

		boolean chocaConBordes = obstacles.contains(nextPoint);

		List<Direction> directions = resolvePosibleDirections(previous);

		List<Direction> posibles = new ArrayList<>();

		//evitamos paredes
		for(Direction direction : directions) {

			nextPoint = resolveNextPoint(head, direction);
			chocaConBordes = obstacles.contains(nextPoint);
			if(!chocaConBordes) {
				posibles.add(direction);
			}
		}

		for(List<Point> serpientes : enemies) {
			Point first = serpientes.get(0);
			if(first.equals(head)) {
				continue;
			}
			serpientes.add(new Point(first.getX()+1,first.getY()));
			serpientes.add(new Point(first.getX()-1,first.getY()));
			serpientes.add(new Point(first.getX(),first.getY()+1));
			serpientes.add(new Point(first.getX(),first.getY()-1));
		}

		//evitamos otras serpietnes
		List<Direction> posiblesSinSerpientes = new ArrayList<>();
		for(Direction direction : posibles) {
			nextPoint = resolveNextPoint(head, direction);
			boolean chocaConSerpiente = false;
			for(List<Point> serpientes : enemies) {
				chocaConSerpiente = serpientes.contains(nextPoint);
				if(chocaConSerpiente) {
					break;
				}
			}

			if(!chocaConSerpiente) {
				posiblesSinSerpientes.add(direction);
			}
		}


		Direction dir = previous;
		double mostNear = 99999999;
		for(Direction direction : posiblesSinSerpientes) {
			nextPoint = resolveNextPoint(head, direction);
			double dist;
			for(Point fruit : fruits) {
				dist = distance(nextPoint, fruit);
				if (dist < mostNear){
					mostNear = dist;
					dir = direction;
				}
			}
		}

		return dir;
	}

	private double distance (Point a , Point b){
		return Math.sqrt((b.getX()- a.getX())*(b.getX()- a.getX())+(a.getY() - b.getY())*(a.getY() - b.getY()));
	}

	private List<Direction> resolvePosibleDirections(Direction previous) {
		List<Direction> directions = new ArrayList<Direction>(Arrays.asList(Direction.LEFT, Direction.RIGHT, Direction.DOWN, Direction.UP));

		switch (previous) {
			case LEFT:
				directions.remove(Direction.RIGHT);
				break;
			case RIGHT:
				directions.remove(Direction.LEFT);
				break;
			case UP:
				directions.remove(Direction.DOWN);
				break;
			case DOWN:
				directions.remove(Direction.UP);
			default:
				break;
		}
		return directions;
	}

	private Point resolveNextPoint(Point head, Direction previous) {
		if(previous.equals(Direction.LEFT)) {
			return new Point(head.getX() - 1, head.getY());
		}

		if(previous.equals(Direction.RIGHT)) {
			return new Point(head.getX() + 1, head.getY());
		}

		if(previous.equals(Direction.UP)) {
			return new Point(head.getX(), head.getY() + 1);
		}

		if(previous.equals(Direction.DOWN)) {
			return new Point(head.getX(), head.getY() - 1);
		}

		// TODO Auto-generated method stub
		return null;
	}

}
