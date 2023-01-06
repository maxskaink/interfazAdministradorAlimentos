package code.adminAlimentos;

import java.util.ArrayList;

public class Usuario {

    private String nombre;
    private int cantComida;
    private int edad;
    private ArrayList<Alimento>  alimentosConsumidos = new ArrayList<Alimento>();


    public Usuario (String nom, int cant, int ed){
        this.cantComida = cant;
        this.edad = ed;
        this.nombre = nom;
    }

    //GET AND SETTER
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCantComida() {
        return cantComida;
    }

    public void consumirAlimento(Alimento alimentoAConsumir){

        int posicionAlimentoExistente = existeAlimentoEn(alimentoAConsumir, this.alimentosConsumidos);

        if (posicionAlimentoExistente != 0)
            this.alimentosConsumidos.get(posicionAlimentoExistente-1).aumentarCantidad(alimentoAConsumir.getCantidad());
        else
            this.alimentosConsumidos.add( alimentoAConsumir );

    }
    private int existeAlimentoEn(Alimento alimentoConsulta, ArrayList<Alimento> alimentosD){
        if(alimentosD.size()==0) return 0;
        int posicionAlimento=1;
        for (Alimento alimento : alimentosD) {
            if(alimento.getNombre().equals( alimentoConsulta.getNombre())) return posicionAlimento;
            posicionAlimento++;
        }
        return 0;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getRol(){
        return "USUARIO";
    }

    public int getCantDulces() {
        return 0;
    }
}
