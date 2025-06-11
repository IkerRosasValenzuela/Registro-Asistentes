import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.SwingUtilities;


public class RegistroGUI extends JFrame {
    private JTextField campoNombre, campoCorreo, campoInstitucion;
    private JTextArea areaConsulta;
    private DBHelper db;

    public RegistroGUI() {
        db = new DBHelper();
        setTitle("Registro de Asistentes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        crearGUI();
    }

    private void crearGUI() {
        JTabbedPane pestañas = new JTabbedPane();

        // Panel de registro
        JPanel panelRegistro = new JPanel(new GridLayout(5, 2, 5, 5));
        campoNombre = new JTextField();
        campoCorreo = new JTextField();
        campoInstitucion = new JTextField();
        JButton btnRegistrar = new JButton("Registrar");
        
        panelRegistro.add(new JLabel("Nombre:"));
        panelRegistro.add(campoNombre);
        panelRegistro.add(new JLabel("Correo:"));
        panelRegistro.add(campoCorreo);
        panelRegistro.add(new JLabel("Institución:"));
        panelRegistro.add(campoInstitucion);
        panelRegistro.add(new JLabel());
        panelRegistro.add(btnRegistrar);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarAsistente();
            }
        });


        // Panel de consulta
        JPanel panelConsulta = new JPanel(new BorderLayout());
        areaConsulta = new JTextArea();
        areaConsulta.setEditable(false);
        JButton btnActualizar = new JButton("Actualizar Lista");
        
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAsistentes();
            }
        });


        panelConsulta.add(new JScrollPane(areaConsulta), BorderLayout.CENTER);
        panelConsulta.add(btnActualizar, BorderLayout.SOUTH);

        pestañas.addTab("Registro", panelRegistro);
        pestañas.addTab("Consulta", panelConsulta);

        add(pestañas);
    }

    private void registrarAsistente() {
        String nombre = campoNombre.getText().trim();
        String correo = campoCorreo.getText().trim();
        String institucion = campoInstitucion.getText().trim();

        if (nombre.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y correo son obligatorios.");
            return;
        }

        // Crear el asistente y guardarlo en la base de datos
        Asistente a = new Asistente(nombre, correo, institucion);
        db.insertarAsistente(a);
        
        // Mensaje de confirmación
        JOptionPane.showMessageDialog(this, "Asistente registrado correctamente.");
        
        // Limpiar los campos de texto
        campoNombre.setText("");
        campoCorreo.setText("");
        campoInstitucion.setText("");
    }

    private void mostrarAsistentes() {
        ArrayList<Asistente> lista = db.obtenerAsistentes();
        
        // Limpiar la lista de asistentes en el área de texto
        areaConsulta.setText("");
        
        // Mostrar los asistentes
        for (Asistente a : lista) {
            areaConsulta.append(a.toString() + "\n");
        }
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(new Runnable() {
    	    @Override
    	    public void run() {
    	        new RegistroGUI().setVisible(true);
    	    }
    	});

    }
}


class Asistente {
    private String nombre;
    private String correo;
    private String institucion;

    public Asistente(String nombre, String correo, String institucion) {
        this.nombre = nombre;
        this.correo = correo;
        this.institucion = institucion;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Correo: " + correo + ", Institución: " + institucion;
    }
}

// Clase DBHelper (simulada, puedes adaptarla para trabajar con una base de datos real)
class DBHelper {
    private ArrayList<Asistente> listaAsistentes;

    public DBHelper() {
        listaAsistentes = new ArrayList<>();
    }

    public void insertarAsistente(Asistente a) {
        listaAsistentes.add(a);
    }

    public ArrayList<Asistente> obtenerAsistentes() {
        return listaAsistentes;
    }
}
