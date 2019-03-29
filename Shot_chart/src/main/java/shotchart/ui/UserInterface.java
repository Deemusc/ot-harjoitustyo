package shotchart.ui;

// @deemus
import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import shotchart.dao.FileShotChartDao;
import shotchart.dao.FileUserDao;
import shotchart.domain.ShotChartApp;

public class UserInterface extends Application {

    private ShotChartApp shotChartApp;

    private Scene shotChartScene;
    private Scene menuScene;
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
        FileShotChartDao shotChartDao = new FileShotChartDao(shotChartFile, userDao);
        shotChartApp = new ShotChartApp(shotChartDao, userDao);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        // -------- login scene -----------------------

        VBox loginPane = new VBox(20);
        HBox inputPane = new HBox(20);
        loginPane.setPadding(new Insets(10));
        Label usernameLabel = new Label("Username:");
        TextField usernameInput = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordInput = new PasswordField();

        inputPane.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput);
        Label loginMessage = new Label();

        Button loginButton = new Button("Login");
        Button createButton = new Button("Create new user");
        
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            menuLabel.setText(username + " logged in...");
            if (shotChartApp.login(username, password)) {
                loginMessage.setText("");                
                primaryStage.setScene(menuScene);
                usernameInput.setText("");
                passwordInput.setText("");
            } else {
                loginMessage.setText("Username or password is not correct.");
                loginMessage.setTextFill(Color.RED);
            }
        });

        createButton.setOnAction(e -> {
            usernameInput.setText("");
            passwordInput.setText("");
            primaryStage.setScene(newUserScene);
        });

        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);

        loginScene = new Scene(loginPane, 600, 500);

        // --------- Create new user -scene -----------------------
        
        VBox newUserPane = new VBox(20);

        HBox newUsernamePane = new HBox(20);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("Username:");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newPasswordPane = new HBox(20);
        newPasswordPane.setPadding(new Insets(10));
        PasswordField newPasswordInput = new PasswordField();
        Label newPasswordLabel = new Label("Password:");
        newPasswordLabel.setPrefWidth(100);
        newPasswordPane.getChildren().addAll(newPasswordLabel, newPasswordInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("Create");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String password = newPasswordInput.getText();

            if (username.length() == 2 || password.length() < 2) {
                userCreationMessage.setText("Username or password is too short.");
                userCreationMessage.setTextFill(Color.RED);
            } else if (shotChartApp.createUser(username, password)) {
                userCreationMessage.setText("");
                loginMessage.setText("New user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);
            } else {
                userCreationMessage.setText("Username has to be unique.");
                userCreationMessage.setTextFill(Color.RED);
            }
        });

        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newPasswordPane, createNewUserButton);

        newUserScene = new Scene(newUserPane, 600, 500);

        // ------ menuscene ------------------------------------
        
        VBox menuLayoutPane = new VBox(20);
        Label menuHeader = new Label("Shot chart application");
        
        Button newGameButton = new Button("New game");
        Button viewGamesButton = new Button("View previous games");
        
        menuLayoutPane.getChildren().addAll(menuHeader, newGameButton, viewGamesButton);
        
        menuScene = new Scene(menuLayoutPane, 600, 500);
        
        // PrimaryStage
        primaryStage.setTitle("ShotCharts");
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

    @Override
    public void stop() {
        System.out.println("Laukaisukartat sulkeutuu");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
