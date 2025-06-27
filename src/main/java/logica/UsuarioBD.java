package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioBD {
	public boolean insertarUsuario(String nombre) {
	    String sql = "INSERT INTO usuario (nombre) VALUES (?)";
	    try (Connection conn = ConexionBD.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, nombre);
	        stmt.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        System.out.println("Error al insertar usuario: " + e.getMessage());
	        return false;
	    }
	}

    public void listarUsuarios() {
        String sql = "SELECT id, nombre FROM usuario";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                System.out.println("ID: " + id + " | Nombre: " + nombre);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
    }

    public int obtenerIdUsuario(String nombre) {
        String sql = "SELECT id FROM usuario WHERE nombre = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ID de usuario: " + e.getMessage());
        }
        return -1;
    }
}
