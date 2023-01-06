package code.adminAlimentos;

public class Alimento  {

    private String nombre;
    private int cantidad;
    private String categoria;
    private Contenedor contenedorPadre;

    public Alimento(String nombre, String categoria, int cantidad){
        this.setNombre(nombre);
        this.setCantidad(cantidad);
        this.setCategoria(categoria);
    }

    public void asignarContenedor(Contenedor contenedor){
        this.contenedorPadre = contenedor;
    }

    public void aumentarCantidad(int cantidadAdicional){
        this.cantidad += cantidadAdicional;
    }

    public void consumirCantidad(int cantidadAConsumir){
        int cantidadTotal = this.cantidad - cantidadAConsumir;
        if(cantidadTotal<0 ||cantidadAConsumir < 0 ){
            throw new miError("No puede consumir una cantidad igual a cero, o mayor a la existente");
        }
        this.cantidad = cantidadTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
