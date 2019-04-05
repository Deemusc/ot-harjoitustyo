package shotchart.ui;

// @deemus
import java.io.FileInputStream;
import java.util.Properties;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import shotchart.dao.FileShotChartDao;
import shotchart.dao.FileUserDao;
import shotchart.domain.ShotChartApp;

public class UserInterface extends Application {

    private ShotChartApp shotChartApp;

    private Scene menuScene;
    private Scene newUserScene;
    private Scene loginScene;
    private Scene newGameScene;
    private Scene fillNewGameInfoScene;

    private final Label menuLabel = new Label("Shot chart application - v.0.3");

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
        // ----- Luodaan sisäänkirjautumisikkuna -----
        VBox loginPane = new VBox(20);
        loginPane.setPadding(new Insets(10));
        HBox inputUsernamePane = new HBox(20);
        Label usernameLabel = new Label("Team's username:");
        TextField usernameInput = new TextField();
        usernameLabel.setPrefWidth(150);
        inputUsernamePane.getChildren().addAll(usernameLabel, usernameInput);

        HBox inputPasswordPane = new HBox(20);
        Label passwordLabel = new Label("Password:");
        PasswordField passwordInput = new PasswordField();
        passwordLabel.setPrefWidth(150);
        inputPasswordPane.getChildren().addAll(passwordLabel, passwordInput);

        // Viesti sisäänkirjautumisen ongelmatilanteita varten...
        Label loginMessage = new Label();

        // Nappulat kirjautumiseen ja uuden käyttäjän luomiseen
        Button loginButton = new Button("Login");
        Button createButton = new Button("Create new user");

