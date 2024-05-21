
package universidadejemplo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import universidadejemplo.AccesoADatos.*;
import universidadejemplo.Entidades.*;

/**
 *
 * @author Javier
 */
public class UniversidadEjemplo {

    public static void main(String[] args) {
        Connection conexion = Conexion.getConexion();        
            //Prueba Guardar alumnos en la BD 
            
             try{
                Alumno alumno = new Alumno(33291234,"Ramirez","Pedro",LocalDate.parse("1989-08-07"),true);
                AlumnoData alumno1 = new AlumnoData();
                alumno1.guardarAlumno(alumno);
                
                alumno.setDni(94854521);
                alumno.setApellido("Montes");
                alumno.setNombre("Juan");
                alumno.setFechaNacimiento(LocalDate.parse("2004-03-15"));      
                alumno.setEstado(true);
                AlumnoData alumno2 = new AlumnoData();
                alumno2.guardarAlumno(alumno);
                
                alumno.setDni(32559878);
                alumno.setApellido("Lopez");
                alumno.setNombre("Carlos");
                alumno.setFechaNacimiento(LocalDate.parse("2000-11-25"));      
                alumno.setEstado(true);
                AlumnoData alumno3 = new AlumnoData();
                alumno3.guardarAlumno(alumno);
                
            }catch(NumberFormatException | NullPointerException ex){
                JOptionPane.showMessageDialog(null, "Complete todos los campos");
            }
            
            
            //Buscar un alumno por ID
            try{
                AlumnoData alumnoABuscar = new AlumnoData();
                int idABuscar = 7;
                System.out.println("");
                System.out.println("*** Prueba de Busqueda de alumno por ID ***");
                System.out.println("Alumno id: "+idABuscar+"\nNombre: "+alumnoABuscar.buscarAlumno(idABuscar).getNombre()
                        +"\nApellido: "+alumnoABuscar.buscarAlumno(idABuscar).getApellido()
                        +"\nDNI: " +alumnoABuscar.buscarAlumno(idABuscar ).getDni());
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null, "Ingrese un numero de ID Valido ");
            }
            
