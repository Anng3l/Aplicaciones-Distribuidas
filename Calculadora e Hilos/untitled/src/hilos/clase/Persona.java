package hilos.clase;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Getter @Setter private String nombre;
    @Getter @Setter private String fechaDeNacimiento;
    @Getter @Setter private String direccion;
    @Getter @Setter private int edad;
    @Getter @Setter private String  genero;

}
