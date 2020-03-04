package application;

import java.io.File;
import java.net.URI;

import javafx.scene.media.AudioClip;

public class DraftSounds {
	
	
	private static AudioClip buttonHoverClip;
	private static AudioClip buttonPressClip;
	private static AudioClip sceneChangeClip;
	
	private String resource = getClass().getResource("../sound/Recording.wav").toExternalForm();
	private AudioClip clip = new AudioClip(resource);
	
	private String pressSoundLoc = getClass().getResource("../sound/testSound.wav").toExternalForm();
	private AudioClip pressButtonClip = new AudioClip(pressSoundLoc);

	
	public DraftSounds() {
		
	}
	
	public void playClick() {
		clip.setVolume(.5);
		clip.play();
	}
	
	public void playPressButton() {
		pressButtonClip.play();
	}


}
