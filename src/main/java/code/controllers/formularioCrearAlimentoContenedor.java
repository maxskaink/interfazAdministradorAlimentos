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

public class formularioCrearAlimentoContenedor {
    @FXML
    public TextField textFieldCantidad;
    public Label labelErrores;
    public ComboBox seleccionContenedor;
    public ComboBox seleccionAlimento;

    public void initialize(){

        main.adminApp.getContenedores().forEach(contenedor -> {
            seleccionContenedor.getItems().add(contenedor.getNombre());
        });

        main.adminApp.getAlimentosDisponibles().forEach( alimento -> {
            seleccionAlimento.getItems().add(alimento.getNombre());
        });

    }
    public void cancelarFormulario(MouseEvent mouseEvent) throws IOException {
        main.setRoot("menuPrincipal");
    }

    public void agregarAlimento(MouseEvent mouseEvent) {
        int cantidadAlimento;
        if(seleccionContenedor.getSelectionModel().getSelectedItem()==null){
            labelErrores.setText("Por favor seleccione el contenedor al que va a agregar el alimento");
            return;
        }
        if(seleccionAlimento.getSelectionModel().getSelectedItem()==null){
            labelErrores.setText("Primero agregue alimentos al adminsitrador para poder agregarlo al contenedor");
            return;
        }

        try{
            cantidadAlimento = Integer.parseInt( textFieldCantidad.getText() );
            Alimento refALimentoAdmin = main.adminApp.getAlimentosDisponibles().get(seleccionAlimento.getSelectionModel().getSelectedIndex());
            Alimento alimentoNuevo = new Alimento( refALimentoAdmin.getNombre(),refALimentoAdmin.getCategoria(), cantidadAlimento );

            int posicionContenedor = seleccionContenedor.getSelectionModel().getSelectedIndex();
            main.adminApp.getContenedores().get(posicionContenedor).agregarAlimento(alimentoNuevo);
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
