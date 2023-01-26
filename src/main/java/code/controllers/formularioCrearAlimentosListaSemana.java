package code.controllers;

import code.adminAlimentos.Alimento;
import code.adminAlimentos.miError;
import code.main;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class formularioCrearAlimentosListaSemana {
    @FXML
    public TextField textFieldCantidad;
    public Label labelErrores;
    public ComboBox seleccionAlimento;

    public void initialize(){
        main.adminApp.getAlimentosDisponibles().forEach( alimento -> {
            seleccionAlimento.getItems().add(alimento.getNombre());
        });

    }
    public void cancelarFormulario(MouseEvent mouseEvent) throws IOException {
        main.setRoot("menuPrincipal");
    }

    public void agregarAlimento(MouseEvent mouseEvent) {
        int cantidadAlimento;

        if(seleccionAlimento.getSelectionModel().getSelectedItem()==null){
            labelErrores.setText("Primero agregue alimentos al adminsitrador para poder agregarlo al contenedor");
            return;
        }

        try{
            cantidadAlimento = Integer.parseInt( textFieldCantidad.getText() );
            Alimento refALimentoAdmin = main.adminApp.getAlimentosDisponibles().get(seleccionAlimento.getSelectionModel().getSelectedIndex());
            Alimento alimentoNuevo = new Alimento( refALimentoAdmin.getNombre(),refALimentoAdmin.getCategoria(), cantidadAlimento );

            main.adminApp.agregarComidaListaSemana(alimentoNuevo);
            main.setRoot("menuPrincipal");
        }catch (NumberFormatException e){
            labelErrores.setText("Por favor en cantidad ingrese solo numeros");
        }catch ( miError e){
            labelErrores.setText(e.msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
