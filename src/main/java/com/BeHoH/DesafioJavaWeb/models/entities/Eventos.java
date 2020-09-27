package com.BeHoH.DesafioJavaWeb.models.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Eventos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue (strategy = GenerationType.TABLE)
	private long id;
			
	@Column(name = "nome")
   	private String nome;
	
	@Column(name = "vagas")
   	private String vagas;

	@Column(name = "dataInicio")
	private Date data_inicio;
	
	@Column(name = "dataFim")
	private Date data_fim;
	
}
