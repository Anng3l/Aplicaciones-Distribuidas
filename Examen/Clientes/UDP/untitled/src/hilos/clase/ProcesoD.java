package hilos.clase;
/*    HEREDANDO
public class ProcesoD extends Thread{

    private int numero;

    public ProcesoD (int numero) {
        this.numero = numero;
    }

    public void impresionAsteriscos(int numero)
    {
        for (int i = 0; i < numero; i++)
        {
            System.out.println("*");
        }
    }

    @Override
    public void run() {
        impresionAsteriscos(numero);
    }
}
*/

// IMPLEMENTANDO
public class ProcesoD implements Runnable{

    private int numero;

    public ProcesoD (int numero) {
        this.numero = numero;
    }

    public void impresionAsteriscos(int numero)
    {
        for (int i = 0; i < numero; i++)
        {
            System.out.println("*");
        }
    }

    @Override
    public void run() {
        impresionAsteriscos(numero);
    }
}
