import java.awt.Color;
//import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MineFeild extends JPanel {
	private static final long serialVersionUID = 1L;
	
	int width;
	int height;
	int cellSize;
	int mines;	
	int openCells=0;
	int flags=0;
	boolean notLost=true;
	
	//this is the main premise of the program
		/*the whole thing is just a grid of JButtons 
		  wrapped in a custom class to 
		  store critical data associated with each cell*/
	Cell[][] cells;
	public class Cell extends JButton {
		private static final long serialVersionUID = 1L;
		int val=0;
		int x;
		int y;
		boolean isOpen=false;
		boolean isBomb=false;
		Cell(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
		
	MineFeild(int width, int height, int cellSize, int mines){
		this.setLayout(null);
		this.height=height;
		this.width=width;
		this.mines=mines;
		this.cellSize=cellSize;
		cells = new Cell[height][width];
		
		//Initialize each cell in the array
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++){
				
				Cell cell=new Cell(j,i);
				cells[i][j] = cell;
				cell.setLocation(j * cellSize, i*cellSize);
				cell.setSize(cellSize, cellSize);
				this.add(cell);
				cell.setOpaque(true);
				cell.setBackground(Color.darkGray);
				cell.setBorder(BorderFactory.createLineBorder(Color.black,1));
				
				//handle left clicks
				cell.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
					Cell cell = (Cell)e.getSource();
					if(cell.isBomb) {
						JOptionPane.showMessageDialog(null, "player lost");
						notLost=false;
					}
					else if(notLost)
						open(cell);
					}
				});
				
				//handle right clicks
				cell.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						Cell cell = (Cell)e.getSource();
						if (e.getButton() == 3) {
							cell.setText("F");
							cell.setOpaque(true);
							cell.setBorder(null);
							cell.setBackground(Color.red);
							flags++;
						}
					}
				});
			}
		}
		//place the set number of bombs in random cells
		while(mines>0) {
			int x=(int)(Math.random()*height);
			int y=(int)(Math.random()*width);
			Cell cell = cells[x][y];
			if(cell.val!=9) {
				cell.val=9;
				cell.isBomb=true;
				mines--;
			}
		}
		//set each cell's value
		//***i feel like this can be optimized, but I suck at algo, and it works well, even at massive grid sizes***
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++){
				Cell cell = cells[i][j];
				if(!cell.isBomb)
					cell.val=countAdjacentMines(cell);
			}
		}
	}
	//the outermost if statements are to avoid an index out of bounds error
	//again, I feel like there is a better way to do this, but i suck at coding
	private int countAdjacentMines(Cell cell) {
		int x=cell.x;
		int y=cell.y;
		int count=0;
		if(x>0) 
			if(cells[y][x-1].isBomb)
				count++;
			
		if(x<width-1) 
			if(cells[y][x+1].isBomb)
				count++;
			
		if(y>0) 
			if(cells[y-1][x].isBomb)
				count++;
			
		if(y<height-1) 
			if(cells[y+1][x].isBomb)
				count++;
			
		if(y>0&&x>0) 
			if(cells[y-1][x-1].isBomb)
				count++;
			
		if(x<width-1&&y>0) 
			if(cells[y-1][x+1].isBomb)
				count++;
			
		if(y<height-1&&x>0) 
			if(cells[y+1][x-1].isBomb)
				count++;
			
		if(y<height-1&&x<width-1) 
			if(cells[y+1][x+1].isBomb)
				count++;
			
		return count;
	}
	
	
	public void open(Cell cell){
		openCells++;
		if(openCells==(height*width)-mines)
			JOptionPane.showMessageDialog(null, "player won!");
		if(cell.val!=0) {
			cell.setText(""+cell.val);
		}
		cell.setBorder(null);
		cell.setForeground(Color.white);
		cell.setBackground(Color.black);
		cell.isOpen=true;
		//Basically recurses across all neighboring 0 cells
		if(cell.val==0){
			openAdjacentCells(cell);	
		}
	}
	public void openAdjacentCells(Cell cell) {
		int x=cell.x;
		int y=cell.y;
		if(x>0) 
			if(!cells[y][x-1].isOpen)
				open(cells[y][x-1]);
			
		if(x<width-1) 
			if(!cells[y][x+1].isOpen)
				open(cells[y][x+1]);
			
		if(y>0) 
			if(!cells[y-1][x].isOpen)
				open(cells[y-1][x]);
			
		if(y<height-1) 
			if(!cells[y+1][x].isOpen)
				open(cells[y+1][x]);
			
		if(y>0&&x>0) 
			if(!cells[y-1][x-1].isOpen)
				open(cells[y-1][x-1]);
			
		if(x<width-1&&y>0) 
			if(!cells[y-1][x+1].isOpen)
				open(cells[y-1][x+1]);
			
		if(y<height-1&&x>0) 
			if(!cells[y+1][x-1].isOpen)
				open(cells[y+1][x-1]);
			
		if(y<height-1&&x<width-1) 
			if(!cells[y+1][x+1].isOpen)
				open(cells[y+1][x+1]);
	}
}
