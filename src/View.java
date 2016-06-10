public class View {

	char screen[][];
	private static int WIDTH = 80;
	private static int HEIGHT = 43;

	private Model model;
	private Player player;
	private Ball ball;

	public View() {
		this(HEIGHT, WIDTH);
	}

	public View(Model model, Player player,Ball ball) {
		this();
		this.model = model;
		this.player = player;
		this.ball= ball;
	}

	public View(int a, int b) {
		this.screen = new char[a][b];
		clear();
	}

	public View(int a, int b, Model model) {
		this(a, b);
		this.model = model;
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

	public void paint() {
		for (int i = 0; i < HEIGHT; i++) {
			System.out.println(screen[i]);
		}
	}

	public void update() {
		// TODO 自動生成されたメソッド・スタブ
		clear();
		drawString("■■■■■", player.getX(), player.getY());
		drawRect('#', 0, 1, WIDTH, HEIGHT - 1);
		put('*',ball.getX(),ball.getY());

//		for (int i = 0; i < model.NumOfBullets(); i++) {
	//		put('*', model.getBullet(i).getX(), model.getBullet(i).getY());
		//}

		paint();
	}
}
