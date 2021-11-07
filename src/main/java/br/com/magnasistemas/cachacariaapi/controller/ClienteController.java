package br.com.magnasistemas.cachacariaapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.modelmapper.ModelMapper;

import br.com.magnasistemas.cachacariaapi.dto.ClienteDTO;
import br.com.magnasistemas.cachacariaapi.entity.Cliente;
import br.com.magnasistemas.cachacariaapi.service.ClienteService;

@Path("cliente")
public class ClienteController {

	@Inject
	private ClienteService clienteService;

	ModelMapper modelmapperCli = new ModelMapper();

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postCliente(ClienteDTO clienteDTO) {

		try {
			clienteService.postCliente(modelmapperCli.map(clienteDTO, Cliente.class));
			return Response.status(Response.Status.CREATED).entity(clienteDTO).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getAllClientes() {
		List<Cliente> cliente = null;
		List<ClienteDTO> clienteDTO = null;

		try {
			cliente = clienteService.findAllCliente();
			clienteDTO = cliente.stream().map(clien -> modelmapperCli.map(clien, ClienteDTO.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(clienteDTO).build();
	}

	@GET
	@Path("{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response findClienteById(@PathParam("id") long id) {

		
		try {
			ClienteDTO clienteDTO = modelmapperCli.map(clienteService.findByIdCliente(id), ClienteDTO.class);
			return  Response.ok(clienteDTO).build();

		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("ID NÃO ENCONTRADO!").build();
		}
	}

	@GET
	@Path("/nome/{nome}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response findWithNameCliente(@PathParam("nome") String nome) {
		List<ClienteDTO> clienteDTO = null;
		try {
			clienteDTO = clienteService.findWithName(nome);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("NAO FOI PASSADO NENHUM NOME EXISTENTE!").build();
		}
		return Response.ok(clienteDTO).build();
	}

	@PUT
	@Path("{id}") //
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putCliente(@PathParam("id") long id, Cliente cliente) {
		ClienteDTO clienteId = null;
		try {
			clienteId = clienteService.putCliente(id, cliente);
			Cliente clien = clienteService.findByIdCliente(id);
			if (clien == null) {
				throw new Exception();
			}
			return Response.status(Response.Status.OK).entity(clienteId).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Id não encontrado!").build();
		}

	}

	@DELETE
	@Path("{id}") //
	public Response deleteCliente(@PathParam("id") long id) {
		try {
			Cliente clienteId = clienteService.findByIdCliente(id);
			if (clienteId != null) {
				clienteService.deleteProduto(clienteId);
			} else {
				throw new Exception("Id não encontrado!");
			}
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		return Response.ok().build();
	}

}
