package code.controllers;

import code.main;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;

public class componenteListaCompras {

    @FXML
    public VBox containerAlimentos;
    public void initialize(){
        containerAlimentos.getChildren().clear();

        main.adminApp.consultarListaDeCompras().forEach(alimento ->{
            Label nombreAlimento = new Label(alimento.getNombre() + " ::Cantidad -> " + alimento.getCantidad());

            nombreAlimento.setAlignment(Pos.CENTER);
            nombreAlimento.setMaxHeight(1.7976931348623157E308);
            nombreAlimento.setMaxWidth(1.7976931348623157E308);
            nombreAlimento.setStyle("-fx-background-radius: 5; -fx-background-color: #3CA6A6;");
            nombreAlimento.setTextAlignment(TextAlignment.CENTER);
            nombreAlimento.setTextFill(Paint.valueOf("#f2e3d5"));
            nombreAlimento.setPadding(new Insets(10));
            nombreAlimento.setFont(Font.font("Berlin Sans FB",29));

            containerAlimentos.getChildren().add(nombreAlimento);
        } );

    }
}
