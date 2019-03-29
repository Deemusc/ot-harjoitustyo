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

        createButton.setOnAction(e -> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);
        });

        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);

        loginScene = new Scene(loginPane, 600, 500);

        // create new user -scene
        VBox newUserPane = new VBox(10);

        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("Username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();

            if (username.length() == 2 || name.length() < 2) {
                userCreationMessage.setText("username or name too short");
                userCreationMessage.setTextFill(Color.RED);
            } else if (shotChartApp.createUser(username, name)) {
                userCreationMessage.setText("");
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);
            }
        });

        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton);

        newUserScene = new Scene(newUserPane, 300, 250);

        // PrimaryStage
        primaryStage.setTitle("ShotCharts:");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("closing");
            System.out.println(shotChartApp.getLoggedUser());
            if (shotChartApp.getLoggedUser() != null) {
                e.consume();
            }

        });

    }

    private void redrawShotChartList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stop() {
        System.out.println("Laukaisukartat sulkeutuu");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
