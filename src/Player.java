public class Player {
	private int x;
	private int y;
	private int left;
	private int Width;
	private Model model;
	private Status status;
	private int mode = 0;
	private static final int NO_ACTION = 0;
	private static final int VALIA = 1;
	private static final int SHOTING = 2;
	private static final int DAMAGE = 9;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;

	public Player(Model model) {
		this.x = 40;
		this.y = 39;
		this.left = 3;
		this.model = model;
		this.Width = 76;
		this.mode = NO_ACTION;
	}

	public void setSta(Status status) {
		this.status = status;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getMode() {
		return mode;
	}

	public void update(int a) {
		if (a == LEFT) {
			if (x - 4 > 0)
				x -= 2;
			mode = LEFT;
			// x--;
		}
		if (a == RIGHT) {
			if (x + 4 < Width)
				x += 2;
			mode = RIGHT;
			// x++;
		}
		if (a == VALIA) {
			mode = VALIA;
		}
		if (a == DAMAGE && mode != VALIA) {
			if (status.zankidown() <= 0)
				model.gameover();
			x = 40;
			mode = VALIA;
		}
		if (a == DAMAGE && mode == VALIA) {
			mode = NO_ACTION;
		}
		if (a == SHOTING)
			mode = SHOTING;

	}

	public void damage() {
		// TODO 自動生成されたメソッド・スタブ
		update(DAMAGE);
	}

}