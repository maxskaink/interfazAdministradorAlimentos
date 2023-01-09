package code.controllers;

import code.adminAlimentos.Administrador;
import code.adminAlimentos.NIño;
import code.adminAlimentos.Usuario;
import code.adminAlimentos.miError;
import code.main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class formularioCrearUsuario {

    @FXML
    public TextField textFieldNombre;
    public TextField textFieldEdad;
    public Label labelErrores;
    public ComboBox seleccionRol;

    public void initialize(){
        seleccionRol.getItems().add("Administrador");
        seleccionRol.getItems().add("Niño");
        seleccionRol.getItems().add("Usuario");
    }

    public void cancelarFormulario() throws IOException {
        main.setRoot("menuPrincipal");
    }

    public void agregarAlimento() throws IOException {

        int edad;
        try {
            edad = Integer.parseInt( textFieldEdad.getText() );
        }catch (NumberFormatException e){
            labelErrores.setText("Ingrese en 'edad' solo numeros o algo valido");
            return;
        }

        try{

        switch (seleccionRol.getSelectionModel().getSelectedIndex()){
            case 0 -> main.adminApp.añadirUsuario(new Administrador( textFieldNombre.getText(),0,edad ));
            case 1 -> main.adminApp.añadirUsuario(new NIño(textFieldNombre.getText(),0, edad,0));
            case 2 -> main.adminApp.añadirUsuario( new Usuario( textFieldNombre.getText(),0,edad ));
        }

        }catch (miError e){
            labelErrores.setText(e.msg);
        }
        main.setRoot("menuPrincipal");
    }

}
