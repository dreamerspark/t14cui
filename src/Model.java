import java.io.IOException;
import java.util.LinkedList;

public class Model {
	private View view;
	private Player player;
	private Controller controller;

	private int mode;
	private int timech = 0;
	private LinkedList<Bullet> bullets;
	private Ball ball;
	private Map map;
	private static final int EASY = 1;
	private static final int NORMAL = 2;
	private static final int HARD = 3;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;

	public Model() {
		this.player = new Player(this);
		ball = new Ball(player.getX(), player.getY() - 2, player);
		map = new Map(ball, player);
		this.view = new View(this, player, ball, map);
		this.controller = new Controller(this);

	}

	public synchronized void process(String event) {
		if (event.equals("TIME_ELAPSED")) {
			if (timech == 2) {
				ball.update();
				timech = 0;
				map.Beforecol();
			}
			map.bupdate();
			timech++;
		}
		// for (Bullet b : bullets)
		// b.update();
		if (event.equals("UP")) {
			map.Makebullet(player.getX(), player.getY());
			System.out.println(map.bulletsize());
		} else if (event.equals("LEFT"))
			player.update(LEFT);
		else if (event.equals("RIGHT"))
			player.update(RIGHT);

		view.update();
	}

	public Bullet getBullet(int i) {
		int chi = 0;
		for (Bullet b : bullets) {
			if (chi == i)
				return b;
			chi++;
		}
		System.exit(1);
		return null;
	}

	public int NumOfBullets() {
		return bullets.size();
	}

	// private LinkedList<Bullet> bullets;
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		Model model = new Model();
		model.run();
	}

	private void run() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		controller.run();
	}

	public void shot(int x) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public static boolean gameover() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
