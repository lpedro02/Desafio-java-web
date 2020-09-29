package com.BeHoH.DesafioJavaWeb.models.entities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evento")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Id
	@Column(name = "id")
	@GeneratedValue (strategy = GenerationType.TABLE)
	private long id;
			
	@Column(name = "nome")
   	private String nome;
	
	@Column(name = "vagas")
   	private long vagas;

	@Column(name = "dataInicio")
	private Date data_inicio;
	
	@Column(name = "dataFim")
	private Date data_fim;
	
	@OneToMany
	private List<Usuario> usuarios;
	
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

	/**
	 * @return the vagas
	 */
	public long getVagas() {
		return vagas;
	}

	/**
	 * @param vagas the vagas to set
	 */
	public void setVagas(long vagas) {
		this.vagas = vagas;
	}

	/**
	 * @return the data_inicio
	 */
	public Date getData_inicio() {
		return data_inicio;
	}

	/**
	 * @param data_inicio the data_inicio to set
	 */
	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	/**
	 * @return the data_fim
	 */
	public Date getData_fim() {
		return data_fim;
	}

	/**
	 * @param data_fim the data_fim to set
	 */
	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}
	
	@Override
	public String toString() {
		return "FuncionarioDto [id=" + id + ", nome=" + nome + ", vagas=" + vagas + ", data inicio=" + data_inicio + ", data fim=" + data_fim + "]";
	}

	/**
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	/**
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
