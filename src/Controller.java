import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.Timer;

public class Controller implements ActionListener {
	private final static int DELAY = 50; // msec
	private Model model;
	private Timer timer;

	public Controller(Model m) {
		model = m;
		timer = new Timer(DELAY, this);
	}

	public void run() throws IOException {
		timer.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String line = null;
		while ((line = reader.readLine()) != null)
			model.process(line);
	}

	public void actionPerformed(ActionEvent e) {
		model.process("TIME_ELAPSED");
	}

}
