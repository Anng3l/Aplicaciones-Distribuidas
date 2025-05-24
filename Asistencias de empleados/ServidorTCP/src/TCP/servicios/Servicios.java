package TCP.servicios;

import java.io.*;

public class Servicios {
    public void escribir(String personaAsistencia, String ruta) throws Exception {
        File archivo = new File(ruta);
        FileOutputStream fos = new FileOutputStream(archivo, true); // Modo append
        ObjectOutputStream oos;

        if (archivo.length() == 0) {
            oos = new ObjectOutputStream(fos);
        } else {
            oos = new ObjectOutputStreamSinCabecera(fos);
        }

        oos.writeObject(personaAsistencia);
        oos.close();
        System.out.println("Datos agregados exitosamente");
    }


    public String leer(String ruta) throws Exception {
        FileInputStream fis = new FileInputStream(ruta);
        ObjectInputStream ois = new ObjectInputStream(fis);

        StringBuilder contenido = new StringBuilder();

        try {
            while (true) {
                String linea = (String) ois.readObject();
                contenido.append(linea).append("\n");
            }
        } catch (EOFException eof) {
        } finally {
            ois.close();
        }

        return contenido.toString();
    }

    private static class ObjectOutputStreamSinCabecera extends ObjectOutputStream {
        public ObjectOutputStreamSinCabecera(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
        }
    }

}
