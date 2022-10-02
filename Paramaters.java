import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;

class Paramaters{
		JFrame frame;
		int cellSize;
		int width;
		int height;
		double density;
		int mines;
		Color closedColor;
		Color openColor;
		Color openBorderColor;
		Color closedBorderColor;
		Color flagColor;
		Color numberColor;
		Dimension screenSize;
		Paramaters(JFrame frame){
			this.frame=frame;
			screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			cellSize = 30;
			width = screenSize.width/cellSize;
			height = ((screenSize.height-28)/cellSize)-1;
			double density;
			String configFilePath = "src/config.properties";
			FileInputStream propsInput = null;
			try {
				propsInput = new FileInputStream(configFilePath);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Properties prop = new Properties();
			try {
				prop.load(propsInput);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			density = Double.valueOf(prop.getProperty("Density")); 
			mines = (int) (1.0*width*height*density);
			closedColor = new Color(Integer.parseInt(prop.getProperty("closedColor_R")),Integer.parseInt(prop.getProperty("closedColor_G")),Integer.parseInt(prop.getProperty("closedColor_B")));
			openColor = new Color(Integer.parseInt(prop.getProperty("openColor_R")),Integer.parseInt(prop.getProperty("openColor_G")),Integer.parseInt(prop.getProperty("openColor_B")));
			closedBorderColor = new Color(Integer.parseInt(prop.getProperty("closedBorderColor_R")),Integer.parseInt(prop.getProperty("closedBorderColor_G")),Integer.parseInt(prop.getProperty("closedBorderColor_B")));
			openBorderColor = new Color(Integer.parseInt(prop.getProperty("openBorderColor_R")),Integer.parseInt(prop.getProperty("openBorderColor_G")),Integer.parseInt(prop.getProperty("openBorderColor_B")));
			flagColor = new Color(Integer.parseInt(prop.getProperty("flagColor_R")),Integer.parseInt(prop.getProperty("flagColor_G")),Integer.parseInt(prop.getProperty("flagColor_B")));
			numberColor = new Color(Integer.parseInt(prop.getProperty("numberColor_R")),Integer.parseInt(prop.getProperty("numberColor_G")),Integer.parseInt(prop.getProperty("numberColor_B")));
		}
		
	}