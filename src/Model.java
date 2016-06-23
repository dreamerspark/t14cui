import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Model {
	private View view;
	private Player player;
	private Controller controller;
	private Status status;
	private int[] ranking = new int[10];
	private int mode, stage;
	private int timech = 0;
	private Ball ball;
	private Map map;
	private int select;
	private static final int TITLE = 1;
	private static final int CLEAR = 8;
	private static final int GAMEOVER = 9;
	private static final int NORMAL = 2;
	// private static final int HARD = 3;
	private static final int STAGE = 4;
	private static final int SHOTING = 2;
	private static final int VALIA = 1;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;
	private static final int START = 7;
	private static final int TIME = 3;
	private static final int ZANKi = 4;
	private static final int READ = 0;
	private static final int WRITE = 1;
	private static final int RANKING = 6;
	// private long time = 0, time2;
	private int times;
	private int timin;

	public Model() {
		mode = TITLE;
		select = 0;
		stage = 1;
		times = 0;
		timin = 0;
		this.player = new Player(this);
		ball = new Ball(2, player.getY() - 10, player);
		map = new Map(ball);
		this.view = new View(player, ball, map);
		this.controller = new Controller(this);
		this.status = new Status(view, 0);
		view.title(select);
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
				System.out.println("check");

				if (select == 0)
					select = 2;
				else
					select--;
				view.title(select);
			}
			if (event.equals("DOWN")) {
				if (select == 2)
					select = 0;
				else
					select++;
				view.title(select);
			}
			if (event.equals("z")) {
				if (select == 0 || select == 1) {
					mode = STAGE;
					player.setSta(status);
					map.setStatus(status);
				}
				if (select == 2) {
					mode = RANKING;
				}
			}

			break;
		case CLEAR:
			if (event.equals("z")) {
				stage++;
				status.scoreplus(CLEAR);
				status.scoreplus(TIME);
				status.scoreplus(ZANKi);
				int a = status.getScore();
				this.player = new Player(this);
				ball = new Ball(2, player.getY() - 10, player);
				map = new Map(ball);
				this.view = new View(player, ball, map);
				this.status = new Status(view, a);
				player.setSta(status);
				map.setStatus(status);
				mode = STAGE;
				if (stage == 6) {
					mode = TITLE;
					rank(READ);
					rank(status.getScore());
					rank(WRITE);
				}
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
				rank(READ);
				rank(status.getScore());
				rank(WRITE);
				this.player = new Player(this);
				this.ball = new Ball(2, player.getY() - 10, player);
				this.map = new Map(ball);
				this.view = new View(player, ball, map);
				this.status = new Status(view, 0);
				stage = 1;
				view.title(select);
				break;
			}
			view.gameover();
			break;
		case RANKING:
			if (event.equals("z")) {
				mode = TITLE;
				view.title(select);
				break;
			}
			view.ranking(ranking);
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

	public void rank(int a) {
		int b = 0;
		if (a == READ) {
			try {
				// test.txtからファイルを読み込んでテキストエリアに表示
				BufferedReader br = new BufferedReader(new FileReader("rank.txt")); // ファイルを開く
				String line;
				while ((line = br.readLine()) != null) { // 1行ずつ読み込む
					// 読み込んだ1行をテキストエリアに表示
					ranking[b] = Integer.parseInt(line);
					b++;
				}
				br.close(); // ファイルを閉じる
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else if (a == WRITE) {
			try {
				// test.txtにテキストエリアの内容を書き込む
				PrintWriter bw = new PrintWriter(new FileWriter("rank.txt")); // ファイルを開く
				for (b = 0; b < 5; b++)
					bw.println(ranking[b] + "");
				bw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			for (int i = 0; i < 5; i++) {
				if (ranking[i] > a)
					continue;
				else {
					int temp = a;
					a = ranking[i];
					ranking[i] = temp;
				}
			}
		}
	}

}
