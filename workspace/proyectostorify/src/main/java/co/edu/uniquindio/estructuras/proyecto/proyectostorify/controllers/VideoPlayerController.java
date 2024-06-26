package co.edu.uniquindio.estructuras.proyecto.proyectostorify.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import co.edu.uniquindio.estructuras.proyecto.proyectostorify.circularList.CircularList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class VideoPlayerController implements Initializable {

	@FXML
	private Button nextButton;

	@FXML
	private BorderPane pane;

	@FXML
	private Button pauseButton;

	@FXML
	private Button playButton;

	@FXML
	private Button previousButton;

	@FXML
	private Button resetButton;

	@FXML
	private Label songLabel;

	@FXML
	private ProgressBar songProgressBar;

	@FXML
	private ComboBox<String> speedBox;

	@FXML
	private Slider volumeSlider;

	private File[] files;
	private CircularList<File> songs;
	private int songNumber;
	private int[] speeds = { 25, 50, 75, 100, 125, 150, 175, 200 };
	// Aca importe el de java util, pero no se si sea el de javafx
	private Timer timer;
	private TimerTask task;
	private boolean running;

	// Se importo el de javafx scene
	private Media media;
	private MediaPlayer mediaPlayer;
	
	/**
	 * Organiza los archivos de música y configura el reproductor multimedia.
	 * 
	 * @param songs la lista de archivos de música a organizar
	 */
	public void organizarArchivos(CircularList<File> songs) {
		this.songs = songs;
		if (files != null) {
			for (File file : files) {
				songs.add(file);

			}
		}
		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);

		songLabel.setText(songs.get(songNumber).getName());

		for (int i = 0; i < speeds.length; i++) {
			speedBox.getItems().add(Integer.toString(speeds[i]) + "%");
		}
		speedBox.setOnAction(event -> changeSpeedEvent(event));
	
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				mediaPlayer.setVolume(volumeSlider.getValue()*0.01);
				
			}
		});
		songProgressBar.setStyle("-fx-accent: #00FF00;");
	}
	
	
	/**
	 * Inicializa la clase.
	 * Este método no realiza ninguna acción específica.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Maneja el evento de cambio de velocidad de reproducción del audio.
	 * 
	 * @param event el evento de cambio de velocidad
	 */
	@FXML
	void changeSpeedEvent(ActionEvent event) {
		// mediaPlayer.setRate(Integer.parseInt(speedBox.getValue())*0.01);
		if (speedBox.getValue() == null) {
			mediaPlayer.setRate(1);
		} else {
			mediaPlayer.setRate(
					Integer.parseInt(speedBox.getValue().substring(0, speedBox.getValue().length() - 1)) * 0.01);
		}
	}
	
	/**
	 * Maneja el evento de reproducción del siguiente archivo multimedia en la lista.
	 * 
	 * @param event el evento de reproducción
	 */
	@FXML
	void nextMediaEvent(ActionEvent event) {
		if (songNumber < songs.size() - 1) {
			songNumber++;
			mediaPlayer.stop();
			
			if(running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			songLabel.setText(songs.get(songNumber).getName());
			playMediaEvent(event);
		} else {
			songNumber = 0;
			mediaPlayer.stop();
			
			if(running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			songLabel.setText(songs.get(songNumber).getName());
			playMediaEvent(event);

		}

	}
	
	/**
	 * Maneja el evento de pausa de reproducción del archivo multimedia actual.
	 * 
	 * @param event el evento de pausa
	 */
	@FXML
	void pauseMediaEvent(ActionEvent event) {
		cancelTimer();
		mediaPlayer.pause();
	}
	
	/**
	 * Maneja el evento de reproducción del archivo multimedia actual.
	 * 
	 * @param event el evento de reproducción
	 */
	@FXML
	void playMediaEvent(ActionEvent event) {
		
		beginTimer();
		/*
		 * Se desea mantener la velocidad de reproduccion que se ha ajustado aun asi
		 * cuando se cambie la cancion
		 */
		changeSpeedEvent(event);
		/*
		 * Se desea mantener el volumen al momento de hacer cambios
		 */
		mediaPlayer.setVolume(volumeSlider.getValue()*0.01);
		mediaPlayer.play();
	}
	
	/**
	 * Maneja el evento de reproducción del archivo multimedia anterior en la lista.
	 * 
	 * @param event el evento de reproducción
	 */
	@FXML
	void previousMediaEvent(ActionEvent event) {
		if (songNumber > 0) {
			songNumber--;
			mediaPlayer.stop();
			
			if(running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			songLabel.setText(songs.get(songNumber).getName());
			playMediaEvent(event);
		} else {
			songNumber = songs.size() - 1;
			mediaPlayer.stop();
			
			if(running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());
			mediaPlayer = new MediaPlayer(media);

			songLabel.setText(songs.get(songNumber).getName());
			playMediaEvent(event);

		}
	}
	
	/**
	 * Maneja el evento de reinicio de reproducción del archivo multimedia actual.
	 * 
	 * @param event el evento de reinicio
	 */
	@FXML
	void resetMediaEvent(ActionEvent event) {
		//Solo se setea la progress bar al parecer
		songProgressBar.setProgress(0);
		mediaPlayer.seek(Duration.seconds(0));
	}
	
	/**
	 * Inicia el temporizador para actualizar la barra de progreso de reproducción del audio.
	 */
	public void beginTimer() {
		timer= new Timer();
		task= new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				running=true;
				double current= mediaPlayer.getCurrentTime().toSeconds();
				double end=media.getDuration().toSeconds();
				songProgressBar.setProgress(current/end);
				
				if(current/end==1) {
					cancelTimer();
				}
			}
		};
		
		/*
		 * Se setea a 0 el delay para que cuando haya cambio de cancion no haya posibilidad 
		 * de que se vea un pocode barra de la otra cancion
		 */
		
		timer.schedule(task, 0, 1000);

	}
	
	/**
	 * Detiene la reproducción actual del audio y detiene el temporizador.
	 */
	public void stop () {
		running=false;
		if (timer!=null) {
			timer.cancel();
		}
		if (task!=null) {
			task.cancel();
		}
		mediaPlayer.stop();
	}
	
	/**
	 * Cancela el temporizador actual.
	 */
	public void cancelTimer() {
		running=false;
		timer.cancel();
	}

}
