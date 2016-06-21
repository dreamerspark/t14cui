public class Bullet {
int x;
int y;
public Bullet(int x,int y) {
	this.x = x;
	this.y = y;
}

public void update(){
	if(y>0)y--;
}

public int getX() {
	return x;
}

public int getY() {
	return y;
}

}
