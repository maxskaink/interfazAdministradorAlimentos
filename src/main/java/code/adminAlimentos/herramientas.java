package code.adminAlimentos;

import java.util.ArrayList;

public abstract class herramientas {
    public void comparadorNombreContenedor(String nombre, ArrayList<Contenedor> contenedores){
        int i;
        String comparador = nombre.toUpperCase();

        for (i = 0; i < contenedores.size(); i++) {
            String nombreMayus = contenedores.get(i).getNombre().toUpperCase();
            if (nombreMayus.equals(comparador)) {
                throw new miError("YA EXISTE UN contenedor CON UN NOMBRE SIMILAR A ESTE");
            }
        }


    }

    public int existeAlimentoEn(Alimento alimentoConsulta, ArrayList<Alimento> alimentosD){
        if(alimentosD.size()==0) return 0;
        int posicionAlimento=1;
        for (Alimento alimento : alimentosD) {
            if(alimento.getNombre().equals( alimentoConsulta.getNombre())) return posicionAlimento;
            posicionAlimento++;
        }
        return 0;
    }
}
