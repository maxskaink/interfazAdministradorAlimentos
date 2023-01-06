package code.controllers;

import code.adminAlimentos.Alimento;
import code.adminAlimentos.Contenedor;
import code.adminAlimentos.miError;
import code.main;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class componenteAlimentosDisponibles {

    @FXML
    public Label labelErrores;
    public VBox containerEspacios;
    public void initialize(){
        containerEspacios.getChildren().clear();

        main.adminApp.getContenedores().forEach( contenedor -> {
            HBox contenedorHBOX = new HBox();
            contenedorHBOX.setAlignment(Pos.CENTER_LEFT);
            contenedorHBOX.setPrefWidth(100);
            contenedorHBOX.setPrefHeight(479);
            contenedorHBOX.setSpacing(15);
            contenedorHBOX.setPadding(new Insets(10));

            Label nombreContenedor = new Label(contenedor.getNombre());
            nombreContenedor.setAlignment(Pos.CENTER);
            nombreContenedor.maxHeight(1.7976931348623157E308);
            nombreContenedor.maxWidth(1.7976931348623157E308);
            nombreContenedor.prefHeight(80);
            nombreContenedor.prefWidth(155);
            nombreContenedor.setStyle("-fx-background-radius: 5; -fx-background-color: #024959;");
            nombreContenedor.setTextAlignment(TextAlignment.CENTER);
            nombreContenedor.setTextFill(Paint.valueOf("#f2e3d5"));
            nombreContenedor.setPadding(new Insets(10));
            nombreContenedor.setFont(Font.font("Berlin Sans FB", 29));

            VBox listaAlimentos = new VBox();
            listaAlimentos.setAlignment(Pos.CENTER_LEFT);
            listaAlimentos.setPrefHeight(80);
            listaAlimentos.setPrefWidth(291);
            listaAlimentos.setSpacing(15);
            listaAlimentos.setPadding(new Insets(10, 20, 10, 10));

            contenedor.getAlimentosDisponibles().forEach( alimento -> {

                Label alimentoLabel = new Label("- "+ alimento.getNombre() + " :: Cantidad -> " + alimento.getCantidad());

                alimentoLabel.setFont( Font.font("Berlin Sans FB", 15));

                listaAlimentos.getChildren().add(alimentoLabel);
            });

            contenedorHBOX.getChildren().addAll(nombreContenedor, listaAlimentos);

            containerEspacios.getChildren().add(contenedorHBOX);
        } );

    }
}
