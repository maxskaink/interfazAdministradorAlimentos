package code.controllers;

import code.adminAlimentos.miError;
import code.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

import java.io.IOException;

public class componenteAlimentosAdministrador {

    @FXML
    public Label labelErrores;
    public VBox containerEspacios;

    public Button botonAgregarAlimentos;
    public void initialize(){
        containerEspacios.getChildren().clear();

        if(main.adminApp.getAlimentosDisponibles().size()== 0) return;

            HBox contenedorHBOX = new HBox();
            contenedorHBOX.setAlignment(Pos.CENTER_LEFT);
            contenedorHBOX.setPrefWidth(100);
            contenedorHBOX.setPrefHeight(479);
            contenedorHBOX.setSpacing(15);
            contenedorHBOX.setPadding(new Insets(10));

            Label nombreContenedor = new Label("Alimentos: ");
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

            main.adminApp.getAlimentosDisponibles().forEach( alimento -> {

                Label alimentoLabel = new Label("- "+ alimento.getNombre() + " Categoria:: " + alimento.getCategoria());

                alimentoLabel.setFont( Font.font("Berlin Sans FB", 15));

                alimentoLabel.setOnMouseClicked( a ->{
                    try{
                        Popup popup = new Popup();
                        Label label = new Label("Esta seguro que quiere borrar " + alimento.getNombre() + " ?");
                        Button confirmButton = new Button("Confirmar");
                        confirmButton.setOnAction(event -> {
                            main.adminApp.borrarAlimentosAdministrador(alimento.getNombre());
                            popup.hide();
                            this.initialize();
                        });

                        Button cancelButton = new Button("Cancelar");
                        cancelButton.setOnAction(event -> popup.hide());

                        HBox buttons = new HBox(confirmButton, cancelButton);
                        VBox root = new VBox(label, buttons);
                        root.setAlignment(Pos.CENTER);
                        root.setPrefWidth(300);
                        root.setPrefHeight(100);
                        root.setSpacing(15);
                        root.setPadding(new Insets(10));
                        root.setStyle("-fx-background-color: #024959;");

                        label.setTextFill(Color.web("#F2E3D5"));
                        buttons.setAlignment(Pos.CENTER);
                        buttons.setSpacing(10);

                        popup.getContent().add(root);
                        popup.show(main.stage);

                    }catch (miError e){
                        labelErrores.setText(e.msg);
                    }

                });

                listaAlimentos.getChildren().add(alimentoLabel);
            });

            contenedorHBOX.getChildren().addAll(nombreContenedor, listaAlimentos);

            containerEspacios.getChildren().add(contenedorHBOX);

    }

    public void agregarAlimentos(){

        containerEspacios.getChildren().clear();

        containerEspacios.getChildren().add(extraerFormulario("crearAlimentoAdmin"));

    }

    private GridPane extraerFormulario(String nombreComponente){
        FXMLLoader loader = (new FXMLLoader(main.class.getResource("formularios/"+ nombreComponente + ".fxml")));
        try {
            return loader.load();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

}
