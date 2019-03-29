package shotchart.ui;

// @deemus


import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import shotchart.dao.FileUserDao;
import shotchart.domain.ShotChartApp;


public class UserInterface extends Application {
    private ShotChartApp shotChartApp;
    
    private Scene shotChartScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private Label menuLabel = new Label();
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String shotChartFile = properties.getProperty("shotChartFile");
        
        FileUserDao userDao = new FileUserDao(userFile);
        //FileShotChartDao shotChartDao = new FileShotChartDao(shotChartFile, userDao);
        //shotChartApp = new ShotChartApp(shotChartDao, userDao);
    }
            
    @Override
    public void start(Stage primaryStage) throws Exception {
        // login scene
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();

        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            menuLabel.setText(username + " logged in...");
            if (shotChartApp.login(username)) {
                loginMessage.setText("");
                redrawShotChartList();
                primaryStage.setScene(shotChartScene);
                usernameInput.setText("");
            } else {
                loginMessage.setText("user does not exist");
                loginMessage.setTextFill(Color.RED);
            }
        });
    
    }
    
}
