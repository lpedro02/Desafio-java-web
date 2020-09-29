package com.BeHoH.DesafioJavaWeb.services;

import java.util.List;

import com.BeHoH.DesafioJavaWeb.dtos.UsuarioNewDto;
import com.BeHoH.DesafioJavaWeb.models.entities.Usuario;

import javassist.tools.rmi.ObjectNotFoundException;

public interface UsuarioService {

		
	public Usuario Insert(Usuario obj);
	
	public List<Usuario> FindAll();
	
	public UsuarioNewDto EntitytoDto(Usuario usuario);
	
	public Usuario DtotoEntity(UsuarioNewDto usuarionewdto);
	
	public List<UsuarioNewDto> List_UsuariotoList_UsuarioNewDto(List<Usuario> list_usuarios);
	
	public Usuario Search(String nome) throws ObjectNotFoundException;
	
}
