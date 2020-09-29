package com.BeHoH.DesafioJavaWeb.models.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BeHoH.DesafioJavaWeb.models.entities.Evento;
import com.BeHoH.DesafioJavaWeb.models.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Iterable<Usuario> findByEvento(Evento evento);
	Usuario findByNome(String nome);
}
