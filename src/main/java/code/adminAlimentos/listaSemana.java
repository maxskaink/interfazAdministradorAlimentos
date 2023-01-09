package code.adminAlimentos;

import java.util.ArrayList;

public class listaSemana {

    private String descripcion;
    private ArrayList<Alimento> alimentosRef= new ArrayList<Alimento>();
    public listaSemana( String desc ){
        this.descripcion = desc;
    }
    public void quitarAlimentos(String nombreAlimento){
        int i;
        if(alimentosRef.size()== 0) throw new miError("No se puede quitar elementos de una lista vacia");
        for(i= 0; i< (alimentosRef.size()); i++){
            if(alimentosRef.get(i).getNombre().equals(nombreAlimento)){
                alimentosRef.remove(i);
            }
        }
    }
    public void agregarAlimento(Alimento objAlimento){
        this.alimentosRef.add(objAlimento);
    }

    
}
