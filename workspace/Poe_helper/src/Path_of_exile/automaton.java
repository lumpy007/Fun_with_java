package Path_of_exile;

import java.beans.EventHandler;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class automaton extends Application {

	public int counter = 0;

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		final Image poeImage = new Image ("https://web.poecdn.com/image/favicon/ogimage.png?atlas");
		
		
		Button flask = new Button("Flask");
		flask.setOnAction( this::flaskbuttonaction);
		
		
		
		Group root = new Group(flask);
		
		Scene scene = new Scene(root, 850, 850);
		scene.setFill(Color.rgb(255, 200, 200));
		primaryStage.setTitle("Poe helper control screen");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(poeImage);
		primaryStage.show();
		
	}
	private void flaskbuttonaction(ActionEvent event) 
	{
		this.counter++;
		Runnable flaskthread = this::flaskbuttonaction;
		
		new Thread(flaskthread).start();
		
		
	}
	
	private void flaskbuttonaction()
	{
		while(counter%2==0)
		{
			System.out.println(counter);
		}
		
	}
	public static void main(String[] args) 
	{
		launch();

	}


}
