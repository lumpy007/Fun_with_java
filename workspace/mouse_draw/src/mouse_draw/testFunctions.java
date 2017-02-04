package mouse_draw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testFunctions implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) 
	{

		fillMatrix();

	}

	private void fillMatrix() {
		for (int i = 0; i < control.mouse.myScreen.screenSize.width; i++)
		{
		
			for (int j = 0; j < control.mouse.myScreen.screenSize.height; j++)
			{
				control.mouse.myScreen.matrix[i][j] = i + j;

				
			}
		}
	}

}
