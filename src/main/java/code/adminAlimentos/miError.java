package code.adminAlimentos;

public class miError extends RuntimeException{

    public String msg;

    public miError(String msg){
        this.msg=msg;
    }

}
