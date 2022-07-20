import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
public class Main  {
	JPanel mineFeild;
	public JFrame frame = new JFrame();
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Main(int cellSize) {
		//number of cells is a function of device size
		int width = screenSize.width/cellSize;
		int height = (screenSize.height-28)/cellSize;
		
		//number of mines is a percent of total cells
		double density=0.10;
		int mines = (int) (width*height*density);
		
		
		mineFeild = new MineFeild(width, height, cellSize, mines);
		frame.add(mineFeild);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MineSweeper");
		frame.setPreferredSize(new Dimension(cellSize*width, cellSize*height+28));
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new Main(30);
	}
}