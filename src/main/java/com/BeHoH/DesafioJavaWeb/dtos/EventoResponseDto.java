package com.BeHoH.DesafioJavaWeb.dtos;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventoResponseDto {

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=20, message="O tamanho deve ser entre 3 e 20 caracteres")
	@JsonProperty("Nome")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=4, message="O tamanho deve ser entre 1 e 4 caracteres")
	@JsonProperty("Vagas")
	private long vagas;
	
	
	private String data_inicio;
	
	private String data_fim;
	
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
	public String getData_inicio() {
		
		return data_inicio;
	}

	/**
	 * @param data_inicio the data_inicio to set
	 */
	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}

	/**
	 * @return the data_fim
	 */
	public String getData_fim() {
		return data_fim;
	}

	/**
	 * @param data_fim the data_fim to set
	 */
	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}

}
