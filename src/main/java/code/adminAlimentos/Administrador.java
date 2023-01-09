package code.adminAlimentos;

public class   Administrador  extends Usuario{

    public Administrador(String nombre, int cantidad, int edad) {
        super(nombre, cantidad, edad);
    }



    //GIT AND GETTERS

    public String getRol(){
        return "ADMIN";
    }
}
