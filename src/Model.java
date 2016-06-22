import java.io.IOException;
import java.util.LinkedList;

public class Model {
	private View view;
	private Player player;
	private Controller controller;

	private int mode;
	private int timech = 0;
	private Ball ball;
	private Map map;
	private int select;
	private static final int TITLE = 1;
	private static final int CLEAR = 8;
	private static final int GAMEOVER = 9;
	private static final int NORMAL = 2;
	private static final int HARD = 3;
	private static final int SHOTING = 2;
	private static final int VALIA = 1;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	private long time = 0, time2;

	public Model() {
		mode = TITLE;
		select = 0;
		this.player = new Player(this);
		ball = new Ball(player.getX(), player.getY() - 2, player);
		map = new Map(ball, player);
		this.view = new View(this, player, ball, map);
		this.controller = new Controller(this);
	}

	public synchronized void process(String event) {
		switch (mode) {
		case NORMAL:
			if (event.equals("TIME_ELAPSED")) {
				if (timech == 2) {
					ball.update();
					timech = 0;
					map.Beforecol();
				}
				map.bupdate();
				timech++;
			}

			// キーボード分岐
			if (event.equals("z")) {
				map.Makebullet(player.getX(), player.getY());
				player.update(SHOTING);
				System.out.println(map.bulletsize());
			}
			if (event.equals("x")) {
				player.update(VALIA);
			}
			if (event.equals("LEFT"))
				player.update(LEFT);
			else if (event.equals("RIGHT"))
				player.update(RIGHT);

			view.update();
			break;
		case TITLE:
			if(event.equals("UP")){
				if(select==0)select=2;
				else select--;
			}
			if(event.equals("DOWN")){
				if(select==2)select=0;
				else select++;
			}
			view.title(select);
		}
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

	public boolean gameover() {

		// TODO 自動生成されたメソッド・スタブ
		while (true)
			view.gameover();
	}

}
