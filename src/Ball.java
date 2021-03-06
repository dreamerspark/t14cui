public class Ball {
	private int x;
	private int y;
	private int vx;
	private int vy;
	private int times;
	private Player player;
	private static final int EASY = 1;
	private static final int NORMAL = 2;
	private static final int HARD = 3;
	private static int WIDTH = 80;
	private static int HEIGHT = 41;

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

	public int getY() {
		return y;
	}

	public int getVx() {
		return vx;
	}

	public int getVy() {
		return vy;
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
				player.damage();
				vy = -vy - 3;
				ch = (this.x - player.getX() - 1)/2;
				vx += ch;
				if (vx > 10)
					vx = 10;
				else if (vx < -10)
					vx = -10;
				/*
				 * int ch = this.x - player.getX(); vx += ch;
				 */
			}
		}

	}

	public void update() {

		collision();
		switch (NORMAL) {
		case EASY:
			move();
			break;
		case NORMAL:
			if (times == 4) {
				gravity();
				times = 0;
			}
			move();
			break;
		case HARD:
		}
		times++;

	}

	private void gravity() {
		// TODO 自動生成されたメソッド・スタブ
		vy += 1;
	}

	private void move() {
		x += vx;
		y += vy;
		// 左右の壁にぶつかった場合にバウンド
		if (x < 0 || x > WIDTH - 1) {
			if (vx > 8)
				vx--;
			if (vx < -8)
				vx++;
			boundX();
			if (x < 0)
				x = 0;
			if (x > WIDTH - 1)
				x = WIDTH - 1;
		}
		// 上の壁にぶつかった場合にバウンド
		if (y < 1 || y > HEIGHT - 2) {
			if (vy < 0 && y > 1)
				vy -= 3;
			else
				boundY();

			if (y > HEIGHT - 1)
				vy += 1;
		}
		if (y > HEIGHT - 2)
			y = HEIGHT - 2;

	}

	public void hitbul() {
		// TODO 自動生成されたメソッド・スタブ
		vy -= 3;
		boundX();
	}
}
