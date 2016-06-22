import java.util.LinkedList;

public class Map {
	private Block[] block;
	private Ball ball;
	private View view;
	private Player player;
	private LinkedList<Bullet> bullets;
	// ブロックの行数
	private static final int NUM_BLOCK_ROW = 20;
	// ブロックの列数
	private static final int NUM_BLOCK_COL = 36;
	// ブロック
	private static final int NUM_BLOCK = NUM_BLOCK_ROW * NUM_BLOCK_COL;

	public Map(Ball ball, Player player) {
		super();
		this.block = new Block[NUM_BLOCK];
		this.ball = ball;
		this.player = player;
		this.bullets = new LinkedList<Bullet>();
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
		for (Bullet b : bullets) {
			view.drawString("◎◎", b.getX(), b.getY());
		}
	}

	public void Collision() {
		int x, y;
		x = ball.getX();
		y = ball.getY();

		for (int i = 0; i < NUM_BLOCK; i++) {
			if (block[i].isDeleted() == true)
				continue;
			else if ((block[i].getX() == x / 2 - 2) && block[i].getY() == y - 3) {
				block[i].Delete();
			}
		}

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
		// if (tx != 0) {
		// ka = ty / tx;
		// se = y - ka * x;
		// }
		int tex = bx, tey;
		for (double i = 0; i < 1; i += 1 / (Math.abs(tx) + Math.abs(ty))) {
			// System.out.println(i);
			if (tx != 0)
				tex = bx + (int) Math.round(tx * i);
			if (tx == 0)
				tex = bx;
			tey = by + (int) Math.round(ty * i);
			for (int j = 0; j < NUM_BLOCK; j++) {
				if (block[j].isDeleted() == true)
					continue;
				else if ((block[j].getX() == (tex / 2) - 2) && block[j].getY() == tey - 3) {
					block[j].Delete();
				}
			}
		}
	}

	public void bupdate() {
		LinkedList<Bullet> delbul = new LinkedList<Bullet>();
		// TODO 自動生成されたメソッド・スタブ
		for (Bullet b : bullets) {
			if (b.getY() <= 0) {
				delbul.add(b);
				continue;
			}
			if (BlockBulletCollision(b) == true) {
				delbul.add(b);
				continue;
			}
			if (BallBulletCollision(b) == true) {
				delbul.add(b);
				continue;
			}
			b.update();
		}
		bullets.removeAll(delbul);
	}

	private boolean BallBulletCollision(Bullet b) {
		// TODO 自動生成されたメソッド・スタブ
		if ((ball.getX() == b.getX() || ball.getX() == b.getX() + 1) && ball.getY() == b.getY()) {
			ball.hitbul();
			return true;
		}
		return false;
	}

	private boolean BlockBulletCollision(Bullet b) {
		for (int i = 0; i < NUM_BLOCK; i++) {
			if (block[i].isDeleted() == true)
				continue;
			else if (block[i].getX() == (b.getX() / 2) - 2 && block[i].getY() == b.getY() - 3) {
				block[i].Delete();
				return true;
			}
		}
		return false;
	}

	public void Makebullet(int x, int y) {
		if (bullets.size() < 3) {
			bullets.add(new Bullet(x, y));
		}
	}

	public int bulletsize() {
		// TODO 自動生成されたメソッド・スタブ
		return bullets.size();
	}

}
