
public class Status {
	//残機MAXの設定
	private static final int ZANKI = 3;
	//スコア用の検出定数
	private static final int BLOCK = 1;
	private static final int CLEAR = 2;
	private static final int TIME = 3;
	private static final int ZANKi = 4;
	int zanki;//現在の残機
	int score;//スコア
	int time;//秒単位の時間
	int minute;//分単位の時間
	private View view;

	public Status(View view) {
		zanki = ZANKI;
		score = 0;
		time = 0;
		minute = 0;
		this.view = view;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public void scoreplus(int a) {
		if(a==BLOCK)score+=100;
		if(a==CLEAR)score+=10000;
		if(a==TIME)score+=time*100+minute*6000;
		if(a==ZANKi)score+=zanki*1000;
	}

	public void count() {
		if (time == 0) {
			time = 59;
			minute--;
		} else
			time--;
	}

	public void draw() {
		String min = String.format("%1$02d", minute);
		String sec = String.format("%1$02d", time);
		String sco = String.format("%1$06d", score);
		String zan = "";
		int x = 0, y = 41;
		for (int i = 0; i < ZANKI; i++) {
			if (zanki > i) {
				zan += "◯";
			} else {
				zan += "●";
			}
		}

		view.drawRect('#', x, y, 80, 3);
		view.drawString("PLAYER " + zan + " # SCORE " + sco + " # TIME " + min + ":" + sec, x + 10, 42);
		view.paint();
	}

	public int zankidown() {
		// TODO 自動生成されたメソッド・スタブ
		zanki--;
		return zanki;
	}
}
