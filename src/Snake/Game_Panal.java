package Snake;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
 import java.awt.event.KeyEvent;

public class Game_Panal extends JPanel implements ActionListener{
//////////////////////////////////////////////////////////////////////////////////////////////	
	static final int SCREEN_WIDTH=600;
	static final int SCREEN_HEIGTH=600;
	static final int UNIT_SIZE=25;
	static final int GAME_UNITS=(SCREEN_WIDTH*SCREEN_HEIGTH)/UNIT_SIZE;
	static final int DELAY = 85;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts =6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
//////////////////////////////Constructor//////////////////////////////////////////////////////
	Game_Panal() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGTH));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MykeyAdapter());
		startGame();
	}
////////////////////////////////////////
	public void startGame() {
		newApple();
		running=true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
/////////////////////////////////////////	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
/////////////////////////////////////////	
	public void draw(Graphics g) {
		if(running) {
	/*		//=======Creat Gid part==============
		for(int i=0;i<SCREEN_HEIGTH/UNIT_SIZE;i++){
			g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGTH);
			g.drawLine(0,i*UNIT_SIZE,SCREEN_HEIGTH,i*UNIT_SIZE);
		}
		*/
			//====================================
		g.setColor(Color.green);
		g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
		for (int i = 0; i < bodyParts; i++) {
			if(i==0) {
				g.setColor(Color.red);
				g.fillRect(x[i], y[i],UNIT_SIZE,UNIT_SIZE);
			}
			else {
				g.setColor(new Color(139,0,0));
				g.fillRect(x[i], y[i],UNIT_SIZE,UNIT_SIZE);
			}
		}
		g.setColor(Color.yellow);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metrics = getFontMetrics(getFont());
		g.drawString("Score:  "+applesEaten,(SCREEN_WIDTH -metrics.stringWidth("Score:  "+applesEaten))/3,g.getFont().getSize());
	}else {
		gameOver(g);
	}
	}
	//////////////////////////////////////////////////////////////////////////////
	public void move() {
		for (int i = bodyParts; i > 0; i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		switch(direction) {
		case 'U':
			y[0]= y[0]-UNIT_SIZE;
			break;
		case 'D':
			y[0]= y[0]+UNIT_SIZE;
			break;
		case 'L':
			x[0]= x[0]-UNIT_SIZE;
			break;
		case 'R':
			x[0]= x[0]+UNIT_SIZE;
			break;
		}
	}
	/////////////////////////////////////////////////////////////////////////
	public void checkApple() {
		if( (x[0] == appleX)&&(y[0]==appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
			
		}
	}
	////////////////////////////////////////////////////////////////////////
	public void checkCollistion() {
	
		for (int i = bodyParts; i > 0; i--) {
			if( (x[0] == x[i])&&(y[0]==y[i])) {
				running = false;
				
			}
		}
		//Left Boder touch 
		if(x[0]<0) {
			running = false;
		}
		//Right Boder touch
		if(x[0]>SCREEN_WIDTH) {
			running = false;
		}
		//UP Boder touch
		if(y[0]<0) {
			running = false;
		}
		//Bottom touch
		if(y[0]>SCREEN_HEIGTH) {
			running = false;
		}
		if(!running) {
			timer.stop();
		}
	}
	///////////////////////////////////////////////////////////////////
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGTH/UNIT_SIZE))*UNIT_SIZE;
	}
	//////////////////////////////////////////////////
	public void gameOver(Graphics g) {
		g.setColor(Color.yellow);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metrics = getFontMetrics(getFont());
		g.drawString("Score:  "+applesEaten,(SCREEN_WIDTH -metrics.stringWidth("Score:  "+applesEaten))/3,g.getFont().getSize());
		
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metrics2 = getFontMetrics(getFont());
		g.drawString("Game Over",(SCREEN_WIDTH -metrics2.stringWidth("Game Over"))/5,SCREEN_HEIGTH/2);
	}
///////////////////////////Event Handling part///////////////////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(running) {
			move();
			checkApple();
			checkCollistion();
		}
		repaint();
	}
	public class MykeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed (KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction !='R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction !='L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction !='D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction !='U') {
					direction = 'D';
				}
				break;
			
			
			}
		}
	}


}

