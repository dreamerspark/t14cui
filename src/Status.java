
public class Status {
	private static final int ZANKI = 3;
	int zanki;
	int score;
	int time;
	int minute;
	private View view;

	public Status(View view) {
		zanki = 3;
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
