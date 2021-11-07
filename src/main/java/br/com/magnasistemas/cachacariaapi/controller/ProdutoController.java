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

import br.com.magnasistemas.cachacariaapi.dto.ProdutoDTO;
import br.com.magnasistemas.cachacariaapi.entity.Produto;
import br.com.magnasistemas.cachacariaapi.service.ProdutoService;

@Path("produto")
public class ProdutoController {

	@Inject
	private ProdutoService produtoservice;

	ModelMapper modelmapper = new ModelMapper();

	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postProduto(ProdutoDTO produtoDTO) {

		try {
			produtoservice.postProduto(modelmapper.map(produtoDTO, Produto.class));
			return Response.status(Response.Status.CREATED).entity(produtoDTO).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getAllProduto() {
		List<Produto> produto = null;
		List<ProdutoDTO> produtoDTO = null;

		try {
			produto = produtoservice.findAllProduto();
			produtoDTO = produto.stream().map(prods -> modelmapper.map(prods, ProdutoDTO.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(produtoDTO).build();
	}

	@GET
	@Path("{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response findProdutoBYid(@PathParam("id") long id) {

		try {
			ProdutoDTO produtoDTO = modelmapper.map(produtoservice.findByIdProduto(id), ProdutoDTO.class);
			return Response.ok(produtoDTO).build();

		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("ID NÃO ENCONTRADO!").build();
		}
	}

	@GET
	@Path("/nome/{nome}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response findWithName(@PathParam("nome") String nome) {
		List<ProdutoDTO> produtoDTO = null;
		try {
			produtoDTO = produtoservice.findWithName(nome);
			return Response.ok(produtoDTO).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("NAO FOI PASSADO NENHUM NOME EXISTENTE!").build();
		}

	}

	@PUT
	@Path("{id}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putProduto(@PathParam("id") long id, Produto produto) {
		ProdutoDTO produtoid = null;
		try {
			produtoid = produtoservice.putProduto(id, produto);
			Produto prod = produtoservice.findByIdProduto(id);
			if (prod == null) {
				throw new Exception();
			}
			return Response.status(Response.Status.OK).entity(produtoid).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Id não encontrado!").build();
		}

	}

	@DELETE
	@Path("{id}")
	public Response deleteProduto(@PathParam("id") long id) {
		try {
			Produto produtoId = produtoservice.findByIdProduto(id);
			if (produtoId != null) {
				produtoservice.deleteProduto(produtoId);
			} else {
				throw new Exception("Id não encontrado!");
			}
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity("Id não encontrado!").build();
		}

		return Response.ok().build();
	}

}
