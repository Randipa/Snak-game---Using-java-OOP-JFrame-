package Snake;

import javax.swing.JFrame;

public class Game_Frame extends JFrame {

	public Game_Frame() {
		Game_Panal panal =new Game_Panal();
		this.add(panal);
		this.setTitle("Snake");
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
