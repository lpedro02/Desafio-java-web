package com.BeHoH.DesafioJavaWeb.models.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BeHoH.DesafioJavaWeb.models.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{

	Evento findById(long id);
	Evento findByNome(String nome);
	
	
}
