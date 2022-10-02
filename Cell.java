import javax.swing.JButton;

class Cell extends JButton {
		private static final long serialVersionUID = 1L;
		int val=0;
		int x;
		int y;
		boolean isOpen=false;
		boolean isBomb=false;
		boolean flaged=false;
		Cell(int x, int y){
			this.x=x;
			this.y=y;
		}
}