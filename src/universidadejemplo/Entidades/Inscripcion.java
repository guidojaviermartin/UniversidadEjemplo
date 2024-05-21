
package universidadejemplo.Entidades;

/**
 *
 * @author Javier
 */
public class Inscripcion {
    private int idInscripcion;
    private double nota;
    Alumno idAlumno;
    Materia idMateria;

    public Inscripcion() {
    }

    public Inscripcion(int idInscripcion, double nota, Alumno idAlumno, Materia idMateria) {
        this.idInscripcion = idInscripcion;
        this.nota = nota;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }

    public Inscripcion(double nota, Alumno idAlumno, Materia idMateria) {
        this.nota = nota;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }
    
    
}
