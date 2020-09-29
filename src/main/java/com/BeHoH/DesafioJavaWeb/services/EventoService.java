package com.BeHoH.DesafioJavaWeb.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.BeHoH.DesafioJavaWeb.dtos.EventoNewDto;
import com.BeHoH.DesafioJavaWeb.dtos.EventoResponseDto;
import com.BeHoH.DesafioJavaWeb.models.entities.Evento;
import com.BeHoH.DesafioJavaWeb.models.entities.Usuario;

import javassist.tools.rmi.ObjectNotFoundException;

public interface EventoService {

	public Evento Insert(Evento obj);
	
	public List<Evento> FindAll();
	
	public Evento DtotoEntity(EventoNewDto eventonewdto);
	
	public EventoResponseDto EntitytoResponseDto(Evento evento);
	
	public List<EventoResponseDto> List_EventotoList_EventResponseDto(List<Evento> list_eventos);
	
	public String DatetoString(Date data);
	
	public Date StringtoDate(String dataRecebida) throws ParseException;

	public Iterable<Usuario> InsertUsuariotoEvento(long id, Usuario usuario);
	
	public Evento Update(String nome,String nome_usuario ) throws ObjectNotFoundException;
	
	public Usuario Verificarusuariolista(List<Usuario> usuarios, String nome);
	
	public boolean ListaCheia(Evento evento);
	
	public Evento Search(String nome) throws ObjectNotFoundException;

}
