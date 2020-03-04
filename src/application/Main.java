package application;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * JobDrafter is a small application written to ease the job of Flight Chiefs in
 * the 369 RCS for drafting jobs for their respected flight.
 * 
 * @author Angelo Barcelona
 *
 */

public class Main extends Application {

	private Stage window;
	private String title = "Barcelona JobDrafter v0.01";
	public static final double WINDOW_WIDTH = 1200;
	public static final double WINDOW_HEIGHT = 800;
	private String style = getClass().getResource("application.css").toExternalForm();
	private Scene mainScene;
	private Group root;
	private TextArea afscField;
	private Button loadJobListButton;
	private Button bookafscButton;
	private TextArea viewArea;
	private Label viewAreaLabel;
	private Pane viewAreaPane;
	private Pane centerTopPane;
	private Pane rightPane;
	private Label flightLabel;
	private Label selectedAfscLabel;
	private Label selectedRicLabel;
	private TextField selectedAfscField;
	private TextField selectedRicField;
	private ArrayList<Button> tempFlightList;
	private Button loginButton;
	private Button registrationButton;
	private XSSFWorkbook jobbook;
	private ListView<Job> jobListView;
	private ArrayList<Flight> accountsList;
	private TextField loginNameField;
	private TextField passwordField;
	private VBox vLoginBox;
	private Scene registrationScene;
	private TextField registerNameField;
	private TextField registerPasswordField;
	private TextField reEnterPasswordField;
	private Button addRecruiterButton;
	private Button saveRegisterButton;
	private Button manageFlightButton;
	
	

	@Override
	public void start(Stage primaryStage) {

		window = primaryStage;
		window.setTitle(title);
		window.setResizable(false);

		createMainDisplay();
		login();
		accountsList = new ArrayList<>();

		window.setScene(login());
		window.sizeToScene();
		window.show();

		// action for login button
		loginButton.setOnAction(e -> {
			String loginName = loginNameField.getText();
			String password = passwordField.getText();

			if (true) {
				new DraftSounds().playPressButton();
				window.setScene(createMainDisplay());
			}
		});

	

	}
	


	/**
	 * Creates the login scene
	 * 
	 * @return
	 */
	private Scene login() {
		vLoginBox = new VBox();
		vLoginBox.setId("vLoginBox");
		Label titleLabel = new Label("369RCS Job Drafter");
		titleLabel.setId("titleLabel");
		loginButton = new Button("Login");
		loginButton.setId("loginButton");
		new Scaler(loginButton);
		registrationButton = new Button("Register");
		new Scaler(registrationButton);
		registrationButton.setId("registrationButton");
		registrationButton.setOnAction(e->{
			registrationScene();
		});
		loginNameField = new TextField();
		loginNameField.setId("loginNameField");
		new Scaler(loginNameField);
		passwordField = new PasswordField();
		passwordField.setId("passwordField");
		new Scaler(passwordField);
		Label loginNameLabel = new Label("User Name:");
		loginNameLabel.setId("loginNameLabel");
		Label passwordLabel = new Label("Password:");
		passwordLabel.setId("passwordLabel");
		vLoginBox.getChildren().addAll(titleLabel, loginNameLabel, loginNameField, passwordLabel, passwordField,
				loginButton, registrationButton);
		Scene loginScene = new Scene(vLoginBox, WINDOW_WIDTH, WINDOW_HEIGHT);
		loginScene.getStylesheets().add(style);
		return loginScene;
	}

	/**
	 * creates right pane that will be a child of the main display
	 * 
	 * @return
	 */
	private Pane createRightPane() {
		rightPane = new Pane();
		rightPane.setId("rightPane");

		VBox rightBox = new VBox();
		rightBox.setId("rightBox");
		rightBox.setSpacing(10);
		rightBox.setAlignment(Pos.CENTER);
		rightBox.setPadding(new Insets(10, 5, 5, 10));

		tempFlightList = new ArrayList<Button>();

		rightBox.getChildren().addAll(tempFlightList);

		rightPane.getChildren().add(rightBox);

		return rightPane;
	}

	/**
	 * creates center top pane of the main display
	 * 
	 * @return
	 */
	private Pane centerTopPane() {

		bookafscButton = new Button("Book AFSC");
		bookafscButton.setId("bookafscButton");
		new Scaler(bookafscButton);

		centerTopPane = new Pane();
		centerTopPane.setId("centerTopPane");

		selectedAfscLabel = new Label("Selected AFSC");
		selectedAfscLabel.setId("selectedAfscLabel");

		selectedRicLabel = new Label("Selected RIC");
		selectedRicLabel.setId("selectedRicLabel");

		selectedAfscField = new TextField();
		selectedAfscField.setId("selectedAfscField");
		new Scaler(selectedAfscField);

		selectedRicField = new TextField();
		selectedRicField.setId("selectedRicField");
		new Scaler(selectedRicField);

		centerTopPane.getChildren().addAll(bookafscButton, selectedAfscField, selectedRicField, selectedAfscLabel,
				selectedRicLabel);

		return centerTopPane;

	}

