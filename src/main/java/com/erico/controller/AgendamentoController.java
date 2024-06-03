package com.erico.controller;

import com.erico.dto.CreateAgendamentoDto;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/agendamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgendamentoController {
	
	@POST
	public Response createAgendamento(CreateAgendamentoDto createAgendamentoDto) {
		return Response.created(null).build();
	}

}
