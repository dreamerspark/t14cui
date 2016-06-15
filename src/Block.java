public class Block {
private int x,y;
private boolean isDeleted;
private int mode;

public Block(int x, int y){
	this.x=x;
	this.y=y;
	isDeleted=false;
	mode=0;
}

public Block(int x,int y,boolean d){
	this.x=x+3;
	this.y=y+3;
	isDeleted=d;
	mode=0;
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

public boolean isDeleted() {
	// TODO 自動生成されたメソッド・スタブ
	return isDeleted;
}

public void Delete(){
	this.isDeleted=true;
}

}
