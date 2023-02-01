package code;

import code.adminAlimentos.NIño;
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
    public static Stage stage;
    @Override

    public void start(Stage stage) throws IOException {
        main.stage = stage;
        adminApp = new administradorAlimentos();

        //TODO borrar
        adminApp.iniciarSecion("UsuarioAdmin");
        adminApp.añadirUsuario(new Usuario("Miguel", 0 , 18));
        adminApp.añadirUsuario(new NIño("Santiago",0, 5,3));

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

//TODO accion para administrar la lista por semana y tambien para ver la lista de compra