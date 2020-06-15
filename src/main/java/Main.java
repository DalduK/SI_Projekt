import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        NonogramAppearance r = new NonogramAppearance();


        Scene scene = new Scene(r, 1500, 1000);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Nonogram");
        primaryStage.show();
    }


    public static void main(String[] args) {
        //javafx stuff
        launch(args);

    }
}
