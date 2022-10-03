import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeaderBoard {
	List<Leader> leaders;
	
	
	LeaderBoard(){
		this.leaders = getLeaders();
		sortLeaders();
		displayLeaders(leaders);
	}
	private List<Leader> getLeaders(){
		List<Leader> leaders = new ArrayList<Leader>();
		Scanner in = null;
		try {
			in = new Scanner(new File("LeaderBoard.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(in.hasNextLine()) {
			leaders.add(parse(in.nextLine().split(":")));
		}
		return leaders;
		
	}
	private void displayLeaders(List<Leader> leaders) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MineSweeper_Leaderboard");
		panel.setBackground(Color.black);
		
		for(int i=0; i<leaders.size(); i++) {
			String entry = i+".   "+leaders.get(i).name+"  "+leaders.get(i).time/60000+":"+(leaders.get(i).time%60000)/1000;
			JLabel label = new JLabel(entry);
			label.setFont(new Font("Sans Serif", Font.PLAIN, 20));
			label.setBorder(BorderFactory.createMatteBorder(10,30,10,10, Color.black));
			label.setBackground(Color.black);
			label.setForeground(Color.white);
			panel.add(label);
		}
		frame.setPreferredSize(new Dimension(500, 600));
		frame.setVisible(true);
		frame.add(panel);
		frame.pack();
		frame.setBackground(Color.black);
	}
	public static Leader parse(String[] data) {
        String name = data[0];
        long time = Long.parseLong(data[1]);
        return new Leader(name, time);
    }
	public void sortLeaders() {
	  List<Leader> leaders = this.leaders;
	  LeaderComparator comparator = new LeaderComparator();
	  Collections.sort(leaders, comparator); 
	}
}
