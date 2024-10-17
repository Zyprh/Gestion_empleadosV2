package gestion_empledos;

import Controlador.CrtlEmpleado;
import Modelo.Empleado;
import DAO.EmpleadoDAOImpl;
import VIEW.Frame1;
import javax.swing.SwingUtilities;

public class ejecutable {


    public static void main(String[] args) {
        // Ejecutar en el hilo del Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            // Crear modelo, DAO, y vista
            Empleado modelo = new Empleado();
            EmpleadoDAOImpl dao = new EmpleadoDAOImpl();
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
