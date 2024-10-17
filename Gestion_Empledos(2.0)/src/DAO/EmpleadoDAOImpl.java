package DAO;

import Implement.EmpleadoDAO;
import db.conexion;
import Modelo.Empleado;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTextField;

public class EmpleadoDAOImpl implements EmpleadoDAO {

    @Override
    // Método para crear un empleado
    public boolean crearEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleados (nombre, apellido, tipo_documento, numero_documento, direccion, telefono, correo_electronico, sexo, cargo, departamento, salario, fecha_nacimiento, estado_civil, estado, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getTipoDocumento());
            ps.setString(4, empleado.getNumDocumento()); // Asegúrate de usar el número de documento
            ps.setString(5, empleado.getDireccion());
            ps.setString(6, empleado.getTelefono());
            ps.setString(7, empleado.getCorreo());
            ps.setString(8, empleado.getSexo());
            ps.setString(9, empleado.getCargo());
            ps.setString(10, empleado.getDepartamento()); // Asegúrate de que esto sea correcto
            ps.setBigDecimal(11, empleado.getSalario()); // Salario como BigDecimal
            ps.setDate(12, new java.sql.Date(empleado.getFechaNacimiento().getTime())); // fecha de nacimiento
            ps.setString(13, empleado.getEstadoCivil());
            ps.setBoolean(14, empleado.isEstado()); // Estado activo o inactivo
            ps.setDate(15, new java.sql.Date(empleado.getFechaIngreso().getTime())); // fecha de ingreso

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    // Método para leer empleados
    public ArrayList<Empleado> listarEmpleados() {
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (Connection conn = conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("tipo_documento"),
                        rs.getString("numero_documento"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo_electronico"),
                        rs.getDate("fecha_nacimiento"), // Esto devuelve java.sql.Date
                        rs.getString("estado_civil"),
                        rs.getString("sexo"),
                        rs.getString("cargo"),
                        rs.getString("departamento"),
                        rs.getBigDecimal("salario"), // Esto devuelve BigDecimal
                        rs.getDate("fecha_ingreso"), // Esto devuelve java.sql.Date
                        rs.getBoolean("estado")
                );
                listaEmpleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }

    @Override
    // Método para actualizar empleado
    public boolean actualizarEmpleado(Empleado modelo) {
        String sql = "UPDATE empleados SET nombre = ?, apellido = ?, tipo_documento = ?, numero_documento = ?, "
                + "direccion = ?, telefono = ?, correo_electronico = ?, sexo = ?, cargo = ?, "
                + "departamento = ?, salario = ?, fecha_nacimiento = ?, fecha_ingreso = ?, "
                + "estado_civil = ?, estado = ? WHERE id = ?";
        try (Connection conn = conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, modelo.getNombre());
            ps.setString(2, modelo.getApellido());
            ps.setString(3, modelo.getTipoDocumento());
            ps.setString(4, modelo.getNumDocumento());
            ps.setString(5, modelo.getDireccion());
            ps.setString(6, modelo.getTelefono());
            ps.setString(7, modelo.getCorreo());
            ps.setString(8, modelo.getSexo());
            ps.setString(9, modelo.getCargo());
            ps.setString(10, modelo.getDepartamento());
            ps.setBigDecimal(11, modelo.getSalario());
            ps.setDate(12, modelo.getFechaNacimiento());
            ps.setDate(13, modelo.getFechaIngreso());
            ps.setString(14, modelo.getEstadoCivil());
            ps.setBoolean(15, modelo.isEstado());
            ps.setString(16, modelo.getId()); // Asegúrate de que se actualiza por ID

            return ps.executeUpdate() > 0; // Retorna true si se actualizó alguna fila
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Retorna false si no se realizó la actualización
    }

    @Override
    // Método para eliminar empleado
    public boolean eliminarEmpleado(String numeroDocumento) {
        String sql = "DELETE FROM empleados WHERE numero_documento=?";
        try (Connection conn = conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, numeroDocumento); // Cambié a numero_documento en lugar de id
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    // Método para buscar empleado
    public boolean buscar(Empleado empleado) {
        String sql = "SELECT * FROM empleados WHERE numero_documento=?";
        try (Connection con = conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, empleado.getNumDocumento());  // Buscamos por numero_documento
            System.out.println("Buscando documento: " + empleado.getNumDocumento());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                empleado.setId(rs.getString("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido")); // Corregí apellido a String
                empleado.setTipoDocumento(rs.getString("tipo_documento"));
                empleado.setNumDocumento(rs.getString("numero_documento"));
                empleado.setDireccion(rs.getString("direccion"));
                empleado.setTelefono(rs.getString("telefono"));
                empleado.setCorreo(rs.getString("correo_electronico"));
                empleado.setSexo(rs.getString("sexo"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setDepartamento(rs.getString("departamento"));
                empleado.setSalario(rs.getBigDecimal("salario")); // Salario como String
                empleado.setEstado(rs.getBoolean("estado"));
                empleado.setEstadoCivil(rs.getString("estado_civil"));

                // Establecer las fechas
                empleado.setFechaNacimiento(rs.getDate("fecha_nacimiento")); // fecha de nacimiento
                empleado.setFechaIngreso(rs.getDate("fecha_ingreso")); // fecha de ingreso

                return true;
            } else {
                System.out.println("No se encontro registro para : " + empleado.getNumDocumento());
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void soloNcarc(int numcarac, JTextField tf, KeyEvent e) {
        if (tf.getText().length() >= numcarac) {
            e.consume();
        }
    }

    @Override
    public void soloTodosNum(KeyEvent e) {
        char c = e.getKeyChar();
        if (c < 48 || c > 57) {
            e.consume();
        }
    }
}
