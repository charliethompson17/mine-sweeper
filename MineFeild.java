import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MineFeild extends JPanel {
	Paramaters paramaters;
	Scoreboard scoreboard;
	private static final long serialVersionUID = 1L;
	int openCells=0;
	int flags=0;
	boolean notLost=true;
	
	//this is the main premise of the program
		/*the whole thing is just a grid of JButtons 
		  wrapped in a custom class to 
		  store critical data associated with each cell*/
	Cell[][] cells;
		
	MineFeild(Paramaters paramaters, Scoreboard scoreboard){
		this.scoreboard=scoreboard;
		this.paramaters=paramaters;
		this.setLayout(null);
		cells = new Cell[paramaters.height][paramaters.width];
		
		//Initialize each cell in the array
		for(int i=0; i<paramaters.height; i++) {
			for(int j=0; j<paramaters.width; j++){
				
				Cell cell=new Cell(j,i);
				cells[i][j] = cell;
				cell.setLocation(j * paramaters.cellSize, i*paramaters.cellSize+30);
				cell.setSize(paramaters.cellSize, paramaters.cellSize);
				this.add(cell);
				cell.setOpaque(true);
				cell.setBackground(paramaters.closedColor);
				cell.setBorder(BorderFactory.createLineBorder(paramaters.closedBorderColor,1));
				
				//handle left clicks
				cell.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(notLost) {
							Cell cell = (Cell)e.getSource();
							if(!cell.flaged) {
								if(notLost)
									open(cell);
							}
						}
					}
				});
				
				//handle right clicks
				cell.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if(notLost) {
							Cell cell = (Cell)e.getSource();
							if(!cell.flaged&&!cell.isOpen) {
								if (e.getButton() == 3) {
									cell.setText("F");
									cell.setOpaque(true);
									cell.setBorder(null);
									cell.setBackground(paramaters.flagColor);
									cell.flaged=true;
									flags++;
									scoreboard.reduceScore();
								}
							}
							else if(cell.flaged) {
								if (e.getButton() == 3) {
									cell.flaged=false;
									flags--;
									open(cell);
									scoreboard.increaseScore();
								}
							}
						}
					}
				});
			}
		}
		//place the set number of bombs in random cells
		while(paramaters.mines>0) {
			int x=(int)(Math.random()*paramaters.height);
			int y=(int)(Math.random()*paramaters.width);
			Cell cell = cells[x][y];
			if(cell.val!=9) {
				cell.val=9;
				cell.isBomb=true;
				paramaters.mines--;
			}
		}
		//set each cell's value
		//***i feel like this can be optimized, but it works well, even at massive grid sizes***
		for(int i=0; i<paramaters.height; i++) {
			for(int j=0; j<paramaters.width; j++){
				Cell cell = cells[i][j];
				if(!cell.isBomb)
					cell.val=countAdjacentMines(cell);
			}
		}
	}
	//the outermost if statements are to avoid an index out of bounds error
	//again, I feel like there is a better way to do this
	private int countAdjacentMines(Cell cell) {
		int x=cell.x;
		int y=cell.y;
		int count=0;
		if(x>0) 
			if(cells[y][x-1].isBomb)
				count++;
			
		if(x<paramaters.width-1) 
			if(cells[y][x+1].isBomb)
				count++;
			
		if(y>0) 
			if(cells[y-1][x].isBomb)
				count++;
			
		if(y<paramaters.height-1) 
			if(cells[y+1][x].isBomb)
				count++;
			
		if(y>0&&x>0) 
			if(cells[y-1][x-1].isBomb)
				count++;
			
		if(x<paramaters.width-1&&y>0) 
			if(cells[y-1][x+1].isBomb)
				count++;
			
		if(y<paramaters.height-1&&x>0) 
			if(cells[y+1][x-1].isBomb)
				count++;
			
		if(y<paramaters.height-1&&x<paramaters.width-1) 
			if(cells[y+1][x+1].isBomb)
				count++;
			
		return count;
	}
	
	
	private void open(Cell cell){
		if(cell.isBomb) {
			JOptionPane.showMessageDialog(null, "player lost");
			notLost=false;
		}
		openCells++;
		if(openCells==(paramaters.height*paramaters.width)-paramaters.mines) {
			playerWon();
		}
		if(cell.val!=0)
			cell.setText(""+cell.val);
		else
			cell.setText("");
		cell.setBorder(null);
		cell.setForeground(paramaters.numberColor);
		cell.setBackground(paramaters.openColor);
		cell.isOpen=true;
		//Basically recurses across all neighboring 0 cells
		if(cell.val==0){
			openAdjacentCells(cell);	
		}
	}
	private void openAdjacentCells(Cell cell) {
		int x=cell.x;
		int y=cell.y;
		if(x>0) 
			if(!cells[y][x-1].isOpen)
				open(cells[y][x-1]);
			
		if(x<paramaters.width-1) 
			if(!cells[y][x+1].isOpen)
				open(cells[y][x+1]);
			
		if(y>0) 
			if(!cells[y-1][x].isOpen)
				open(cells[y-1][x]);
			
		if(y<paramaters.height-1) 
			if(!cells[y+1][x].isOpen)
				open(cells[y+1][x]);
			
		if(y>0&&x>0) 
			if(!cells[y-1][x-1].isOpen)
				open(cells[y-1][x-1]);
			
		if(x<paramaters.width-1&&y>0) 
			if(!cells[y-1][x+1].isOpen)
				open(cells[y-1][x+1]);
			
		if(y<paramaters.height-1&&x>0) 
			if(!cells[y+1][x-1].isOpen)
				open(cells[y+1][x-1]);
			
		if(y<paramaters.height-1&&x<paramaters.width-1) 
			if(!cells[y+1][x+1].isOpen)
				open(cells[y+1][x+1]);
	}
	private void playerWon() {
		JOptionPane.showMessageDialog(null, "player won!");
		new LeaderBoard(scoreboard.getTime());
	}
}