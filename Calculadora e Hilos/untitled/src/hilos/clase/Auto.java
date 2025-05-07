package hilos.clase;

import lombok.*;

import javax.swing.*;

@AllArgsConstructor
@NoArgsConstructor
public class Auto extends JFrame {

    @Getter @Setter
    private String marca;
    @Getter @Setter
    private String modelo;
    @Getter @Setter
    private float precio;
}
