package com.BeHoH.DesafioJavaWeb.models.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "evento")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Id
	@Column(name = "id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
			
	@Column(nullable=false)
   	private String nome;
	
	@Column(name = "vagas", nullable=false)
   	private int vagas;

	@Column(name = "dataInicio")
	private Date data_inicio;
	
	@Column(name = "dataFim", nullable=false)
	private Date data_fim;
	
	@ManyToMany
    @JoinTable(
        name = "evento_usuario",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
	private List<Usuario> usuarios;
	
		
}
