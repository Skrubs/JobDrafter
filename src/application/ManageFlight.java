package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManageFlight extends Pane implements Runnable {
	
	private boolean isRunning;
	private int height = 400;;
	private int width = 400;
	private Button addRecruiterButton;
	private Button removeRecruiterButton;
	private Button checkRecruiterQWButton;
	private Button closeButton;
	private VBox vManageBox;
	private Stage manageStage;
	private Scene manageScene;
	private double parentX;
	private double parentY;

	public ManageFlight(Stage parentStage) {
		super();
		parentX = parentStage.getX() + 300;
		parentY = parentStage.getY()+100;
		setPrefSize(width,height);
		addChildren();
		addParents();
	}
	
	private void addChildren() {
		vManageBox = new VBox();
		vManageBox.setId("vManageBox");
		addRecruiterButton = new Button("Add Recruiter");
		addRecruiterButton.setId("addRecruiterButton");
		removeRecruiterButton = new Button("Remove Recruiter");
		removeRecruiterButton.setId("removeRecruiterButton");
		checkRecruiterQWButton = new Button("Check RIC QW");
		checkRecruiterQWButton.setId("checkRecruiterQWButton");
		closeButton = new Button("Close");
		closeButton.setId("closeButton");
		vManageBox.getChildren().addAll(addRecruiterButton,removeRecruiterButton,checkRecruiterQWButton, closeButton);
		getChildren().add(vManageBox);
	}
	
	private void addParents() {
		manageStage = new Stage();
		manageScene = new Scene(this,width,height);
		manageStage.setScene(manageScene);
		manageStage.sizeToScene();
		manageStage.setResizable(false);
		manageStage.setAlwaysOnTop(true);
		manageStage.initModality(Modality.APPLICATION_MODAL);
		manageStage.setX(parentX);
		manageStage.setY(parentY);		
		manageStage.show();
		
		closeButton.setOnAction(e->{
			manageStage.close();
		});
	}
	
	
	@Override
	public void run() {
		
		
	}
	
	//Button getters
	public Button getAddRecruiterButton() {
		return addRecruiterButton;
	}
	
	public Button getRemoveRecruiterButton() {
		return removeRecruiterButton;
	}
	
	public Button getCheckRecruiterQWButton() {
		return checkRecruiterQWButton;
	}

}
