
public class Map {
	private Block[] block;

	// ブロックの行数
	private static final int NUM_BLOCK_ROW = 10;
	// ブロックの列数
	private static final int NUM_BLOCK_COL = 7;
	// ブロック数
	private static final int NUM_BLOCK = NUM_BLOCK_ROW * NUM_BLOCK_COL;

	public Map() {
		super();
		this.block = new Block[NUM_BLOCK];

		for (int i = 0; i < NUM_BLOCK_ROW; i++) {
			for (int j = 0; j < NUM_BLOCK_COL; j++) {
				int x = j;
				int y = i;
				block[i * NUM_BLOCK_COL + j] = new Block(x, y);
			}
		}

	}

	
	public void Collision(){
		for(int i=0;i<NUM_BLOCK;i++){
			if(block[i].isDeleted())	
				continue;
			
		}
	}
}