        // Nappuloiden toiminta
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            if (shotChartApp.login(username, password)) {
                loginMessage.setText("");
                primaryStage.setScene(menuScene);
                usernameInput.setText("");
                passwordInput.setText("");
            } else {
                loginMessage.setText("Username or password is not valid.");
                loginMessage.setTextFill(Color.RED);
            }
        });

        createButton.setOnAction(e -> {
            usernameInput.setText("");
            passwordInput.setText("");
            primaryStage.setScene(newUserScene);
        });

        loginPane.getChildren().addAll(loginMessage, inputUsernamePane, inputPasswordPane, loginButton, createButton);

        loginScene = new Scene(loginPane, 600, 1000);

        // ----- Uuden käyttäjän luomisikkuna -----
        VBox newUserPane = new VBox(20);
        newUserPane.setPadding(new Insets(10));
        HBox newUsernamePane = new HBox(20);
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("Team's username:");
        newUsernameLabel.setPrefWidth(150);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newPasswordPane = new HBox(20);
        PasswordField newPasswordInput = new PasswordField();
        Label newPasswordLabel = new Label("Password:");
        newPasswordLabel.setPrefWidth(150);
        newPasswordPane.getChildren().addAll(newPasswordLabel, newPasswordInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("Create");
        Button backToLoginButton = new Button("Back to login screen");

        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String password = newPasswordInput.getText();

            // Tarkistetaan, että tunnukset ovat yli 2 merkkiä pitkät
            if (username.length() == 2 || password.length() < 2) {
                userCreationMessage.setText("Username or password is too short.");
                userCreationMessage.setTextFill(Color.RED);
            } else if (shotChartApp.createUser(username, password)) {
                userCreationMessage.setText("");
                loginMessage.setText("New user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);
            } else {
                userCreationMessage.setText("Username has already taken.");
                userCreationMessage.setTextFill(Color.RED);
            }
        });

        backToLoginButton.setOnAction(e -> {
            primaryStage.setScene(loginScene);
        });

        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newPasswordPane, createNewUserButton, backToLoginButton);

        newUserScene = new Scene(newUserPane, 600, 1000);

        // ----- Luodaan ikkuna päävalikolle -----
        VBox menuLayoutPane = new VBox(20);
        Label menuHeader = new Label("Shot chart application");

        Button newGameButton = new Button("New game");
        Button viewGamesButton = new Button("View previous games");
        Button logoutButton = new Button("Logout");

        // Uuden pelin luomisnapin painalluksen käsittely
        newGameButton.setOnAction(e -> {
            primaryStage.setScene(fillNewGameInfoScene);
        });

        // Logout-napin painalluksen käsittely
        logoutButton.setOnAction(e -> {
            shotChartApp.logout();
            primaryStage.setScene(loginScene);
        });

        menuLayoutPane.getChildren().addAll(menuLabel, menuHeader, newGameButton, viewGamesButton, logoutButton);

        menuScene = new Scene(menuLayoutPane, 600, 1000);

        // ----- Luodaan ikkuna uuden pelin tietojen syöttämiselle -----
        VBox infoPane = new VBox(20);
        VBox infoInputPane = new VBox(20);
        infoPane.setPadding(new Insets(10));

        // Selittäviä tekstejä ja syöttökenttiä
        Label infoLabel = new Label("Creating new game... Fill in some information.");
        Label dateLabel = new Label("Date (yyyy-mm-dd):");
        TextField dateInput = new TextField();
        Label opponentLabel = new Label("Opposing team:");
        TextField opponentInput = new TextField();

        // Asetellaan
        infoInputPane.getChildren().addAll(menuLabel, infoLabel, dateLabel, dateInput, opponentLabel, opponentInput);

        // Luodaan napit eteen- ja taaksepäin siirtymiselle
        Button confirmButton = new Button("Create game");
        Button backButton = new Button("Back to menu");

        // Nappuloiden painamisen seuraukset
        confirmButton.setOnAction(e -> {
            String date = dateInput.getText();
            String opponent = opponentInput.getText();
            shotChartApp.createNewGame(date, opponent);
            primaryStage.setScene(newGameScene);
        });

        backButton.setOnAction(e -> {
            primaryStage.setScene(menuScene);
        });

        infoPane.getChildren().addAll(infoInputPane, confirmButton, backButton);

        fillNewGameInfoScene = new Scene(infoPane, 600, 1000);

        newGameScene = newGameBase();

        // ----- Luodaan primarystage -----
        primaryStage.setTitle("ShotCharts Application");
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

    public Scene newGameBase() {
        // Luodaan tyhjä taulu ja piirturi        
        Canvas gameBase = new Canvas(600, 950);
        GraphicsContext gameBaseDrawer = gameBase.getGraphicsContext2D();

        // Asetellaan ne
        BorderPane gameLayout = new BorderPane();
        gameLayout.setCenter(gameBase);

        // Luodaan ryhmä nappuloille
        ToggleGroup shotTypeButtonGroup = new ToggleGroup();

        // Luodaan nappulat maalille, torjunnalle ja ohivedolle ja asetetaan ne ryhmään
        RadioButton goal = new RadioButton("Goal");
        goal.setToggleGroup(shotTypeButtonGroup);
        goal.setSelected(true);
        RadioButton block = new RadioButton("Blocked shot");
        block.setToggleGroup(shotTypeButtonGroup);
        RadioButton miss = new RadioButton("Missed shot");
        miss.setToggleGroup(shotTypeButtonGroup);

        // Asetetaan nappulat layoutiin
        VBox shotTypeButtons = new VBox();
        shotTypeButtons.getChildren().addAll(goal, block, miss);
        gameLayout.setTop(shotTypeButtons);

        // Piirretään tyhjä kenttä
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Maalataan kentän pohja valkoiseksi
                gameBaseDrawer.setFill(Color.WHITE);
                gameBaseDrawer.clearRect(0, 0, 600, 950);
                gameBaseDrawer.fillRect(0, 0, 600, 950);

                // Piirretään kentän viivat harmaalla
                gameBaseDrawer.setFill(Color.LIGHTGRAY);

                // Keskiviiva
                gameBaseDrawer.fillRect(0, 475, 600, 5);

                // Ylempi maalivahdinalue
                gameBaseDrawer.fillRect(250, 50, 100, 5);
                gameBaseDrawer.fillRect(250, 125, 100, 5);
                gameBaseDrawer.fillRect(250, 50, 5, 75);
                gameBaseDrawer.fillRect(350, 50, 5, 80);
                gameBaseDrawer.fillRect(270, 70, 60, 5);
                gameBaseDrawer.fillRect(270, 50, 5, 20);
                gameBaseDrawer.fillRect(330, 50, 5, 25);

                // Alempi maalivahdinalue
                gameBaseDrawer.fillRect(250, 900, 100, 5);
                gameBaseDrawer.fillRect(250, 825, 100, 5);
                gameBaseDrawer.fillRect(250, 825, 5, 75);
                gameBaseDrawer.fillRect(350, 825, 5, 80);
                gameBaseDrawer.fillRect(270, 880, 60, 5);
                gameBaseDrawer.fillRect(270, 880, 5, 20);
                gameBaseDrawer.fillRect(330, 880, 5, 25);

                // Kulmapisteet
                gameBaseDrawer.fillRect(25, 50, 5, 5);
                gameBaseDrawer.fillRect(25, 900, 5, 5);
                gameBaseDrawer.fillRect(570, 50, 5, 5);
                gameBaseDrawer.fillRect(570, 900, 5, 5);

                // Ei piirretä laitoja ainakaan tässä iteraatiossa.              
            }
        }.start();

        // Palautetaan näkymä tyhjästä kentästä
        return new Scene(gameLayout);
    }

    @Override
    public void stop() {
        System.out.println("Laukaisukartat sulkeutuu");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
