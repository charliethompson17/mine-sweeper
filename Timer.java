import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Timer extends JLabel{
	long startTime;
	private static final long serialVersionUID = 1L;
	class StopWatch implements Runnable {
		private Thread t;
		private String threadName;
				   
		StopWatch( String name) {
			threadName = name;
		}
		   
		public void run() {
			System.out.println("Running " +  threadName );
			try {
				while(true) {
					updateTime(System.currentTimeMillis());
					// Let the thread sleep for a while.
					Thread.sleep(50);
				}
			} catch (InterruptedException e) {
				System.out.println("Thread " +  threadName + " interrupted.");
			}
			System.out.println("Thread " +  threadName + " exiting.");
		}
			   
		public void start () {
			if (t == null) {
				t = new Thread (this, threadName);
				t.start ();
			}
		}
	}
	Timer(Paramaters paramaters){
		startTime = System.currentTimeMillis();
		StopWatch R1 = new StopWatch( "Thread-1");
		R1.start();
		
		this.setFont(new Font("Helvetica",Font.PLAIN,20));
		this.setForeground(Color.red);
		this.setLocation(paramaters.screenSize.width/2+paramaters.cellSize*2, 0);
		this.setSize(paramaters.cellSize*3, paramaters.cellSize);
		this.setOpaque(true);
		this.setBackground(paramaters.openColor);
	}
	void updateTime(long time){
		time = time-startTime;
		int minutes = (int) (time/60000);
		time=time%60000;
		int seconds = (int) (time/1000);
		this.setText("" + minutes+":"+seconds);
	}
}
