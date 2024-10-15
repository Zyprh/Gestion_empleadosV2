
package gestion_empledos;

import Controlador.CrtlEmpleado;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import VIEW.Frame1;
import javax.swing.SwingUtilities;

/**
 *
 * @author Alumno
 */
public class ejecutable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Ejecutar en el hilo del Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Crear modelo, DAO, y vista
            Empleado modelo = new Empleado();
            EmpleadoDAO dao = new EmpleadoDAO();
            Frame1 vista = new Frame1();
            
            // Crear el controlador y pasarle el modelo, DAO, y vista
            CrtlEmpleado controlador = new CrtlEmpleado(modelo, dao, vista);

            // Iniciar el controlador (agrega ítems a los ComboBox)
            controlador.iniciar();
            
            // Mostrar la vista
            vista.setVisible(true);
        });
    }
    
}
