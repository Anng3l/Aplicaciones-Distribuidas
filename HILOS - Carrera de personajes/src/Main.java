import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaCarrera();
            }
        });
    }
}

class VentanaCarrera extends JFrame {
    public VentanaCarrera()
    {
        //Nombre de la ventana
        super("Carrera");
        //Tamaño de la ventana
        setSize(500, 350);
        //Cierre de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Todo lo gráfico se encontrará dentro del JPanel
        JPanel jpanel = new JPanel();
        //Se desactiva el Layout manager del JPanel, por lo que las ubicaciones y dimensiones de los
        //Elementos en el JPanel deberán ser establecidos manualmente con setBounds(x, y, width, height)
        jpanel.setLayout(null);
        add(jpanel);
        setVisible(true);
        jpanel.setBackground(Color.WHITE);

        //Los gifs son jlabels que cambian de posición
        JLabel kirby = new JLabel();
        JLabel sonic = new JLabel();
        JLabel yoshi = new JLabel();
        JLabel kirby_position = new JLabel();
        JLabel sonic_position = new JLabel();
        JLabel yoshi_position = new JLabel();
        JButton buttonIniciarCarrera = new JButton("Iniciar carrera");





        //Los JLabel sólo almacenan icons, por lo que las imágenes una vez capturadas
        Image imagen_kirby = new ImageIcon("./src/img/kirby-walk.gif").getImage();
        //Son envueltas en un ImageIcon
        ImageIcon icon_kirby = new ImageIcon(imagen_kirby.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        kirby.setIcon(icon_kirby);

        Image imagen_sonic = new ImageIcon("./src/img/sonic-running.gif").getImage();
        ImageIcon icon_sonic = new ImageIcon(imagen_sonic.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        sonic.setIcon(icon_sonic);

        Image imagen_yoshi = new ImageIcon("./src/img/yoshi-sprite.gif").getImage();
        ImageIcon icon_yoshi = new ImageIcon(imagen_yoshi.getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        yoshi.setIcon(icon_yoshi);




        //Seteo de los tamaños y posiciones en el JPanel de los JLabels
        kirby.setBounds(50, 50, 50, 50);
        sonic.setBounds(50, 110, 50, 50);
        yoshi.setBounds(50, 170, 50, 50);

        //Mensajes de llegada
        kirby_position.setBounds(50, 50, 350,50);
        sonic_position.setBounds(50, 110, 350, 50);
        yoshi_position.setBounds(50, 170, 350, 50);

        //Botón de inicar carrera
        buttonIniciarCarrera.setBounds(150, 230, 150, 50);


        //Seteo de los elementos en el JPanel
        jpanel.add(kirby);
        jpanel.add(sonic);
        jpanel.add(yoshi);

        jpanel.add(kirby_position);
        jpanel.add(sonic_position);
        jpanel.add(yoshi_position);

        jpanel.add(buttonIniciarCarrera);

        buttonIniciarCarrera.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hilo tKirby = new Hilo("Kirby", kirby, kirby_position);
                Hilo tSonic = new Hilo("Sonic", sonic, sonic_position);
                Hilo tkirby = new Hilo("Yoshi", yoshi, yoshi_position);
            }
        });


    }
}

class Hilo implements Runnable {
    Thread t;
    String nombre;
    JLabel personaje;
    JLabel labelFinal;

    public static int lugar;

    public Hilo (String nombre, JLabel personaje, JLabel labelFinal)
    {
        this.nombre = nombre;
        this.labelFinal = labelFinal;
        this.personaje = personaje;
        t = new Thread(this,nombre);
        t.start();
    }

    @Override
    public void run() {
        int retardo;
        try
        {
            lugar = 1;
            retardo = (int) (Math.random() * 15) + 1;
            labelFinal.setVisible(false);
            personaje.setVisible(true);

            for(int i = 50; i <= 500; i++)
            {
                personaje.setLocation(i, personaje.getY());
                Thread.sleep(retardo);
            }

            personaje.setVisible(false);
            labelFinal.setVisible(true);
            labelFinal.setText(nombre + " ha llegado en la posición " + lugar);
            lugar++;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}