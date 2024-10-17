/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Implement;

import Modelo.Empleado;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Alumno
 */
public interface EmpleadoDAO {

    public boolean crearEmpleado(Empleado empleado);

    public ArrayList<Empleado> listarEmpleados();

    public boolean actualizarEmpleado(Empleado modelo);

    public boolean eliminarEmpleado(String numeroDocumento);

    public boolean buscar(Empleado empleado);

    public void soloNcarc(int numcarac, JTextField tf, KeyEvent e);

    public void soloTodosNum(KeyEvent e);

}
