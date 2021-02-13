package com.BeHoH.DesafioJavaWeb.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BeHoH.DesafioJavaWeb.dtos.EventoNewDto;
import com.BeHoH.DesafioJavaWeb.dtos.responses.EventoResponseDto;
import com.BeHoH.DesafioJavaWeb.models.dao.repositories.EventoRepository;
import com.BeHoH.DesafioJavaWeb.models.entities.Evento;
import com.BeHoH.DesafioJavaWeb.models.entities.Usuario;
import com.BeHoH.DesafioJavaWeb.services.EventoService;
import com.BeHoH.DesafioJavaWeb.services.UsuarioService;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class EventoServiceImpl implements EventoService {

	private static final Logger log = LoggerFactory.getLogger(EventoServiceImpl.class);

	@Autowired
	private EventoRepository eventorepository;
	
	@Autowired
	UsuarioService usuarioservice;

	@Override
	public Evento Insert(Evento obj) {

		log.info("Cadastrando um Evento {}", obj);

		obj = eventorepository.save(obj);

		return obj;
	}

	@Override
	public List<Evento> FindAll() {

		log.info("Listando todos os Eventos{}");

		return eventorepository.findAll();

	}

	@Override
	public Evento DtotoEntity(EventoNewDto eventonewdto) throws ParseException {

		Evento evento = new Evento();

		evento.setNome(eventonewdto.getNome());
		evento.setVagas(eventonewdto.getVagas());
		evento.setData_inicio(StringtoDate(eventonewdto.getData_inicio()));
		evento.setData_fim(StringtoDate(eventonewdto.getData_fim()));

		return evento;

	}

	@Override
	public EventoResponseDto EntitytoResponseDto(Evento evento) {

		EventoResponseDto eventoresponsedto = new EventoResponseDto();

		eventoresponsedto.setNome(evento.getNome());
		eventoresponsedto.setVagas(evento.getVagas());
		eventoresponsedto.setData_inicio(DatetoString(evento.getData_inicio()));
		eventoresponsedto.setData_fim((DatetoString(evento.getData_fim())));
		
		return eventoresponsedto;

	}

	@Override
	public List<EventoResponseDto> List_EventotoList_EventResponseDto(List<Evento> list_eventos) {

		List<EventoResponseDto> list_eventoresponsedtos = new ArrayList<EventoResponseDto>();

		for (Evento evento : list_eventos) {

			EventoResponseDto eventoresponsedto = new EventoResponseDto();

			eventoresponsedto.setNome(evento.getNome());
			eventoresponsedto.setVagas(evento.getVagas());
			eventoresponsedto.setData_inicio(DatetoString(evento.getData_inicio()));
			eventoresponsedto.setData_fim((DatetoString(evento.getData_fim())));
			eventoresponsedto.setLista_de_presenca(usuarioservice.listUsuariotolistUsuarioResponseDto(evento.getUsuarios()));

			list_eventoresponsedtos.add(eventoresponsedto);

		}

		return list_eventoresponsedtos;
	}

	@Override
	public String DatetoString(Date data) {

		SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String stringDate = DateFor.format(data);
		System.out.println("Date Format with dd-MM-yyyy hh:mm:ss : " + stringDate);

		return stringDate;

	}

	@Override
	public Date StringtoDate(String dataRecebida) throws ParseException {
		SimpleDateFormat formato = null;
		try {
			formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		} catch (Exception e) {

			e.printStackTrace();
		}
		Date dataFormatada = formato.parse(dataRecebida);

		return dataFormatada;
	}

	@Override
	public Evento EventoaddUser(String nome_evento, String nome_usuario) throws ObjectNotFoundException {

		Evento eventoobjreturn = new Evento();

		Evento evento = Search(nome_evento);

		boolean listavazia = ListaCheia(evento);
		boolean eventocomecou = Verificarhoraevento(evento.getData_inicio());
		boolean usuarionalista = Verificarusuariolista(evento.getUsuarios(), nome_usuario);
		
		if (eventocomecou == false) {

			if (listavazia == true) {
				
				if (usuarionalista == false) {
				
					List<Usuario> usuarios = evento.getUsuarios();
					Usuario usuario = usuarioservice.Search(nome_usuario);
					usuarios.add(usuario);
					evento.setUsuarios(usuarios);
					evento.setVagas(evento.getVagas() - 1);
					evento.setId(evento.getId());
					evento = eventorepository.save(evento);
					
					
				}
			}
		}
						
		return eventoobjreturn;

	}

	@Override
	public boolean Verificarhoraevento(Date data_inicio) {

		boolean eventoinicou;

		Calendar cal_data_inicio_evento = Calendar.getInstance();

		Calendar cal_data_atual = Calendar.getInstance();

		cal_data_inicio_evento.setTime(data_inicio);
		cal_data_atual.setTime(new Date());

		if (cal_data_atual.before((cal_data_inicio_evento))) {
			eventoinicou = false;
		} else {
			eventoinicou = true;
		}

		return eventoinicou;

	}

	@Override
	public boolean Verificarusuariolista(List<Usuario> usuarios, String nome) {

		boolean usuario_na_lista=false;
		
		for (Usuario usuario : usuarios) {
			if (usuario.getNome().equals(nome)) {
				usuario_na_lista = true;
			} else {

				log.info("esse usuario não exite na lista{}");
				usuario_na_lista = false;
			}
		}

		return usuario_na_lista;

	}

	@Override
	public boolean ListaCheia(Evento evento) {

		boolean listavazia;
		List<Usuario> usuarios = evento.getUsuarios();

		if (evento.getVagas() > usuarios.size()) {

			listavazia = true;

		}

		else {

			listavazia = false;

		}

		return listavazia;

	}

	@Override
	public Evento Search(String nome) throws ObjectNotFoundException {

		Evento evento = eventorepository.findByNome(nome);

		if (evento == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! CPF: " + nome + ", Tipo: " + Evento.class.getName(), null);
		}

		return evento;
	}

}