	/**
	 * creates center pane with GUI controls
	 * 
	 * @return
	 */
	private Pane centerBottomPane() {

		viewArea = new TextArea();
		viewArea.setEditable(false);
		viewArea.setId("viewArea");
		viewAreaPane = new Pane();
		viewAreaPane.setId("viewAreaPane");
		viewAreaPane.getChildren().addAll(viewArea);
		return viewAreaPane;

	}

	/**
	 * Creates main display
	 * 
	 * @return
	 */
	private Scene createMainDisplay() {
		root = new Group();
		mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		mainScene.getStylesheets().add(style);
		Pane mainPane = new Pane();
		mainPane.setId("mainPane");
		mainPane.setPrefSize(mainScene.getWidth(), mainScene.getHeight());
		jobListView = new ListView<>();
		jobListView.setId("afscField");
		afscField = new TextArea();
		afscField.setId("afscField");
		afscField.setEditable(false);
		new Scaler(afscField);
		loadJobListButton = new Button("Load");
		loadJobListButton.setId("loadJobListButton");
		new Scaler(loadJobListButton);
		flightLabel = new Label("Flight");
		flightLabel.setId("flightLabel");
		viewAreaLabel = new Label("Selected RIC: " + " QW");
		viewAreaLabel.setId("viewAreaLabel");
		manageFlightButton = new Button("Manage Flight");
		manageFlightButton.setId("manageFlightButton");
		new Scaler(manageFlightButton);
		manageFlightButton.setOnAction(e->{
			manageFlightWindow(new Stage());
		});

		mainPane.getChildren().addAll(jobListView, loadJobListButton, centerBottomPane(), centerTopPane(),
				createRightPane(), flightLabel, viewAreaLabel, manageFlightButton);

		root.getChildren().add(mainPane);

		jobListView.setOnMouseClicked(e -> {
			selectedAfscField.clear();
			if (jobListView.getSelectionModel().getSelectedItem() != null)
				selectedAfscField.setText("" + jobListView.getSelectionModel().getSelectedItem());
		});

		// Actions for Recruiter buttons
		for (Button b : tempFlightList) {

			b.setPrefSize(145, 35);

			b.setOnAction(e -> {
				selectedRicField.clear();
				selectedRicField.setText(b.getText());
			});
		}
		
		loadJobListButton.setOnAction(e -> {
			JobListReader jReader = new JobListReader();
			try {
				ArrayList<Job> tempList = jReader.readJobListFile(window);
				jobListView.getItems().setAll(tempList);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});
		

		return mainScene;
	}
	
	/**
	 * creates a new window for registration of a new flight
	 * @return
	 */
	private void registrationScene() {
		Stage registerStage = new Stage();
		Pane registerRoot = new Pane();
		registerRoot.setPrefSize(400, 400);
		registerStage.setX(window.getX()+20);
		registerStage.setY(window.getY()+40);
		registrationScene = new Scene(registerRoot, 400, 400);
		registrationScene.getStylesheets().add(style);
		registerNameField = new TextField();
		registerNameField.setPromptText("User Name");
		registerNameField.setId("registerNameField");
		registerPasswordField = new PasswordField();
		registerPasswordField.setId("registerPasswordField");
		registerPasswordField.setPromptText("Password");
		reEnterPasswordField = new PasswordField();
		reEnterPasswordField.setId("reEnterPasswordField");
		reEnterPasswordField.setPromptText("Re-Enter Password");
		saveRegisterButton = new Button("Save");
		saveRegisterButton.setId("saveRegisterButton");
		saveRegisterButton.setOnAction(e->{
			String password = registerPasswordField.getText();
			String rePassword = reEnterPasswordField.getText();
			if(password.equals(rePassword)) {
				loginNameField.setText(registerNameField.getText());
			}
		});
		VBox registerVbox = new VBox();
		registerVbox.setPadding(new Insets(10,10,10,10));
		registerVbox.setId("registerVbox");
		registerVbox.getChildren().addAll(registerNameField,registerPasswordField,
											reEnterPasswordField,saveRegisterButton);
		registerRoot.getChildren().add(registerVbox);
		registerStage.setScene(registrationScene);
		saveRegisterButton.requestFocus();
		registerStage.show();
	}
	
	//REMOVE AND MAKE ITS OWN CLASS
	public void manageFlightWindow(Stage stage) {
	
		stage.setX(window.getX()+ 300);
		stage.setY(window.getY()+100);
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 400, 400);
		VBox vBox = new VBox();
		pane.getChildren().add(vBox);
		
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.sizeToScene();
		stage.setTitle("Manage Flight");
		stage.show();
	}
	


	public static void main(String[] args) {
		launch(args);
	}
}
