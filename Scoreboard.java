import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scoreboard extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel mines;
	JButton reset;
	private Timer time;
	private int minesLeft;
	Scoreboard(Paramaters paramaters){
		minesLeft=paramaters.mines;
		int width = paramaters.screenSize.width;
		this.setSize(width, paramaters.cellSize);
		this.setBackground(paramaters.openColor);
		this.setLayout(null);
		
		mines = new JLabel(""+minesLeft);
		mines.setFont(new Font("Helvetica",Font.PLAIN,20));
		mines.setForeground(Color.red);
		mines.setLocation((width/2)-paramaters.cellSize*2, 0);
		mines.setSize(paramaters.cellSize*2, paramaters.cellSize);
		mines.setOpaque(true);
		mines.setBackground(paramaters.openColor);
		this.add(mines);
		
		reset = new JButton(":)");
		reset.setFont(new Font("Helvetica",Font.PLAIN,20));
		reset.setForeground(Color.red);
		reset.setLocation(width/2, 0);
		reset.setSize(paramaters.cellSize, paramaters.cellSize);
		reset.setOpaque(true);
		reset.setBackground(paramaters.openColor);
		reset.setBorder(null);
		this.add(reset);
		
		time = new Timer(paramaters);
		this.add(time);
	}
	public void update(){
		mines.setText(""+minesLeft);
	}
	public void reduceScore() {
		minesLeft--;
		update();
	}
	public void increaseScore() {
		minesLeft++;
		update();
	}
	public long getTime() {
		return time.getTime();
	}
}
