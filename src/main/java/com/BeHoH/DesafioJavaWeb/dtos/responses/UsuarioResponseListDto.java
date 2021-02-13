package com.BeHoH.DesafioJavaWeb.dtos.responses;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseListDto {

	private String nome;
	
	private List<EventoResponseDto>lista_eventos;
			
}
