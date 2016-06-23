import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;

public class Model {
	private View view;
	private Player player;
	private Controller controller;
	private Status status;

	private int mode, stage;
	private int timech = 0;
	private Ball ball;
	private Map map;
	private int select;
	private static final int TITLE = 1;
	private static final int CLEAR = 8;
	private static final int GAMEOVER = 9;
	private static final int NORMAL = 2;
	private static final int HARD = 3;
	private static final int STAGE = 4;
	private static final int SHOTING = 2;
	private static final int VALIA = 1;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	private static final int START = 7;
	private static final int TIME = 3;
	private static final int ZANKi = 4;
	// private long time = 0, time2;
	private int times;
	private int time;
	private int timin;

	public Model() {
		mode = TITLE;
		select = 0;
		stage = 5;
		time = 0;
		times = 0;
		timin = 0;
		this.player = new Player(this);
		ball = new Ball(2, player.getY() - 10, player);
		map = new Map(ball);
		this.view = new View(this, player, ball, map);
		this.controller = new Controller(this);
		this.status = new Status(view,0);
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

				if (timin == 20) {
					status.count();
					timin = 0;
				}
				map.bupdate();
				timech++;
				timin++;
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
			if (map.checkclear() == true)
				mode = CLEAR;
			view.update();
			status.draw();
			break;
		case TITLE:
			if (event.equals("UP")) {
				if (select == 0)
					select = 2;
				else
					select--;
			}
			if (event.equals("DOWN")) {
				if (select == 2)
					select = 0;
				else
					select++;
			}
			if (event.equals("z")) {
				if (select == 0 || select == 1) {
					mode = STAGE;
					player.setSta(status);
					map.setStatus(status);
				}
			}
			view.title(select);
			break;
		case CLEAR:
			if (event.equals("z")) {
				stage++;
				status.scoreplus(CLEAR);
				status.scoreplus(TIME);
				status.scoreplus(ZANKi);
				int a=status.getScore();
				this.player = new Player(this);
				ball = new Ball(2, player.getY() - 10, player);
				map = new Map(ball);
				this.view = new View(this, player, ball, map);
				this.status = new Status(view,a);
				player.setSta(status);
				map.setStatus(status);
				mode = STAGE;
				if(stage==6)mode =TITLE;
			}
			view.clears(status.getScore(), status.getZanki(), stage);
			break;
		case STAGE:
			if (event.equals("TIME_ELAPSED")) {
				if (times == 15) {
					times = 0;
					status.setMinute(3);
					status.setTime(0);
					mode = START;
					map.stageread(stage);
				}
				times++;
			}
			view.stage(stage);
			break;
		case START:
			if (event.equals("TIME_ELAPSED")) {
				if (times == 15) {
					times = 0;
					mode = NORMAL;
				}
				times++;
			}
			view.start();
			break;
		case GAMEOVER:
			if (event.equals("r")) {
				mode = TITLE;
				this.player = new Player(this);
				ball = new Ball(2, player.getY() - 10, player);
				map = new Map(ball);
				this.view = new View(this, player, ball, map);
				this.status = new Status(view,0);
				stage=1;
			}
			view.gameover();
			break;
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

	public void gameover() {

		// TODO 自動生成されたメソッド・スタブ

		mode = GAMEOVER;
	}

}
