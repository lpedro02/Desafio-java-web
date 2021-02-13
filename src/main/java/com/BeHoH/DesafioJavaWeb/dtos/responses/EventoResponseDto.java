package com.BeHoH.DesafioJavaWeb.dtos.responses;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoResponseDto {
	
	private String nome;
		
	private long vagas;
	
	private String data_inicio;
	
	private String data_fim;
	
	private List<UsuarioResponseDto>lista_de_presenca;
	
}
