package hilos.clase;

public class ProcesoC extends Thread {

    private int x;
    public ProcesoC(int x) {
        this.x = x;
    }

    public void dividir(int x)
    {
        //Saldrá error
        for (int i = 10; i > -1; i--)
        {
            System.out.println(i + "--" + x/i);
        }
    }

    @Override
    public void run() {
        dividir(x);
    }
}
