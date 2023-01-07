package code.controllers;

import code.adminAlimentos.miError;
import code.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class formularioCrearContenedor {

    @FXML

    public TextField textFieldNombre;
    public Label labelErrores;

    public void agregarContenedor(){

        try{
            main.adminApp.agregarContenedor(textFieldNombre.getText());
            main.setRoot("menuPrincipal");
        }catch (miError e){
            labelErrores.setText(e.msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void cancelarFormulario(MouseEvent mouseEvent) throws IOException {

        main.setRoot("menuPrincipal");
    }

}
