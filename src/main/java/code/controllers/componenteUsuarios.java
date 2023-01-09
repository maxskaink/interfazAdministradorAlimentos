package code.controllers;

import code.adminAlimentos.Usuario;
import code.adminAlimentos.miError;
import code.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

import java.io.IOException;

public class componenteUsuarios {

    @FXML
    public VBox containerUsuarios;
    public Label labelErrores;
    public void initialize(){
        containerUsuarios.getChildren().clear();

        main.adminApp.getUsuarios().forEach(usuario ->{
            Label nombreAlimento = new Label(usuario.getNombre());

            nombreAlimento.setAlignment(Pos.CENTER);
            nombreAlimento.setMaxHeight(1.7976931348623157E308);
            nombreAlimento.setMaxWidth(1.7976931348623157E308);
            nombreAlimento.setStyle("-fx-background-radius: 5; -fx-background-color: #3CA6A6;");
            nombreAlimento.setTextAlignment(TextAlignment.CENTER);
            nombreAlimento.setTextFill(Paint.valueOf("#f2e3d5"));
            nombreAlimento.setPadding(new Insets(10));
            nombreAlimento.setFont(Font.font("Berlin Sans FB",29));

            nombreAlimento.setOnMouseMoved(a->{
                labelErrores.setText("Presione para borrar el usuario");
            });

            nombreAlimento.setOnMouseClicked(a->{

                try{
                    Popup popup = new Popup();
                    Label label = new Label("Esta seguro que quiere borrar el usuario " + usuario.getNombre() + " ?");
                    Button confirmButton = new Button("Confirmar");
                    confirmButton.setOnAction(event -> {
                        main.adminApp.eliminarUsuarios(usuario);

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

            containerUsuarios.getChildren().add(nombreAlimento);
        } );

    }

    public void agregarUsuario(){

        containerUsuarios.getChildren().clear();

        containerUsuarios.getChildren().add(extraerFormulario("crearUsuario"));

    }

    private GridPane extraerFormulario(String nombreComponente){
        FXMLLoader loader = (new FXMLLoader(main.class.getResource("formularios/"+ nombreComponente + ".fxml")));
        try {
            return loader.load();
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
