package application;
	
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	
	public long tiempoInicio;
	public Scene scene;
	public GraphicsContext gc;
	public Image imagen;
	
	@Override
	public void start(final Stage primaryStage) {
		try {
			
			primaryStage.setTitle("Prueba JavaFX");
			BorderPane root = new BorderPane();
			scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			
			Canvas canvas = new Canvas(512,512);
			root.getChildren().add(canvas);
			
			gc = canvas.getGraphicsContext2D();
			imagen = new Image("gota.png");

			Timeline loop = new Timeline();
			loop.setCycleCount(Timeline.INDEFINITE);
			
			gc.drawImage( imagen, 150, 0 );
			gc.drawImage(imagen, 300, 0);
			tiempoInicio = System.currentTimeMillis();
			
			KeyFrame keyFrame = new KeyFrame(
					Duration.seconds(0.017),
					new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							
		                    double y = (System.currentTimeMillis() - tiempoInicio) / 10.0; 
                            
		                    gc.clearRect(0, 0, 512,512);
		                    
		                    gc.drawImage( imagen, 100, y );
		                    gc.drawImage( imagen, 300, y );
		                    
		                    if(scene.getHeight() < y) {
		                    	tiempoInicio = System.currentTimeMillis();
		                    }
						}
					});
			
			loop.getKeyFrames().add(keyFrame);
			loop.play();
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
