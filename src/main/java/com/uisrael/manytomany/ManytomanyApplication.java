package com.uisrael.manytomany;

import com.uisrael.manytomany.model.Cursos;
import com.uisrael.manytomany.model.Estudianntes;
import com.uisrael.manytomany.repository.CursoRepository;
import com.uisrael.manytomany.repository.EstudiantesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ManytomanyApplication implements CommandLineRunner {

	@Autowired
	EstudiantesRepository estudiantesRepository;
	@Autowired
	CursoRepository cursoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ManytomanyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Estudianntes estudianntes1 = new Estudianntes();
		estudianntes1.setNombre("Gabriel");
		Estudianntes estudianntes2 = new Estudianntes();
		estudianntes2.setNombre("Jair");
		Cursos curso1 = new Cursos();
		curso1.setNombreCurso("Matematica");
		Cursos curso2 = new Cursos();
		curso2.setNombreCurso("Fisica");

		cursoRepository.save(curso1);
		cursoRepository.save(curso2);

		List<Cursos> cursosEstudiantes1 = new ArrayList<>();
		cursosEstudiantes1.add(curso1);
		cursosEstudiantes1.add(curso2);

		List<Cursos> cursosEstudiantes2 = new ArrayList<>();
		cursosEstudiantes2.add(curso1);
		estudianntes2.setCursos(cursosEstudiantes2);

		estudiantesRepository.save(estudianntes1);
		estudiantesRepository.save(estudianntes2);
		// 1. Listar estudiantes por curso
		List<Estudianntes> estudiantesMatematica = estudiantesRepository.findEstudiantesPorCurso("Matematica");
		estudiantesMatematica.forEach(e -> System.out.println("Estudiante: " + e.getNombre()));

		// 2. Listar cursos de un estudiante específico
		List<Cursos> cursosGabriel = cursoRepository.findCursosPorEstudiante("Gabriel");
		cursosGabriel.forEach(c -> System.out.println("Curso: " + c.getNombreCurso()));

		// 3. Contar estudiantes en un curso específico
		Long totalEstudiantesFisica = estudiantesRepository.contarEstudiantesPorCurso("Fisica");
		System.out.println("Total estudiantes en Fisica: " + totalEstudiantesFisica);

		// 4. Listar estudiantes inscritos en más de un curso
		List<Estudianntes> estudiantesMultiplesCursos = estudiantesRepository.findEstudiantesConMultiplesCursos();
		estudiantesMultiplesCursos.forEach(e -> System.out.println("Estudiante en varios cursos: " + e.getNombre()));

		// 5. Listar cursos con el número de estudiantes inscritos
		List<Object[]> cursosConEstudiantes = cursoRepository.contarEstudiantesPorCurso();
		cursosConEstudiantes.forEach(c ->
				System.out.println("Curso: " + c[0] + ", Total estudiantes: " + c[1]));

	}
}
