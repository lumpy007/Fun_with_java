package play;

import java.io.FileInputStream;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.effect.MotionBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class origin extends Application{

	@Override
	public void start(Stage stage) throws Exception 
	{
        //FileInputStream file = new FileInputStream("d:/userdata/atkiss/Desktop/tumblr_ly4dkagYm31qcr7fqo1_400.gif");
        //FileInputStream file1 = new FileInputStream("d:/userdata/atkiss/Desktop/overwatch.png");
        Image image1 = new Image("http://quake.cs2.hu/s/quake/image/66/quake_cs2_hu-6670_m.jpg");
       
        Stop[] stops = new Stop[] {
                new Stop(0, Color.DARKSLATEBLUE),  
                new Stop(1, Color.DARKRED)
             };       
             LinearGradient radialGradient =
                new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
       
       
       
        //Image image = new Image(file);
        //Image image1 = new Image(file1);
        Glow glow = new Glow();
       
       
        glow.setLevel(20);
        Path path = new Path();
        Text text = new Text("NOKIA");
        text.setX(200);
        text.setY(500);
        text.setFont(Font.font("NokiaKokia", FontWeight.BOLD, FontPosture.REGULAR, 70));
        text.setFill(radialGradient);
        text.setStrokeWidth(3);
        text.setStroke(Color.GRAY);
        text.setEffect(glow);
       
       
        PhongMaterial material1 = new PhongMaterial();
        PhongMaterial material2 = new PhongMaterial();
        //material1.setBumpMap(image);
        //material1.setDiffuseMap(image);
        material1.setDiffuseMap(image1);
        material2.setDiffuseMap(image1);
       
        ImageView imageView = new ImageView(image1);
        imageView.setX(100);
        imageView.setY(500);
        imageView.setFitHeight(200);
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        imageView.setVisible(false);
       
       
        Box box = new Box();
        box.setWidth(150.0);
        box.setHeight(150.0);  
        box.setDepth(150.0);
        box.setEffect(glow);
        Sphere sphere = new Sphere(200);
        sphere.setLayoutX(300);
        sphere.setLayoutY(300);
        sphere.setMaterial(material2);
       
        box.setMaterial(material2);
       
        Translate translate = new Translate();      
        translate.setX(400);
        translate.setY(150);
        translate.setZ(25);
       
       
       
       
        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
        rxBox.setAngle(30);
        ryBox.setAngle(50);
        rzBox.setAngle(30);
        box.setEffect(glow);
        box.getTransforms().addAll(translate,rxBox, ryBox, rzBox);
        //sphere.getTransforms().addAll(translate,rxBox, ryBox, rzBox);
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.seconds(20));
        rotateTransition.setNode(box);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(20);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setAxis(new Point3D(300,300,0));
        rotateTransition.play();
       
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.seconds(20));
        scaleTransition.setNode(box);
        scaleTransition.setByY(1.5);
        scaleTransition.setByX(1.5);
        scaleTransition.setByZ(1.5);
        scaleTransition.setCycleCount(20);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
       
       
       
       
        MoveTo move = new MoveTo(108,71);
        LineTo line1 = new LineTo(321, 161);
        LineTo line2 = new LineTo(126,232);
        LineTo line3 = new LineTo(232,52);
        LineTo line4 = new LineTo(269, 250);
        LineTo line5 = new LineTo(408, 371);
        QuadCurveTo curve = new QuadCurveTo(508,585,50,26);
       
        path.getElements().add(move);
        path.getElements().addAll(line1, line2, line3, line4, line5,curve);
       
        Group root = new Group(path,text,box,imageView);
       
       
       
        Scene scene = new Scene(root, 850, 850);
       
        stage.setTitle("Elso FX ablakom");
        stage.setScene(scene);
       
        stage.show();
		
	}

	
	public static void main() 
    {
        launch();
    }
}
