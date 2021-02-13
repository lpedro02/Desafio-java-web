package com.BeHoH.DesafioJavaWeb.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.BeHoH.DesafioJavaWeb.dtos.EventoNewDto;
import com.BeHoH.DesafioJavaWeb.dtos.responses.EventoResponseDto;
import com.BeHoH.DesafioJavaWeb.models.entities.Evento;
import com.BeHoH.DesafioJavaWeb.models.entities.Usuario;

import javassist.tools.rmi.ObjectNotFoundException;

public interface EventoService {

	public Evento Insert(Evento obj);
	
	public List<Evento> FindAll();
	
	public Evento DtotoEntity(EventoNewDto eventonewdto) throws ParseException;
	
	public EventoResponseDto EntitytoResponseDto(Evento evento);
	
	public List<EventoResponseDto> List_EventotoList_EventResponseDto(List<Evento> list_eventos);
	
	public String DatetoString(Date data);
	
	public Date StringtoDate(String dataRecebida) throws ParseException;

	public Evento EventoaddUser(String nome,String nome_usuario ) throws ObjectNotFoundException;
	
	public boolean Verificarusuariolista(List<Usuario> usuarios, String nome);
	
	public boolean ListaCheia(Evento evento);
	
	public Evento Search(String nome) throws ObjectNotFoundException;
	
	public boolean Verificarhoraevento(Date data_inicio);

}
