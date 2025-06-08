package hilos.test;

import hilos.clase.*;
import hilos.vista.VentanaAuto;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("asdasdasd1");






        ProcesoA a = new ProcesoA("Jose");
        ProcesoB b = new ProcesoB(100);

        ProcesoC c = new ProcesoC(10);

        a.start();
        b.start();

        //Se caerá la aplicación aplicación porque llega a dividir por 0 y sale una excepción que no es manejada.
        //El hilo que se usa, que es sólo 1, cae y todo cae.
        c.start();





        //Si se hereda
        //ProcesoD d = new ProcesoD(100);
        //d.start();

        //Si se implementa
        ProcesoD d = new ProcesoD(100);
        d.run();

        //o

        //ProcesoD d = new ProcesoD(100);
        //Thread x = new Thread(d);
        //x.start();

        // o

        /*Runnable x = new ProcesoD(100);
        Thread d = new Thread(x);
        d.start();*/


        Runnable xyz = new ProcesoE();
        Thread p = new Thread(xyz);
        p.start();



        System.out.println("asdasdasd2");




        Persona persona = new Persona();
        persona.setNombre("Jose");
        persona.setEdad(25);
        persona.setDireccion("Quito");

        System.out.println("Nombre: " + persona.getNombre());
        System.out.println("Edad: " + persona.getEdad());
        System.out.println("Diección: " + persona.getDireccion());

        VentanaAuto ventanaAuto = new VentanaAuto();
        ventanaAuto.setVisible(true);
    }
}
