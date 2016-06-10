public class Ball {
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int times;
	private int mode;
	private Player player;
	private static final double GRAVITY = 0.5;
	private static final int EASY = 1;
	private static final int NORMAL = 2;
	private static final int HARD = 3;
	private static int WIDTH = 80;
	private static int HEIGHT = 43;

	public Ball(int x, int y, Player player) {
		this.x = x;
		this.y = y;
		this.vx = 1;
		this.vy = 1;
		this.player = player;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setspeed(int x, int y) {
		this.vx = x;
		this.vy = y;
	}

	public void boundX() {
		vx = -vx;
	}

	public void boundY() {
		vy = -vy;
	}

	public void boundXY() {
		vx = -vx;
		vy = -vy;
	}

	public void collision() {
		if (this.x >= player.getX() - 4 && this.x <= player.getX() + 4) {
			int ch;
			if (this.y == player.getY()) {
				vy = -vy;
				ch = this.x - player.getX()-1;
				vx+=ch;
				/*
				 * int ch = this.x - player.getX(); vx += ch;
				 */
			}
		}

	}

	public void update() {
		collision();
		switch (EASY) {
		case EASY:
			move();
			break;
		case NORMAL:
			gravity();
			move();
			break;
		case HARD:
		}
	}

	private void gravity() {
		// TODO 自動生成されたメソッド・スタブ
		vy += 1;
	}

	private void move() {
		x += vx;
		y += vy;
		// 左右の壁にぶつかった場合にバウンド
		if (x < 0 || x > WIDTH) {
			boundX();
		}

		// 上の壁にぶつかった場合にバウンド
		if (y < 0 || y > HEIGHT) {
			boundY();
		}
	}
}
