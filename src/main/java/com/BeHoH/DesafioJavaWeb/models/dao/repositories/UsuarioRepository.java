package com.BeHoH.DesafioJavaWeb.models.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.BeHoH.DesafioJavaWeb.models.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
