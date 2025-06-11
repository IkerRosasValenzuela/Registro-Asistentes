import java.sql.*;
import java.util.ArrayList;
public class DBHelper {
 private static final String DB_URL = "jdbc:sqlite:asistentes.db";
 public DBHelper() {
 crearTablaSiNoExiste();
 }
 private void crearTablaSiNoExiste() {
 try (Connection conn = DriverManager.getConnection(DB_URL);
 Statement stmt = conn.createStatement()) {
 String sql = "CREATE TABLE IF NOT EXISTS asistentes (" +
 "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
 "nombre TEXT NOT NULL, " +
 "correo TEXT NOT NULL, " +
 "institucion TEXT)";
 stmt.execute(sql);
 } catch (SQLException e) {
 e.printStackTrace();
 }
 }
 public void insertarAsistente(Asistente a) {
 String sql = "INSERT INTO asistentes(nombre, correo, institucion) VALUES(?, ?, ?)";
 try (Connection conn = DriverManager.getConnection(DB_URL);
 PreparedStatement pstmt = conn.prepareStatement(sql)) {
 pstmt.setString(1, a.getNombre());
 pstmt.setString(2, a.getCorreo());
 pstmt.setString(3, a.getInstitucion());
 pstmt.executeUpdate();
 } catch (SQLException e) {
 e.printStackTrace();
 }
 }
 public ArrayList<Asistente> obtenerAsistentes() {
 ArrayList<Asistente> lista = new ArrayList<>();
 String sql = "SELECT nombre, correo, institucion FROM asistentes";
 try (Connection conn = DriverManager.getConnection(DB_URL);
 Statement stmt = conn.createStatement();
 ResultSet rs = stmt.executeQuery(sql)) {
 while (rs.next()) {
 lista.add(new Asistente(
 rs.getString("nombre"),
 rs.getString("correo"),
 rs.getString("institucion")));
 }
 } catch (SQLException e) {
 e.printStackTrace();
 }
 return lista;
 }
} 