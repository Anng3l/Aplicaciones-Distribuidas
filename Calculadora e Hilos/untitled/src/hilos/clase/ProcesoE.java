package hilos.clase;

public class ProcesoE implements Runnable{

    public void saludo()
    {

        System.out.println("Hola como estás?");
    }

    @Override
    public void run() {
        saludo();
    }
}
