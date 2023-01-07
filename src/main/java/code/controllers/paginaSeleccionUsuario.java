package code.controllers;

import code.adminAlimentos.Alimento;
import code.adminAlimentos.Usuario;
import code.adminAlimentos.administradorAlimentos;
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

    private ArrayList<String> usuarios = new ArrayList<String>();
    @FXML
    public VBox containerUser;

    public void initialize(){
        administradorAlimentos adminApp = main.adminApp;

        for (Usuario usuarioActual : adminApp.getUsuarios()) {
            usuarios.add(usuarioActual.getNombre());
        }

        containerUser.getChildren().clear();

        int contadorColumnas = usuarios.size()/5;
        if(contadorColumnas == usuarios.size()/5) contadorColumnas++;
        int contadorUsuarios=0, seleccionImagen=1;

        for (int contador= 0; contador < contadorColumnas; contador++){

            HBox filasUsuario = new HBox();

            filasUsuario.setPrefHeight(150);
            filasUsuario.setPrefWidth(970);
            filasUsuario.setSpacing(10);
            filasUsuario.setPadding(new Insets(15));
            filasUsuario.setAlignment(Pos.CENTER);

            while (contadorUsuarios < (contador+1)*5 && contadorUsuarios<usuarios.size()){

                VBox contenedorUsuario= new VBox();
                contenedorUsuario.prefHeight(120);
                contenedorUsuario.prefWidth(204);
                contenedorUsuario.setSpacing(10);
                contenedorUsuario.setStyle("-fx-background-radius: 20; -fx-background-color: #22BABB; -fx-border-width: 2; -fx-border-color: black; -fx-border-radius: 20;");
                contenedorUsuario.setPadding(new Insets(5,40,5,40));

                int finalContador = contador;
                contenedorUsuario.setOnMouseClicked(a -> {
                    adminApp.iniciarSecion(finalContador);
                    try {
                        main.setRoot("menuPrincipal");

                        //TODO borrar esto

                        main.adminApp.agregarContenedor("Nevera");
                        main.adminApp.agregarAlimentoAdministrador(new Alimento("manzana", "fruta", 0));
                        main.adminApp.getContenedores().get(0).agregarAlimento(new Alimento("manzana", "fruta", 15));

                    } catch (IOException e) {

                    }
                });

                Label nombreUsuario = new Label(usuarios.get(contadorUsuarios));
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

                contadorUsuarios++;
            }

            containerUser.getChildren().add(filasUsuario);

        }

    }

}