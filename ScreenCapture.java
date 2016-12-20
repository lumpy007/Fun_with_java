package mouse_draw;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ScreenCapture {
	
	public Rectangle screenSize = new Rectangle();
	public int[][] matrix;
	public int max = 0;
	
	
	
	public ScreenCapture() {
		screenSize = getScreenBounds();
		matrix = new int[screenSize.width][screenSize.height];
	}




	public Rectangle getScreenBounds()
	{
		
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice[] gs = ge.getScreenDevices();
	      
	      for (int j = 0; j < gs.length; j++)
	      {
	          GraphicsDevice gd = gs[j];
	          GraphicsConfiguration[] gc = gd.getConfigurations();
	          for (int i=0; i < gc.length; i++)
	          {
	        	  screenSize = screenSize.union(gc[i].getBounds());
	          }
	      }
	      return screenSize;
	}
	
	public int getMax(int[][] matrix)
	{
		/*int maxValue = 0;
		
		for (int i = 0; i < control.mouse.myScreen.screenSize.width; i++)
		{
		
			for (int j = 0; j < control.mouse.myScreen.screenSize.height; j++)
			{
				if (matrix[i][j] > maxValue) maxValue = matrix[i][j];
			}
		}*/
		List<Integer> maxCols = new ArrayList<>(); 
		Arrays.stream(matrix).forEach((col)->{
			maxCols.add(Arrays.stream(col).max().getAsInt());
		});
		
		
		return maxCols.stream().max((a,b)->
			 Integer.compare(a, b)
		).get();
	}

}
