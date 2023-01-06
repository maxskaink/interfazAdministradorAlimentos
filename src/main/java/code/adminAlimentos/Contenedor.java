package code.adminAlimentos;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Contenedor {

    private String nombre;
    private ArrayList<Alimento> alimentosDisponibles = new ArrayList<Alimento>();

    private administradorAlimentos adminAlimentos;

    public Contenedor(String nombre, administradorAlimentos admin){
        this.nombre = nombre;
        this.adminAlimentos = admin;
    }


    public void agregarAlimento(Alimento objAlimento){
        AtomicBoolean existeAlimento= new AtomicBoolean(false);

        int posicionAlimentoExistente = existeAlimentoEn(objAlimento, this.alimentosDisponibles);

        if(posicionAlimentoExistente != 0){
            existeAlimento.set(true);

            this.alimentosDisponibles.get(posicionAlimentoExistente-1).aumentarCantidad(objAlimento.getCantidad());
        }else{
            if(existeAlimentoEn(objAlimento, adminAlimentos.getAlimentosDisponibles()) != 0){
                existeAlimento.set(true);
                this.alimentosDisponibles.add( objAlimento );
                this.alimentosDisponibles.get( this.alimentosDisponibles.size()-1 ).asignarContenedor(this);

            }
        }

        if(!existeAlimento.get()) throw new miError("El alimento " + objAlimento.getNombre() + " debe esstar en el administrador");
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
    public void quitarAlimento(Alimento alimentoAQuitar){

            for (int contador= 0; contador < alimentosDisponibles.size(); contador++ ){
                Alimento alimentoActual = alimentosDisponibles.get(contador);
                if(alimentoActual.getNombre().equals(alimentoAQuitar.getNombre())){
                    alimentoActual.consumirCantidad(alimentoAQuitar.getCantidad());
                }
                if(alimentoActual.getCantidad()== 0){
                    alimentosDisponibles.remove(contador);
                }
                this.adminAlimentos.getUsuarioEnSecion().consumirAlimento(alimentoAQuitar);
            }
    }


    //GET AND SETTER
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Alimento> getAlimentosDisponibles() {
        return alimentosDisponibles;
    }

    public void setAlimentosDisponibles(ArrayList<Alimento> alimentosDisponibles) {
        this.alimentosDisponibles = alimentosDisponibles;
    }
}
