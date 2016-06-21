import java.util.LinkedList;

public class Player {
	private int x;
	private int y;
	private int left;
	private int Width;
	private Model model;
	private static final int NO_ACTION = 0;
	private static final int VALIA = 1;
	private static final int SHOTING = 2;
	private static final int DAMAGE = 9;
	private static final int LEFT = 3;
	private static final int RIGHT = 4;

	public Player(Model model) {
		this.x = 40;
		this.y = 41;
		this.left = 3;
		this.model = model;
		this.Width = 76;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void update(int a) {
		if (a == LEFT) {
			if (x - 4 > 0)
			x-=2;
	//x--;
		}
		if (a == RIGHT) {
			if (x + 4 < Width)
				x+=2;
		//x++;
		}
		if (a == DAMAGE) {
			if (left == 0)
				model.gameover();
			left--;
		}

	}
	
	public void collision(Ball ball) {

	}

}