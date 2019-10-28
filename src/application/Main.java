package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JobDrafter is a small application written to ease the job
 * of Flight Chiefs in the 369 RCS for drafting jobs
 * for their respected flight.
 * @author Angelo
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
	

	@Override
	public void start(Stage primaryStage) {

		window = primaryStage;
		window.setTitle(title);
		window.setResizable(false);
		
		createMainDisplay();
		
		window.setScene(login());
		window.sizeToScene();
		window.show();
		
		
		//action for login button
		loginButton.setOnAction(e->{
			if(true) {
				window.setScene(createMainDisplay());
			}
		});
		
		//Actions for Recruiter buttons
		for(Button b : tempFlightList) {
			
			b.setPrefSize(175, 35);
			
			b.setOnAction(e->{
				selectedRicField.clear();
				selectedRicField.setText(b.getText());
			});
		}
		

	}
	
	private Scene login() {
		VBox vLoginBox = new VBox();
		vLoginBox.setId("vLoginBox");
		Label titleLabel = new Label("369RCS Job Drafter");
		titleLabel.setId("titleLabel");
		loginButton = new Button("Login");
		loginButton.setId("loginButton");
		registrationButton = new Button("Register");
		registrationButton.setId("registrationButton");
		TextField loginNameField = new TextField();
		loginNameField.setId("loginNameField");
		TextField passwordField = new PasswordField();
		passwordField.setId("passwordField");
		Label loginNameLabel = new Label("User Name:");
		loginNameLabel.setId("loginNameLabel");
		Label passwordLabel = new Label("Password:");
		passwordLabel.setId("passwordLabel");
		vLoginBox.getChildren().addAll(titleLabel,loginNameLabel,loginNameField,passwordLabel,
								passwordField, loginButton);
		Scene loginScene = new Scene(vLoginBox,WINDOW_WIDTH, WINDOW_HEIGHT);
		loginScene.getStylesheets().add(style);
		return loginScene;
	}

	private Pane createRightPane() {
		rightPane = new Pane();
		rightPane.setId("rightPane");
		
		VBox rightBox = new VBox();
		rightBox.setId("rightBox");
		rightBox.setSpacing(10);
		rightBox.setAlignment(Pos.CENTER);
		rightBox.setPadding(new Insets(10,5,5,10));
		
		tempFlightList = new ArrayList<>();
		
		for(int i = 0; i < 8; i++) {
			tempFlightList.add(new Button("RIC" + " " + (i+1)));
			tempFlightList.get(i).setPrefSize(100, 30);
		}
		
		rightBox.getChildren().addAll(tempFlightList);
		
		rightPane.getChildren().add(rightBox);
		
		return rightPane;
	}

	private Pane centerTopPane() {

		bookafscButton = new Button("Book AFSC");
		bookafscButton.setId("bookafscButton");

		centerTopPane = new Pane();
		centerTopPane.setId("centerTopPane");

		selectedAfscLabel = new Label("Selected AFSC");
		selectedAfscLabel.setId("selectedAfscLabel");

		selectedRicLabel = new Label("Selected RIC");
		selectedRicLabel.setId("selectedRicLabel");

		selectedAfscField = new TextField();
		selectedAfscField.setId("selectedAfscField");

		selectedRicField = new TextField();
		selectedRicField.setId("selectedRicField");

		centerTopPane.getChildren().addAll(bookafscButton,selectedAfscField,selectedRicField,
										selectedAfscLabel,selectedRicLabel);

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
		afscField = new TextArea();
		afscField.setId("afscField");
		afscField.setEditable(false);
		loadJobListButton = new Button("Load");
		loadJobListButton.setId("loadJobListButton");
		flightLabel = new Label("Flight");
		flightLabel.setId("flightLabel");
		viewAreaLabel = new Label("Selected RIC: " + " QW");
		viewAreaLabel.setId("viewAreaLabel");		

		mainPane.getChildren().addAll(afscField, loadJobListButton, centerBottomPane(), centerTopPane(),
				createRightPane(), flightLabel, viewAreaLabel);

		root.getChildren().add(mainPane);

		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
