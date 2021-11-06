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

import br.com.magnasistemas.cachacariaapi.dto.MarcaDTO;
import br.com.magnasistemas.cachacariaapi.entity.Marca;
import br.com.magnasistemas.cachacariaapi.service.MarcaService;

@Path("marca")
public class MarcaController {
	
	@Inject
	private MarcaService marcaService;

	ModelMapper modelmapperMarc = new ModelMapper();

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postMarca(MarcaDTO marcaDTO) {

		try {
			marcaService.postMarca(modelmapperMarc.map(marcaDTO, Marca.class));
			return Response.status(Response.Status.CREATED).entity(marcaDTO).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getAllMarcas() {
		List<Marca> marca = null;
		List<MarcaDTO> marcaDTO = null;

		try {
			marca = marcaService.findAllMarca();
			marcaDTO = marca.stream().map(marcs -> modelmapperMarc.map(marcs, MarcaDTO.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(marcaDTO).build();
	}

	@GET
	@Path("{id}") // tratar erro
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response findMarcaById(@PathParam("id") long id) {

		MarcaDTO marcaDTO = modelmapperMarc.map(marcaService.findByIdMarca(id), MarcaDTO.class);
		try {
			if (marcaDTO != null) {
				return Response.ok(marcaDTO).build();
			} else {
				throw new Exception("Id não encontrado!");
			}

		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Id não encontrado!").build();
		}
	}

	@GET
	@Path("/nome/{nome}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response findWithNameMarca(@PathParam("nome") String nome) {
		List<MarcaDTO> marcaDTO = null;
		try {
			marcaDTO = marcaService.findWithNameMarca(nome);
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Não foi passado nenhum nome!").build();
		}
		return Response.ok(marcaDTO).build();
	}

	@PUT
	@Path("{id}") //
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putMarca(@PathParam("id") long id, Marca marca) {
		MarcaDTO marcaId = null;
		try {
			marcaId = marcaService.putMarca(id, marca);
			Marca marcaExiste = marcaService.findByIdMarca(id);
			if (marcaExiste == null) {
				throw new Exception();
			}
			return Response.status(Response.Status.OK).entity(marcaId).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Id não encontrado!").build();
		}

	}

	@DELETE
	@Path("{id}") //
	public Response deleteMarca(@PathParam("id") long id) {
		try {
			Marca marcaId = marcaService.findByIdMarca(id);
			if (marcaId != null) {
				marcaService.deleteMarca(marcaId);
			} else {
				throw new Exception("Id não encontrado!");
			}
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		return Response.ok().build();
	}
}
