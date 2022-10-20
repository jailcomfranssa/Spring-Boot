package com.example.teste.repository;

import com.example.teste.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OauthRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT e FROM Usuario e JOIN FETCH e.roles WHERE e.email= (:username)")
    public Usuario findByUsername(@Param("username") String username);

    boolean existsByEmail(String username);
}
