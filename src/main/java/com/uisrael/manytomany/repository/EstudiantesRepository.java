package com.uisrael.manytomany.repository;

import com.uisrael.manytomany.model.Estudianntes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudiantesRepository extends JpaRepository<Estudianntes,Long> {
    // 1. Listar todos los estudiantes que están inscritos en un curso específico
    @Query("SELECT e FROM Estudianntes e JOIN e.cursos c WHERE c.nombreCurso = :nombreCurso")
    List<Estudianntes> findEstudiantesPorCurso(@Param("nombreCurso") String nombreCurso);

    // 3. Obtener la cantidad total de estudiantes inscritos en un curso específico
    @Query("SELECT COUNT(e) FROM Estudianntes e JOIN e.cursos c WHERE c.nombreCurso = :nombreCurso")
    Long contarEstudiantesPorCurso(@Param("nombreCurso") String nombreCurso);

    // 4. Listar los estudiantes que están inscritos en más de un curso
    @Query("SELECT e FROM Estudianntes e WHERE SIZE(e.cursos) > 1")
    List<Estudianntes> findEstudiantesConMultiplesCursos();
}
