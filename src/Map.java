
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
		int x, y,pos;
		x = ball.getX();
		y = ball.getY();

		for (int i = 0; i < NUM_BLOCK; i++) {
			if (block[i].isDeleted() == true)
				continue;
			else if ((block[i].getX() == x / 2 - 2) && block[i].getY() == y - 3) {
				block[i].Delete();
				pos=i;
			}
		}
	//	block[pos];
		
		
	}
}
