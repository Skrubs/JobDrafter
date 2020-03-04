package application;

import javafx.scene.Node;

public class Scaler {
	
		
	public Scaler(Node node) {
		scaleComponent(node);
	}
	
	private void scaleComponent(Node node) {
		node.setOnMouseEntered(e->{
			node.setScaleX(1.2);
			node.setScaleY(1.2);
			new DraftSounds().playClick();
		
		});
		
		node.setOnMouseExited(e->{
			node.setScaleX(1);
			node.setScaleY(1);
		});
	}
	

}
