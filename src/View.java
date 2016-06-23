public class View {

	private static final int VALIA = 1;
	char screen[][];
	private static int WIDTH = 80;
	private static int HEIGHT = 43;

	private Player player;
	private Ball ball;
	private Model model;
	private Map map;
	private Moji moji;

	public View() {
		this(HEIGHT, WIDTH);
	}

	public View(Model model, Player player, Ball ball, Map map) {
		this();
		// this.model = model;
		this.player = player;
		this.ball = ball;
		this.map = map;
		this.moji = new Moji(this);
		map.setView(this);
	}

	public View(int a, int b) {
		this.screen = new char[a][b];
		clear();
	}

	public View(int a, int b, Model model) {
		this(a, b);
		// this.model = model;
	}

	public void clear() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				screen[i][j] = ' ';
			}
		}
	}

	public void put(char c, int x, int y) {
		if (y >= 0 && y < screen.length)
			if (x >= 0 && x < screen[y].length)
				screen[y][x] = c;
	}

	public void drawString(String s, int x, int y) {
		for (int i = 0; i < s.length(); i++) {
			this.put(s.charAt(i), x + i, y);
		}
	}

	void drawRect(char c, int x, int y, int w, int h) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (i == 0 || i == h - 1 || j == 0 || j == w - 1)
					this.put(c, x + j, y + i);
			}
		}
	}

	void drawRects(char c, int x, int y, int w, int h) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (i == 0 || i == h - 1 || j == 0 || j == w - 1)
					this.put(c, x + j, y + i);
				else
					this.put(' ', x + j, y + i);
			}
		}
	}

	public void paint() {
		for (int i = 0; i < HEIGHT; i++) {
			System.out.println(screen[i]);
		}
	}

	public void update() {
		// TODO 自動生成されたメソッド・スタブ
		clear();
		map.draw();
		if (player.getMode() != VALIA) {
			drawString("■■■■■", player.getX() - 3, player.getY());
		}
		if (player.getMode() == VALIA) {
			drawString("#■■■■■ #", player.getX() - 4, player.getY());
			drawString("########", player.getX() - 4, player.getY() - 1);
		}
		drawRect('#', 0, 1, WIDTH, HEIGHT - 3);
		// put('*', player.getX(), player.getY());
		put('●', ball.getX(), ball.getY());
		drawString(Integer.toString(player.getX()), WIDTH - 4, HEIGHT - 5);
		drawString(Integer.toString(ball.getX()), WIDTH - 4, HEIGHT - 4);

		// for (int i = 0; i < model.NumOfBullets(); i++) {
		// put('*', model.getBullet(i).getX(), model.getBullet(i).getY());
		// }

		paint();
	}

	public void gameover() {
		moji.gameover();
	}

	public void title(int select) {
		// TODO 自動生成されたメソッド・スタブ
		moji.title(select);
	}

	public void clears(int i, int j, int stage) {
		// TODO 自動生成されたメソッド・スタブ
		moji.clear(i, j, stage);
	}

	public void stage(int select) {
		// TODO 自動生成されたメソッド・スタブ
		moji.stage(select);
	}

	public void start() {
		// TODO 自動生成されたメソッド・スタブ
		moji.start();
	}

}
