package code.controllers;

import code.adminAlimentos.Alimento;
import code.adminAlimentos.miError;
import code.main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class formularioCrearAlimentosAdmin {

    @FXML
    public TextField textFieldNombre;
    public TextField textFieldCategoria;
    public Label labelErrores;

    public void cancelarFormulario() throws IOException {
        main.setRoot("menuPrincipal");
    }

    public void agregarAlimento() throws IOException {
        Alimento nuevoAlimento = new Alimento( textFieldNombre.getText(), textFieldCategoria.getText(), 0 );

        try{
            main.adminApp.agregarAlimentoAdministrador(nuevoAlimento);
            main.setRoot("menuPrincipal");
        }catch (miError e){
            labelErrores.setText(e.msg);
        }

    }

}
