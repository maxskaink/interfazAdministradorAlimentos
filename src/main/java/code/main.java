package code;

import code.adminAlimentos.Usuario;
import code.adminAlimentos.administradorAlimentos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    public static administradorAlimentos adminApp;
    public static Scene paginaPrincipal;
    @Override

    public void start(Stage stage) throws IOException {

        adminApp = new administradorAlimentos();

        paginaPrincipal = cargarFXML("paginaSeleccionUsuario");

        stage.setTitle("Administrador de alimentos");
        stage.setScene(paginaPrincipal);
        stage.show();
    }

    public Scene cargarFXML(String nombreArchivo)throws IOException{
        FXMLLoader loader = (new FXMLLoader(main.class.getResource(nombreArchivo + ".fxml")));
        return new Scene(loader.load());
    }

    public static void setRoot(String nombreFXML) throws  IOException{
        paginaPrincipal.setRoot( (new FXMLLoader(main.class.getResource(nombreFXML + ".fxml")).load() ));
    }

    public static void main(String[] args) {
        launch();
    }
}

//TODO boton para cerrar cesion
//TODO accion para consumir y agregar alimentos
//TODO accion para agregar o eliminar contenedores
//TODO accion para administrar los alimentos del administrador
//TODO accion para administrar Usuarios
//TODO accion para administrar la lista por semana y tambien para ver la lista de compra