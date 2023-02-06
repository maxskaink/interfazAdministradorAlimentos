package code.adminAlimentos;

import java.util.ArrayList;

public class administradorAlimentos {

    private ArrayList<Contenedor> contenedores= new ArrayList<Contenedor>();
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    private ArrayList<Alimento> listasPorSemana = new ArrayList<Alimento>();

    private ArrayList<Alimento> AlimentosDisponibles = new ArrayList<Alimento>();
    private Usuario usuarioEnSecion;

    public administradorAlimentos(){
        this.usuarios.add( new Administrador("UsuarioAdmin",0,99));
    }

    public void iniciarSecion(int posicionUsuario){
        this.usuarioEnSecion = usuarios.get(posicionUsuario);
    }

    public ArrayList<Contenedor> getContenedores(){
        return contenedores;
    }

    public void consumirAlimentoDeContenedor(int posicionContenedor,Alimento alimentoAConsumir){

        if(this.usuarioEnSecion.getRol().equals("NIÑO")){
            if( (this.usuarioEnSecion.getCantDulces() + alimentoAConsumir.getCantidad() > 5 && alimentoAConsumir.getCategoria().equals("dulce"))){
                throw new miError("Un usuario tipo niño no puede consumir mas de 5 dulces");
            }
        }
        contenedores.get(posicionContenedor).quitarAlimento(alimentoAConsumir);
    }

    public void agregarContenedor(String nombreContenedor){
        validarAdministrador("USTED NO ES ADMINISTRADOR");
        comparadorNombreContenedor(nombreContenedor, contenedores);
        contenedores.add(new Contenedor(nombreContenedor, this));
    }
    public void borrarContenedor(Contenedor cont){
        validarAdministrador("USTED NO ES ADMINISTRADOR");
        contenedores.remove(cont);
    }

    public ArrayList<Alimento> getListaPorSemana(){
        return this.listasPorSemana;
    }

    public void borrarAlimentosAdministrador(String nombreAlimento){
        int i, f;

        for(i= 0; i< (AlimentosDisponibles.size()); i++){
            if(AlimentosDisponibles.get(i).getNombre().equals(nombreAlimento)){
                AlimentosDisponibles.remove(i);
            }
            

        }
        for (Contenedor contenedor : this.contenedores) {
            for (Alimento alimento : contenedor.getAlimentosDisponibles()) {
                if(nombreAlimento.equals(alimento.getNombre())){
                    contenedor.getAlimentosDisponibles().remove(alimento);
                    return;
                }
            }
        }

        for (Alimento alimento : this.listasPorSemana){
                if(nombreAlimento.equals(alimento.getNombre())){
                    this.listasPorSemana.remove(alimento);
                    return;
                }
        }




    }
    public void agregarAlimentoAdministrador(Alimento objAlimento){
        if(this.usuarioEnSecion == null) throw new miError("Antes de agregar alimentos al admin inicie secion como admin ");
            if (this.usuarioEnSecion.getRol().equals("ADMIN")){
                    comparadorNombre(objAlimento);
                    this.AlimentosDisponibles.add(objAlimento);

            }else {
                throw new miError("Para poder agregar alimentos al admin debe ser admin");
           }
    }

    public ArrayList<Alimento> getAlimentosDisponibles(){
        return this.AlimentosDisponibles;
    }

    public Usuario getUsuarioEnSecion() {
        return usuarioEnSecion;
    }

