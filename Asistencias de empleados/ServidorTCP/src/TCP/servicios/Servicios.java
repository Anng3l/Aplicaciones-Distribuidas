package TCP.servicios;

import java.io.*;

public class Servicios {
    public void escribir(String personaAsistencia, String ruta) throws Exception {
        File archivo = new File(ruta);
        FileOutputStream fos = new FileOutputStream(archivo, true); // Modo append
        ObjectOutputStream oos;

        if (archivo.length() == 0) {
            // Si el archivo está vacío, se usa el ObjectOutputStream normal
            oos = new ObjectOutputStream(fos);
        } else {
            // Si ya tiene contenido, se usa una versión que no escribe encabezado
            oos = new ObjectOutputStreamSinCabecera(fos);
        }

        oos.writeObject(personaAsistencia);
        oos.close();
        System.out.println("Datos agregados exitosamente");
    }


    public String leer(String ruta) throws Exception {
        FileInputStream fis = new FileInputStream(ruta);
        ObjectInputStream ois = new ObjectInputStream(fis);
        String p = (String) ois.readObject();
        ois.close();
        return p;
    };

    private static class ObjectOutputStreamSinCabecera extends ObjectOutputStream {
        public ObjectOutputStreamSinCabecera(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // No escribir el encabezado
        }
    }

}
