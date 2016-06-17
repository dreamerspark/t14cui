
public class Map {
	private Block[] block;
	private Ball ball;
	private View view;
	// ブロックの行数
	private static final int NUM_BLOCK_ROW = 20;
	// ブロックの列数
	private static final int NUM_BLOCK_COL = 38;
	// ブロック
	private static final int NUM_BLOCK = NUM_BLOCK_ROW * NUM_BLOCK_COL;

	public Map(Ball ball) {
		super();
		this.block = new Block[NUM_BLOCK];
		this.ball = ball;
		for (int i = 0; i < NUM_BLOCK_ROW; i++) {
			for (int j = 0; j < NUM_BLOCK_COL; j++) {
				int x = j;
				int y = i;
				block[i * NUM_BLOCK_COL + j] = new Block(x, y);
			}
		}

	}

	public void setView(View view) {
		this.view = view;
	}

	public void draw() {
		int dx, dy;
		dx = 4;
		dy = 3;
		for (int i = 0; i < NUM_BLOCK_ROW; i++) {
			for (int j = 0; j < NUM_BLOCK_COL; j++) {
				if (block[i * NUM_BLOCK_COL + j].isDeleted() == false) {
					if (block[i * NUM_BLOCK_COL + j].getMode() == 0) {
						view.drawString("■■", dx + j * 2, dy + i);
					}
				}
			}
		}
	}

	public void Collision() {
		int x, y, pos;
		x = ball.getX();
		y = ball.getY();

		for (int i = 0; i < NUM_BLOCK; i++) {
			if (block[i].isDeleted() == true)
				continue;
			else if ((block[i].getX() == x / 2 - 2) && block[i].getY() == y - 3) {
				block[i].Delete();
				pos = i;
			}
		}
		// block[pos];

	}

	/*
	 * public void Beforecol() { int x = ball.getX() , y = ball.getY() ; int bx
	 * = (ball.getX() - ball.getVx()) , by = (ball.getY() - ball.getVy()) ;
	 * //前のx,y座標 int tx, ty, ka, se;//傾きと切片 int temp;//一時的なyの座標 tx = x - bx; ty
	 * = y - by; ka = ty / tx; se = y - ka * x; for (int j = bx; j < x; j++) {
	 * temp = j * ka + se; for (int i = 0; i < NUM_BLOCK; i++) { if
	 * (block[i].isDeleted() == true) continue; else if ((block[i].getX() ==
	 * (j/2)-2) && block[i].getY() == temp-3) { block[i].Delete(); } }
	 * 
	 * }
	 * 
	 * }
	 */

	public void Beforecol() {
		int x, y, bx, by;
		double tx, ty, ka, se;
		double range;
		x = ball.getX();
		y = ball.getY();
		bx = ball.getX() - ball.getVx();
		by = ball.getY() - ball.getVy();
		tx = x - bx;
		ty = y - by;
		range = Math.sqrt(tx * tx + ty * ty);
		if (tx != 0) {
			ka = ty / tx;
			se = y - ka * x;
		}
		int tex = bx, tey;
		if (range > 0) {
			for (double i = 0; i < range; i += range / (Math.abs(tx) + Math.abs(ty))) {
				System.out.println(i);
				if (tx != 0)
					tex = (int) (bx + tx * i);
				if (tx == 0)
					tex = bx;
				tey = (int) (by + ty * i);
				for (int j = 0; j < NUM_BLOCK; j++) {
					if (block[j].isDeleted() == true)
						continue;
					else if ((block[j].getX() == (tex / 2) - 2) && block[j].getY() == tey - 3) {
						block[j].Delete();
					}
				}
			}
		} else {
			for (double i = 0; i > range; i += range / (tx + ty)) {
				System.out.println(i);
				if (tx != 0)
					tex = (int) (bx + tx * i);
				if (tx == 0)
					tex = bx;
				tey = (int) (by + ty * i);
				for (int j = 0; j < NUM_BLOCK; j++) {
					if (block[j].isDeleted() == true)
						continue;
					else if ((block[j].getX() == (tex / 2) - 2) && block[j].getY() == tey - 3) {
						block[j].Delete();
					}
				}
			}
		}
	}
}
