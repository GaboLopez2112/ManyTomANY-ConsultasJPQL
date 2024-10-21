package com.uisrael.manytomany.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tbl_cursos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCurso;
    @ManyToMany(mappedBy = "cursos")
    private List<Estudianntes> estudianntes;
}
