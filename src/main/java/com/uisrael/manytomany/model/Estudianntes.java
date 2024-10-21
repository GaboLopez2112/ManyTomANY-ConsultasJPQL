package com.uisrael.manytomany.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table (name = "tbl_estudiantes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Estudianntes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToMany
    @JoinTable(
            name = "estudiante_curso", joinColumns = @JoinColumn(name = "estudiante_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id",referencedColumnName = "id")

    )
    private List<Cursos> cursos;
}
