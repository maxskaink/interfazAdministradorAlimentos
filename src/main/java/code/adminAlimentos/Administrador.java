package code.adminAlimentos;

public class   Administrador  extends Usuario{
    public Administrador(String nom, int cant, int ed) {
        super(nom, cant, ed);

    }
    public String getRol(){
        return "ADMIN";
    }
}
