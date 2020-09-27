package com.BeHoH.DesafioJavaWeb.models.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private  Usuario usuario ;
	
	@Id
	@Column(name = "id")
	@GeneratedValue (strategy = GenerationType.TABLE)
	private long id;
			
	@Column(name = "nome")
   	private String nome;

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	
}

