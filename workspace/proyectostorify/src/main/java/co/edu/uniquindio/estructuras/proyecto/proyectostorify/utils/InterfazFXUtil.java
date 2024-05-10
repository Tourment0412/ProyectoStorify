package co.edu.uniquindio.estructuras.proyecto.proyectostorify.utils;

import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterfazFXUtil {
	
	/**
	 * Metodo que muestra un mensaje
	 * @param titulo Titulo del mensaje
	 * @param encabezado Cabecera del mensaje
	 * @param texto Texto principal del mensaje
	 * @param alerta Alerta del mensaje
	 */
	public static void mostrarMensaje(String titulo,String encabezado,String texto,AlertType alerta) {
		Alert alert = new Alert(alerta);
		alert.setTitle(titulo);
		alert.setHeaderText(encabezado);
		alert.setContentText(texto);
		alert.showAndWait();
	}
	
	/**
	 * Metodo que muestra un mensaje
	 * @param titulo Titulo del mensaje
	 * @param texto Texto principal del mensaje
	 * @param alerta Alerta del mensaje
	 */
	public static void mostrarMensaje(String titulo,String texto,AlertType alerta) {
		Alert alert = new Alert(alerta);
		alert.setTitle(titulo);
		alert.setHeaderText(titulo);
		alert.setContentText(texto);
		alert.showAndWait();
	}
	
	/**
	 * Metodo que muestra un mensaje
	 * @param titulo Titulo del mensaje
	 * @param texto Texto principal del mensaje
	 */
	public static void mostrarMensaje(String titulo,String texto) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(titulo);
		alert.setContentText(texto);
		alert.showAndWait();
	}
	
	/**
	 * Metodo que muestra un mensaje
	 * @param titulo Titulo del mensaje
	 */
	public static void mostrarMensaje(String texto) {
		Alert alert = new Alert(AlertType.NONE);
		alert.setTitle("");
		alert.setHeaderText("");
		alert.setContentText(texto);
		alert.showAndWait();
	}
	
	public static boolean estaCampoVacio(TextField campo) {
		return campo.getText() == null || campo.getText().trim().equals("");
	}
	
	public static boolean estaCampoVacio(ComboBox campo) {
		return campo.getValue() == null || ((String) campo.getValue()).trim().equals("");
	}
	
    /**
     * Bloquea la entrada de texto en un campo de texto y establece el estilo de fondo como gris.
     * @param texto El campo de texto a bloquear.
     */
	public void bloquearEntrada(TextField texto) {
		texto.setEditable(false);
		texto.setStyle("-fx-background-color: #aaaaaa;");
		texto.setText("N/A");
	}
	
    /**
     * Desbloquea la entrada de texto en un campo de texto y restablece el estilo de fondo predeterminado.
     * @param texto El campo de texto a desbloquear.
     */
	public void desbloquearEntrada(TextField texto) {
		texto.setEditable(true);
		texto.setStyle(null);
		texto.setText("");
	}

    /**
     * Bloquea la selección y la entrada de texto en un ComboBox estableciendo el valor predeterminado como "N/A".
     * @param comboBox El ComboBox a bloquear.
     */

	public void bloquearEntrada(ComboBox comboBox) {
		comboBox.setEditable(false);
		comboBox.setValue("N/A");
		comboBox.setDisable(true);
		
	}

	/**
     * Desbloquea la selección y la entrada de texto en un ComboBox restableciendo el valor predeterminado.
     * @param comboBox El ComboBox a desbloquear.
     */
	public void desbloquearEntrada(ComboBox comboBox) {
		comboBox.setValue("");
		comboBox.setDisable(false);	
	}

	public static boolean mostrarConfirmacion(String titulo, String texto) {
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle(titulo);
	    alert.setHeaderText(titulo);
	    alert.setContentText(texto);

	    Optional<ButtonType> resultado = alert.showAndWait();

	    return resultado.isPresent() && resultado.get() == ButtonType.OK;
	}
}
