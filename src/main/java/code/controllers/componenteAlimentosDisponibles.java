package code.controllers;

import code.adminAlimentos.Alimento;
import code.adminAlimentos.Contenedor;
import code.adminAlimentos.miError;
import code.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

import java.io.IOException;
import java.util.ArrayList;

public class componenteAlimentosDisponibles {

    @FXML
    public Label labelErrores;
    public VBox containerEspacios;
    public void initialize(){
        containerEspacios.getChildren().clear();
        int contador=0;

        for (Contenedor contenedor : main.adminApp.getContenedores()) {
            HBox contenedorHBOX = new HBox();
            contenedorHBOX.setAlignment(Pos.CENTER_LEFT);
            contenedorHBOX.setPrefWidth(100);
            contenedorHBOX.setPrefHeight(100);
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

            if(contenedor.getAlimentosDisponibles().size()==0){

                Label alimentoLabel = new Label("El contenedor está vacio, por favor agregue presionando el boton de añadir");

                alimentoLabel.setFont( Font.font("Berlin Sans FB", 15));

                listaAlimentos.getChildren().add(alimentoLabel);
            }

            int finalContador = contador;
            contenedor.getAlimentosDisponibles().forEach(alimento -> {

                Label alimentoLabel = new Label("- "+ alimento.getNombre() + " :: Cantidad -> " + alimento.getCantidad());
                alimentoLabel.setFont( Font.font("Berlin Sans FB", 15));
                alimentoLabel.setOnMouseClicked(a->{
                    try{
                        Popup popup = new Popup();
                        Label label = new Label("Esta seguro que quiere consumir " + alimento.getNombre() + " ?");
                        Button confirmButton = new Button("Confirmar");
                        TextField cantidadAlimento = new TextField();

                        confirmButton.setOnAction(event -> {
                            int cantidadConsumir;
                            try{
                                cantidadConsumir =  Integer.parseInt(cantidadAlimento.getText());
                                Alimento alimentoAConsumir = new Alimento( alimento.getNombre(), alimento.getCategoria(),cantidadConsumir);
                                main.adminApp.consumirAlimentoDeContenedor(finalContador,alimentoAConsumir);
                                popup.hide();
                                this.initialize();
                            }catch ( NumberFormatException e){
                                label.setText("Por favor ingrese una cantidad valida");
                            }catch (miError e){
                                label.setText("Por favor ingrese una cantidad existente o valida");
                            }

                        });

                        Button cancelButton = new Button("Cancelar");
                        cancelButton.setOnAction(event -> popup.hide());

                        HBox buttons = new HBox(confirmButton, cancelButton);
                        Label avisoCantidad = new Label("Cantidad");
                        avisoCantidad.setTextFill(Color.web("#F2E3D5"));

                        HBox infoCantidad = new HBox(avisoCantidad,cantidadAlimento );
                        infoCantidad.setAlignment(Pos.CENTER);
                        infoCantidad.setSpacing(10);

                        VBox root = new VBox(label,infoCantidad,buttons);
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

                alimentoLabel.setOnMouseMoved( e -> {
                    labelErrores.setText("Presione para consumir alimentos");
                });

                listaAlimentos.getChildren().add(alimentoLabel);
            });

            contenedorHBOX.getChildren().addAll(nombreContenedor, listaAlimentos);

            containerEspacios.getChildren().add(contenedorHBOX);
        contador++;
        }

    }

    public void agregarAlimento(){

        containerEspacios.getChildren().clear();

        containerEspacios.getChildren().add( extraerFormulario("crearAlimentoContenedor") );

    }
    private GridPane extraerFormulario(String nombreComponente){
        FXMLLoader loader = (new FXMLLoader(main.class.getResource("formularios/"+ nombreComponente + ".fxml")));
        try {
            return loader.load();
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
