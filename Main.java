import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Main  {
	Paramaters paramaters;
	Scoreboard scoreboard;
	JPanel mineFeild;
	public JFrame frame = new JFrame();
	JPanel container;
	public Main() {
		container = new JPanel();
		container.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MineSweeper");
		paramaters = new Paramaters(frame);
		startGame();
	}
	public void startGame() {
		container = new JPanel();
		container.setLayout(new BorderLayout());
		paramaters = new Paramaters(frame);
		scoreboard = new Scoreboard(paramaters);
		scoreboard.reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("reset clicked");
				container.removeAll();
				startGame();
			}
		});
		container.add(scoreboard);
		mineFeild = new MineFeild(paramaters, scoreboard);
		container.add(mineFeild);
		frame.setPreferredSize(new Dimension(paramaters.cellSize*paramaters.width, paramaters.cellSize*paramaters.height+28+paramaters.cellSize));
		frame.setVisible(true);
		frame.add(container);
		frame.pack();
		frame.setBackground(Color.black);
	}
	public static void main(String[] args) {
		new Main();
	}
}