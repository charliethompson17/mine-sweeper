import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class AddName {
	
	AddName(long time){
		addName(getUserName(), time);
	}
	public void addName(String name, long time) {
		try {
		      FileWriter myWriter = new FileWriter("LeaderBoard.txt", true);
		      myWriter.write(name+":"+time+"\n");
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	public String getUserName() {
		String name = JOptionPane.showInputDialog("Enter Name");
		return name;
	}
	class Leader{
		String name;
		long time;
		Leader(String name, long time){
			this.name=name;
			this.time=time;
		}
	}
}
