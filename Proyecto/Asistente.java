public class Asistente {
    private String nombre;
    private String correo;
    private String institucion;

    public Asistente(String nombre, String correo, String institucion) {
        this.nombre = nombre;
        this.correo = correo;
        this.institucion = institucion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getInstitucion() {
        return institucion;
    }

    @Override
    public String toString() {
        return nombre + " - " + correo + " - " + institucion;
    }
}
