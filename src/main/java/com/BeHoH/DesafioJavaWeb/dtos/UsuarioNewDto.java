package com.BeHoH.DesafioJavaWeb.dtos;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioNewDto {

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=20, message="O tamanho deve ser entre 3 e 20 caracteres")
	@JsonProperty("Nome")
	private String nome;
	
	
	public UsuarioNewDto(
			@NotEmpty(message = "Preenchimento obrigatório") @Length(min = 3, max = 20, message = "O tamanho deve ser entre 3 e 20 caracteres") String nome) {
		super();
		this.nome = nome;
	}

	public UsuarioNewDto() {}	
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
