package logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayListBD {
    public int crearPlaylist(String nombre, int usuarioId) {
        String sql = "INSERT INTO playlist (nombre, usuario_id) VALUES (?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nombre);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al crear playlist: " + e.getMessage());
        }
        return -1;
    }

    public boolean agregarCancionAPlaylist(int playlistId, int cancionId) {
        String sql = "INSERT INTO playlist_cancion (playlist_id, cancion_id) VALUES (?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playlistId);
            stmt.setInt(2, cancionId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar canción a playlist: " + e.getMessage());
            return false;
        }
    }

    public void listarPlaylists(int usuarioId) {
        String sql = "SELECT id, nombre FROM playlist WHERE usuario_id = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("ID Playlist: " + rs.getInt("id") + " | Nombre: " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar playlists: " + e.getMessage());
        }
    }

    public void listarCancionesDePlaylist(int playlistId) {
        String sql = """
            SELECT c.id, c.nombre, c.artista, c.genero, c.duracion, c.año_publicacion
            FROM playlist_cancion pc
            JOIN cancion c ON pc.cancion_id = c.id
            WHERE pc.playlist_id = ?
            """;
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playlistId);
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
            System.out.println("Error al listar canciones de la playlist: " + e.getMessage());
        }
    }
}
