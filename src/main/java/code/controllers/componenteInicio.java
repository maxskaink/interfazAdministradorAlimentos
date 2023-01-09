package code.controllers;

import code.adminAlimentos.Alimento;
import code.main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class componenteInicio {
    @FXML
    public Label labelNombre;
    public Label labelRol;
    public Label labelEdad;
    public VBox containerAlimentos;

    public void initialize() {
        containerAlimentos.getChildren().clear();
        labelNombre.setText(main.adminApp.getUsuarioEnSecion().getNombre());
        labelRol.setText(main.adminApp.getUsuarioEnSecion().getRol());
        labelEdad.setText( Integer.toString(main.adminApp.getUsuarioEnSecion().getEdad()));

        for (Alimento alimento : main.adminApp.getUsuarioEnSecion().getAlimentosConsumidos()) {
            Label nuevoLabel = new Label(alimento.getNombre() + " -> " + alimento.getCantidad());
            nuevoLabel.setFont(Font.font("System Bold", 15));

            containerAlimentos.getChildren().add(nuevoLabel);
        }
    }

}
