package code.adminAlimentos;

public class   Administrador  extends Usuario{
    private String rol;
    public Administrador(String nom, int cant, int ed, String rol) {
        super(nom, cant, ed);
        this.rol = rol;

    }



    //GIT AND GETTERS
    public String getRolAdmin() {
        return rol;
    }
    public void setRolAdmin(String rol) {
        this.rol = rol;
    }
    public String getRol(){
        return "ADMIN";
    }
}
