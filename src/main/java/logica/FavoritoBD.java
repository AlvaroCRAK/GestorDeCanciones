package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FavoritoBD {
    public boolean agregarFavorito(int usuarioId, int cancionId) {
        String sql = "INSERT INTO favorito (usuario_id, cancion_id) VALUES (?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            stmt.setInt(2, cancionId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar favorito: " + e.getMessage());
            return false;
        }
    }

    public void listarFavoritos(int usuarioId) {
        String sql = """
            SELECT c.id, c.nombre, c.artista, c.genero, c.duracion, c.año_publicacion
            FROM favorito f
            JOIN cancion c ON f.cancion_id = c.id
            WHERE f.usuario_id = ?
            """;
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("id") +
                    " | " + rs.getString("nombre") +
                    " | Artista: " + rs.getString("artista") +
                    " | Género: " + rs.getString("genero") +
                    " | Duración: " + rs.getInt("duracion") +
                    " seg | Año: " + rs.getInt("año_publicacion")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al listar favoritos: " + e.getMessage());
        }
    }
}
