package hilos.clase;

public class ProcesoA extends Thread {

    private String name;

    public ProcesoA(String name)
    {
        this.name = name;
    }

    public void saludar(String name)
    {
        System.out.println("Hola " + name);
    }

    @Override
    public void run() {
        saludar(name);
    }
}
