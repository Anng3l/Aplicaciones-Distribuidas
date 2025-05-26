package clase;

import lombok.Getter;

@Getter
public class Persona {

    @Getter private int clave;
    @Getter private String nombre;
    @Getter private String correo;
    @Getter private String cargo;
    @Getter private double sueldo;

    public Persona(int clave, String nombre, String correo, String cargo, double sueldo) {
        this.clave = clave;
        this.nombre = nombre;
        this.correo = correo;
        this.cargo = cargo;
        this.sueldo = sueldo;
    }


    public int getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSueldo() {
        return sueldo;
    }
}
