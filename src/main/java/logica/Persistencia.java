package logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;

public class Persistencia {
    public static void guardarUsuarios(List<Usuario> usuarios, String File) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(File))) {
            out.writeObject(usuarios);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Usuario> cargarUsuarios(String File) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(File))) {
            return (List<Usuario>) in.readObject();
        } catch (FileNotFoundException e) {
            return new java.util.ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
}
