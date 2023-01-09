package code.controllers;

import code.adminAlimentos.Alimento;
import code.adminAlimentos.Usuario;
import code.adminAlimentos.administradorAlimentos;
import code.adminAlimentos.miError;
import code.main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class paginaSeleccionUsuario {

    @FXML
    public VBox containerUser;

    public void initialize(){

        containerUser.getChildren().clear();

        int seleccionImagen=1;

            HBox filasUsuario = new HBox();

            filasUsuario.setPrefHeight(150);
            filasUsuario.setPrefWidth(970);
            filasUsuario.setSpacing(10);
            filasUsuario.setPadding(new Insets(15));
            filasUsuario.setAlignment(Pos.CENTER);

        for (Usuario usuario : main.adminApp.getUsuarios()) {

                VBox contenedorUsuario= new VBox();
                contenedorUsuario.prefHeight(120);
                contenedorUsuario.prefWidth(204);
                contenedorUsuario.setSpacing(10);
                contenedorUsuario.setStyle("-fx-background-radius: 20; -fx-background-color: #22BABB; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 20;");
                contenedorUsuario.setPadding(new Insets(5,40,5,40));

                contenedorUsuario.setOnMouseClicked(a -> {

                        main.adminApp.iniciarSecion(usuario.getNombre());
                    try {
                        main.setRoot("menuPrincipal");

                        //TODO borrar esto

                        main.adminApp.agregarContenedor("Nevera");
                        main.adminApp.agregarAlimentoAdministrador(new Alimento("manzana", "fruta", 0));
                        main.adminApp.getContenedores().get(0).agregarAlimento(new Alimento("manzana", "fruta", 15));

                    } catch (miError e) {
                        System.out.println(e.msg);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                Label nombreUsuario = new Label(usuario.getNombre());
                nombreUsuario.setTextFill(Color.WHITE);
                nombreUsuario.setFont(Font.font("Britannic Bold", 26));
                nombreUsuario.setTextAlignment(TextAlignment.CENTER);

                ImageView imagenUsuario = new ImageView();
                imagenUsuario.setBlendMode(BlendMode.SRC_ATOP);
                imagenUsuario.setFitHeight(96);
                imagenUsuario.setFitWidth(130);
                imagenUsuario.setPickOnBounds(true);
                imagenUsuario.setPreserveRatio(true);
                if(seleccionImagen==1){
                    imagenUsuario.setImage( new Image( getClass().getResource("/img/hombre.png").toString() ));
                    seleccionImagen=2;
                }else{
                    imagenUsuario.setImage( new Image(getClass().getResource("/img/mujer.png").toString() ));
                    seleccionImagen=1;
                }

                contenedorUsuario.getChildren().addAll(nombreUsuario,imagenUsuario);
                filasUsuario.getChildren().add(contenedorUsuario);
        }
        containerUser.getChildren().add(filasUsuario);
    }

}