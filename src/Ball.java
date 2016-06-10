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
		if (this.x >= player.getX() - 2 && this.x <= player.getX() + 2) {
			if (this.y == player.getY()) {
				vy = -vy;
				int ch = this.x - player.getX();
				vx += ch;
			}
		}

	}

	public void update() {
		collision();
		switch (EASY) {
		case EASY:
			
			break;
		case NORMAL:	
		case HARD:
		}
	}
}
