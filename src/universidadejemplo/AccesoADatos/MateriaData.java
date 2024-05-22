
package universidadejemplo.AccesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadejemplo.Entidades.Alumno;
import universidadejemplo.Entidades.Materia;
/**
 *
 * @author Javier
 */
public class MateriaData {
    private Connection con = null;
    
    public MateriaData(){
        con = Conexion.getConexion();
    }
    
    public void guardarMateria(Materia materia){
        
        String sql = "INSERT INTO materia(nombre, anio, estado) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //System.out.println(materia.getNombre());
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()) {
                materia.setIdMateria(rs.getInt(1));
                System.out.println("Materia ID: "+materia.getIdMateria()+ " Materia: "+materia.getNombre());
                JOptionPane.showMessageDialog(null,"*Materia añadida con exito*" ); 
                
            }
            ps.close();

        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia: "+ex.getMessage()); 
        }
    
        }
    
    public Materia buscarMateria(int id){
        Materia materia = null;
        String sql = "SELECT nombre, anio FROM materia WHERE idMateria = ? AND estado = 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,id );
            rs = ps.executeQuery();
            if (rs.next()) {
                materia=new Materia();
                materia.setIdMateria(id);
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materia.setEstado(true);
            } else {
                JOptionPane.showMessageDialog(null, "No existe la materia");
            }        
        
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia "+ex.getMessage()); 
        }finally{
            try {
               if (rs != null) rs.close();
               if (ps != null) ps.close();
            }catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Error al cerrar la conexión " + ex.getMessage());
            }
        }

    return materia;
   
    }
    
    public void modificarMateria(Materia materia){
        String sql = "UPDATE materia SET nombre = ?, anio = ?, estado = ? WHERE idMateria = ?";
        PreparedStatement ps = null;
            try{
                ps = con.prepareStatement(sql);
                ps.setString(1, materia.getNombre());
                ps.setInt(2, materia.getAnio());
                ps.setBoolean(3, materia.isEstado());
                ps.setInt(4, materia.getIdMateria());
                int fila = ps.executeUpdate();

                if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Materia Modificada Exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "La materia no existe o fue eliminada");
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia "+ex.getMessage());
            }
    }
    
    
    public void eliminarMateria(int id){

        PreparedStatement ps = null;
        try{
            String sql = "UPDATE materia SET estado = ? WHERE idMateria = ? AND estado = ?";
            ps=con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            ps.setBoolean(3, true);
            int fila = ps.executeUpdate();
            if(fila==1){
                JOptionPane.showMessageDialog(null, "Materia eliminada con exito!");
                System.out.println("La materia con Id: "+id+" fue elminada");
            }else{
                System.out.println("La materia con Id: "+id+" no se encuentra o ya fue elminada");
            }
           ps.close(); 
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Estado de eliminacion de materia: Fallida "+e.getMessage());
        } 
    }
    
    public List<Materia> listarMateria(){
        List<Materia> materias = new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql = "SELECT * FROM materia WHERE estado = 1 ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
             while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materia.setEstado(rs.getBoolean("estado"));
                materias.add(materia);
             }
        ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla materia "+ex.getMessage());
        }finally{
            try {
               if (rs != null) rs.close();
               if (ps != null) ps.close();
            }catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Error al cerrar la conexión " + ex.getMessage());
            }
        }
        return materias;
    }//Fin listarMaterias
    
    
}//Fin class