            //Buscar Alumno por DNI
            try{
                int dniABuscar = 27350398;
                AlumnoData alumnoABuscar = new AlumnoData();
                System.out.println("");
                System.out.println("*** Prueba de Busqueda de alumno por DNI ***");
                System.out.println("Alumno id: "+alumnoABuscar.buscarAlumnoPorDni(dniABuscar).getIdAlumno()
                        +"\nNombre: "+ alumnoABuscar.buscarAlumnoPorDni(dniABuscar).getNombre()
                        +"\nApellido: "+alumnoABuscar.buscarAlumnoPorDni(dniABuscar).getApellido()
                        +"\nDNI: " +alumnoABuscar.buscarAlumnoPorDni(dniABuscar ).getDni());
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null, "Ingrese un numero de DNI Valido ");
            }
             //Listar Alumnos
             AlumnoData alumnoData = new AlumnoData();
             List<Alumno> alumnos = new ArrayList<>();
             System.out.println("");
             System.out.println("*** Prueba de Listado de alumnos ***");
             alumnos = alumnoData.listarAlumnos();
             
             for (Alumno alumno : alumnos) {
                 System.out.println(alumno.toString());
                }
            
            //Modificar un alumno
            try{
                AlumnoData alumnoAModificar = new AlumnoData();
                int id=5;
                System.out.println("");
                System.out.println("*** Prueba de modificacion de alumno ***");
                System.out.println("Registro a actualizar: "+alumnoAModificar.buscarAlumno(id).getIdAlumno());
                Alumno alumno = new Alumno(id,33291234,"Ramirez","Pedro Santiago",LocalDate.parse("1989-08-07"),true);
                alumnoAModificar.modificarAlumno(alumno);
                System.out.println("Registro actualizado: Alumno id: "+id+ ", DNI: " +alumnoAModificar.buscarAlumno(id ).getDni()
                        +", Apellido: "+alumnoAModificar.buscarAlumno(id).getApellido()
                        +", Nombre: "+alumnoAModificar.buscarAlumno(id).getNombre()); 
                
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null, "Error update ");
            }
            
            
            //Eliminar un alumno
            try{
                AlumnoData alumnoAEliminar = new AlumnoData();
                int alumno=6;
                System.out.println("");
                System.out.println("*** Prueba de Eliminacion de alumno ***");
                System.out.println("Alumno a eliminar ID: "+alumnoAEliminar.buscarAlumno(alumno).getIdAlumno());
                alumnoAEliminar.eliminarAlumno(alumno);
                System.out.println("*Eliminado*");
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null, "El Alumno a eliminar no existe o ya fue elimninado");
                System.out.println("Estado de Eliminacion: fallida");
            }
            
            //Reseteo de BD
            try{
                String sql = "DELETE FROM alumno WHERE idAlumno > 4";
                PreparedStatement ps = conexion.prepareStatement(sql);
                int fila=ps.executeUpdate();
                if(fila>=3){
                    JOptionPane.showMessageDialog(null, " Se reseatearon los registros con exito!");
                }
                ps.close();
                String sql1 = "ALTER TABLE alumno AUTO_INCREMENT = 5";
                PreparedStatement ps2 = conexion.prepareStatement(sql1);
                ps2.executeUpdate();
                ps.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno"+ e.getMessage());
            }
           
            /*
            Class.forName("org.mariadb.jdbc.Driver"); //Cargando el Driver
            
            String url = "jdbc:mysql://localhost:3306/universidadejemplo";
            String usr="root";
            String pass= ""; 
            
            //conexion = DriverManager.getConnection(url,usr,pass);//creando la conexion
            
            //Insertando 3 alumnos
            String sql1 = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado) "
                    + "VALUES (44729123, 'Diaz', 'Ramon','2000-05-23', 1),"
                    + "(78544223, 'Cannigia', 'Claudio','1991-10-18', 1),"
                    + "(65324247, 'Palermo', 'Martin','2001-01-04', 1)";
            /*
            filas = pStat(conexion, sql1).executeUpdate();
            if(filas > 0){
                JOptionPane.showMessageDialog(null, "Alumno/s cargado/s con Exito!");
            }
            //Cargando 4 Materias
            String slq2 = "INSERT INTO materia(nombre, año, estado) VALUES "
                    +"('Laboratorio de programacion 1', 2, 1),"
                    +"('Estructura de Datos y Algoritmos', 2, 1),"
                    +"('Comprension de Textos', 1, 1),"
                    +"('Ingenieria del software 1', 3, 1)";
            /*
            filas = pStat(conexion, slq2).executeUpdate();
            if(filas > 0){
                JOptionPane.showMessageDialog(null, "Materia/s cargada/s con Exito!");
            }
            
            //Inscribir a 3 Alumnos en 2 materias c/u
            String slq3 = "INSERT INTO inscripcion(nota, idAlumno, idMateria) " 
                    +"VALUES (9, 1, 3), (7.5, 1, 2)," 
                    +"(10, 2, 4), (8.5, 2, 3)," 
                    +"(6.5, 3, 1), (9, 3, 3)";
            /*
            filas = pStat(conexion, slq3).executeUpdate();
            if(filas > 0){
                JOptionPane.showMessageDialog(null, "Inscripcion/es cargada/s con Exito!");
            }
            
            //desinscribir un alumno en una materia
            String slq4 = "DELETE inscripcion FROM inscripcion JOIN alumno ON inscripcion.idAlumno = alumno.idAlumno " +
                    "JOIN materia ON inscripcion.idMateria = materia.idMateria " +
                    "WHERE alumno.apellido = 'Diaz' AND materia.idMateria = 3";
            /*
            filas = pStat(conexion, slq4).executeUpdate();
            if(filas > 0){
                JOptionPane.showMessageDialog(null, "Materia eliminada con Exito!");
            }
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar el Driver"+ex.getMessage());
        } catch (SQLException ex) {
                        int codigoError = ex.getErrorCode();
            //System.out.println(codigoError);
            switch (codigoError) {
                case 1062:
                    JOptionPane.showMessageDialog(null, "Error al crear conexion: Registro/s duplicado/s "+ex.getMessage());
                    break;
                case 1049:
                    JOptionPane.showMessageDialog(null, "Error al crear conexion: BD No existe o no se encuentra "+ex.getMessage());
                    break;
                case 1045:
                    JOptionPane.showMessageDialog(null, "Error al crear conexion: Nombre de usuario y/o contraseña incorrecto/s: "+ex.getMessage());
                    break;
                case 1064:
                    JOptionPane.showMessageDialog(null, "Error al crear conexion: Error de sintaxis: "+ex.getMessage());
                    break;
                case 1054:
                    JOptionPane.showMessageDialog(null, "Error al crear conexion: Error de sintaxis en Columna: "+ex.getMessage());
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "ERROR: FALLO EN LA CONEXION: "+ex.getMessage());
            }     
        }finally{
            try {
                conexion.close();
                System.out.println("CONEXION CERRADA CON EXITO! Saliendo en 3,2,1..."); 
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR: FALLO EL CIERRE DE LA CONEXION: "+ex.getMessage());
            }
        }
    }//Fin Main
    
    public static PreparedStatement pStat(Connection conexion, String sql) throws SQLException{    
    return conexion.prepareStatement(sql); 
*/
    } 
}
