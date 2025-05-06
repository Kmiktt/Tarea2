//falta por agregar implements invitable
public class Empleado implements Invitable{
    private String id;
    private String apellidos;
    private String nombre;
    private String correo;

    public Empleado(String x, String y, String z, String c){
        id=x;
        apellidos=y;
        nombre=z;
        correo=c;
    }
    public String getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellidos(){
        return apellidos;
    }

    public String getCorreo(){
        return correo;
    }
    //quiza dejarlo en protected, pero la opcion de cambiar correo y nombre a veces se hace necesaria
    public void setCorreo(String x){
        correo=x;
    }

    public void setNombre(String x){
        nombre=x;
    }

    public void Invitar(){

    }
}
