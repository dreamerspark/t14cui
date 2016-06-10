public class Block {
private int x,y;
private boolean isDeleted;

public Block(int x, int y){
	this.x=x;
	this.y=y;
	isDeleted=false;
}

public Block(int x,int y,boolean d){
	this.x=x;
	this.y=y;
	isDeleted=d;
}

}
