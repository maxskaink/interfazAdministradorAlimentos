package code.controllers;

import code.main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class menuPrincipal {

    @FXML
    public Label labelErrores;
    public Pane containerState;
    public Label labelSeccionActual;
    public Text textInfoAccion;
    private GridPane extraerComponente(String nombreComponente){
        FXMLLoader loader = (new FXMLLoader(main.class.getResource("componente"+nombreComponente + ".fxml")));
        try {
            return loader.load();
        } catch (IOException e) {throw new RuntimeException(e);}
    }
    public void abrirListaCompras(){
        containerState.getChildren().clear();
        labelSeccionActual.setText("Lista Compras");
        labelSeccionActual.setPadding(new Insets(0,20,0,60));

        containerState.getChildren().add( extraerComponente("ListaCompras") );

    }
    public void abrirAlimentosDisponibles(){
        containerState.getChildren().clear();
        labelSeccionActual.setText("Alimentos");
        labelSeccionActual.setPadding(new Insets(0,20,0,60));

        containerState.getChildren().add( extraerComponente("AlimentosDisponibles") );

    }
    public void regresarMenuPrincipal(){
        labelSeccionActual.setText("Inicio");
        containerState.getChildren().clear();
    }

    public void cerrarSecion() throws IOException {
        main.setRoot("paginaSeleccionUsuario");
    }

    public void infoMenuP(){
        informarAccionBoton(0);
    }
    public void infoBoton1(){
        informarAccionBoton(1);
    }
    public void infoBoton2(){
        informarAccionBoton(2);
    }
    public void infoBoton3(){
        informarAccionBoton(3);
    }
    public void infoBoton4(){
        informarAccionBoton(4);
    }
    public void infoBoton5(){
        informarAccionBoton(5);
    }
    public void infoBoton6(){
        informarAccionBoton(6);
    }
    private void informarAccionBoton(int buton){
        switch (buton) {
            case 0 -> textInfoAccion.setText("Este boton lo llevara a la seccion menu principal");
            case 1 -> textInfoAccion.setText("Presione para poder observar su lista de compras ");
            case 2 -> textInfoAccion.setText("Presione para poder observar la lista de alimentos disponibles en los compartimentos, ademas de poder agregar o conusmir alimentos ");
            case 3 -> textInfoAccion.setText("Presione para agregar o eliminar contenedores del administrador");
            case 4 -> textInfoAccion.setText("Presione para poder observar la lista por semana o modificarla");
            case 5 -> textInfoAccion.setText("Presione para poder agregar,cambiar o eliminar a los usuarios dentro del admonistrador");
            case 6 -> textInfoAccion.setText("Presione para cerrar secion :)");

        }
    }

}