    public void añadirUsuario(Usuario usuarioA) {
        validarAdministrador("USTED NO ES ADMIN, no puede agregar usuarios");
        this.usuarios.add(usuarioA);

    }
    public void eliminarUsuarios(Usuario usuarioA){
        validarAdministrador("NO ES ADMINISTRADOR, NO PUEDE QUITAR USUARIOS ");

        if(usuarioA.getNombre().equals("UsuarioAdmin")){
            throw new miError("NO SE PUEDE BORRAR AL PRIMER ADMINISTRADOR");
        }
        boolean seBorroUsuario = this.usuarios.remove(usuarioA);

        if(!seBorroUsuario) throw new miError("No se encontro el usuario para borrar");

    }
    public ArrayList<Usuario> getUsuarios(){ return this.usuarios; }
    public void validarAdministrador(String mensajeError){
        if (!this.usuarioEnSecion.getRol().equals("ADMIN"))
            throw new miError(mensajeError);

    }
    public void agregarComidaListaSemana(Alimento alimentoAgregar){
        validarAdministrador("USTED NO ES ADMINISTRADOR, NO PUEDE AGREGAR COMIDA A LA LISTA ");
        int posicionAlimento = existeAlimentoEn(alimentoAgregar, this.AlimentosDisponibles);

        if(posicionAlimento!=0) listasPorSemana.add(alimentoAgregar);
        else throw new miError("El alimento"+ alimentoAgregar.getNombre() + " no esta disponible");
    }

    public void borrarAlimentoListaSemana(Alimento alimentoEliminar){
        validarAdministrador("USTED NO ES ADMINISTRADOR, NO PUEDE BORAR COMIDA A LA LISTA ");
        if(this.listasPorSemana.size()==0) throw new miError("no se pude borrar elementos de una lista vacia");

        for (Alimento alimentoActual : this.listasPorSemana) {

            if(alimentoActual.getNombre().equals(alimentoEliminar.getNombre())){
                alimentoActual.consumirCantidad(alimentoEliminar.getCantidad());
            }
            if(alimentoActual.getCantidad()== 0){
                listasPorSemana.remove(alimentoActual);
            }
        }

    }

    public void reiniciarListaPorSemana(){
        validarAdministrador("Usted no es administrador");
        for (Alimento alimento : this.listasPorSemana) {
            this.listasPorSemana.remove(alimento);
        }
        System.out.println("La lista por semana se reinicio exitosamente");
    }

    public ArrayList<Alimento> consultarListaDeCompras(){

        ArrayList<Alimento> listaCompra = new ArrayList<Alimento>();

        for (Contenedor contenedorActual : contenedores) {
            for (Alimento alimentoActual : contenedorActual.getAlimentosDisponibles()) {

                int posicionAlimento = existeAlimentoEn(alimentoActual, listasPorSemana);

                if(posicionAlimento!=0){
                    try{
                        Alimento alimentoEncontrado = listasPorSemana.get(posicionAlimento-1);
                        Alimento alimentoTemp = new Alimento(alimentoEncontrado.getNombre(),
                                                             alimentoEncontrado.getCategoria(),
                                                             alimentoEncontrado.getCantidad());
                        alimentoTemp.consumirCantidad(alimentoActual.getCantidad());
                        listaCompra.add(alimentoTemp);
                    }catch (miError e) {

                    }
                }
            }
        }

        boolean alimentoEx = false;
        for (Alimento alimentoSemana : listasPorSemana) {

            for (Alimento alimentoAgregado : listaCompra) {
                if( alimentoSemana.getNombre().equals( alimentoAgregado.getNombre() )){
                    alimentoEx = true;
                }
            }

            if(!alimentoEx){
                listaCompra.add(new Alimento( alimentoSemana.getNombre(), alimentoSemana.getCategoria(), alimentoSemana.getCantidad() ));
            }

            alimentoEx = false;
        }

        return listaCompra;
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

    public void comparadorNombre(Alimento objAlimento){
        int i;
        String comparador = objAlimento.getNombre().toUpperCase();

            for (i = 0; i < this.AlimentosDisponibles.size(); i++) {
                String nombreMayus = this.AlimentosDisponibles.get(i).getNombre().toUpperCase();
                if (nombreMayus.equals(comparador)) {
                    throw new miError("YA EXISTE UN ALIMENTO CON UN NOMBRE SIMILAR A ESTE");
                }
            }


    }
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
    public void iniciarSecion(String nombre){
        for (Usuario usuario : this.usuarios) {
            if(usuario.getNombre().equals(nombre)){
                this.usuarioEnSecion = usuario;
                return;
            }
        }
    }

}
