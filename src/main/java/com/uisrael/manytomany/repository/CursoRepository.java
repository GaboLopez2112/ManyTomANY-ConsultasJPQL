package com.uisrael.manytomany.repository;

import com.uisrael.manytomany.model.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Cursos,Long> {
    // 2. Listar todos los cursos en los que está inscrito un estudiante por su nombre
    @Query("SELECT c FROM Cursos c JOIN c.estudianntes e WHERE e.nombre = :nombreEstudiante")
    List<Cursos> findCursosPorEstudiante(@Param("nombreEstudiante") String nombreEstudiante);

    // 5. Listar todos los cursos junto con el número de estudiantes inscritos
    @Query("SELECT c.nombreCurso, COUNT(e) FROM Cursos c JOIN c.estudianntes e GROUP BY c.nombreCurso")
    List<Object[]> contarEstudiantesPorCurso();
}
