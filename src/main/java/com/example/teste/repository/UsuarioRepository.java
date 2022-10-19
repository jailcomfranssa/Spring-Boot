package com.example.teste.repository;

import com.example.teste.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    @Query("SELECT e FROM Usuario e JOIN FETCH e.roles WHERE e.email= (:email)")
    public Usuario buscarEmail(@Param("email") String email);

    List<Usuario> findByNome(String nome);
}
