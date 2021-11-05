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

//
@Path("produto")
public class ProdutoController {

	@Inject
	private ProdutoService produtoservice;	

	ModelMapper modelmapper = new ModelMapper();

	@POST // ok dto ok,
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

	@GET // Ok! mapper ok tentat deixar mais bonito!!!
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
	@Path("{id}") // Ok mapper ok!
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response findProdutoBYid(@PathParam("id") long id) {

		ProdutoDTO produtoDTO = modelmapper.map(produtoservice.findByIdProduto(id), ProdutoDTO.class);
		try {

			return Response.ok(produtoDTO).build();

		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@GET
	@Path("/nome/{nome}") // mapper ok! Arrumar para achar todos por qualquer letra
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response findWithName(@PathParam("nome") String nome) {
		List<ProdutoDTO> produtoDTO = null;
		try {
			produtoDTO = produtoservice.findWithName(nome);
		} catch (Exception e) {
			
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}
		return Response.ok(produtoDTO).build();
	}

	@PUT
	@Path("{id}") // erro nao muito, dto ok
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putProduto(@PathParam("id") long id, Produto produto) {
		ProdutoDTO produtoid = null;
		try {
			produtoid = produtoservice.putProduto(id, produto);
			Produto prod = produtoservice.findByIdProduto(id);
			if (prod == null) {
				throw new Exception("Id não encontrado!");
			}
			return Response.status(Response.Status.OK).entity(produtoid).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

	}

	@DELETE
	@Path("{id}") // dto ok ERRO ok
	public Response deleteProduto(@PathParam("id") long id) {
		try {
			Produto produtoId = produtoservice.findByIdProduto(id);
			if (produtoId != null) {
				produtoservice.deleteProduto(produtoId);
			} else {
				throw new Exception("Id não encontrado!");
			}
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

		return Response.ok().build();
	}

}

/*
 * @POST // ok,
 * 
 * @Consumes(value = MediaType.APPLICATION_JSON)
 * 
 * @Produces(MediaType.APPLICATION_JSON) public Response postProduto(Produto
 * produto) {
 * 
 * try { produtoservice.postProduto(produto); return
 * Response.status(Response.Status.CREATED).entity(produto).build(); } catch
 * (Exception e) { return
 * Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage())
 * .build(); } } //mapper
 * 
 * @POST // ok,
 * 
 * @Consumes(value = MediaType.APPLICATION_JSON)
 * 
 * @Produces(MediaType.APPLICATION_JSON) public Response postProduto(Produto
 * produto) {
 * 
 * try { produtoservice.postProduto(modelmapper.map(produto, ProdutoDTO.class));
 * return Response.status(Response.Status.CREATED).entity(produto).build(); }
 * catch (Exception e) { return
 * Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage())
 * .build(); } }
 */

/*
 * antes do mapper.
 * 
 * @GET
 * 
 * @Produces(value = MediaType.APPLICATION_JSON) public Response getAllProduto()
 * { List<Produto> produto = null; try { produto =
 * produtoservice.findAllProduto(); } catch (Exception e) { e.printStackTrace();
 * } return Response.ok(produto).build(); }
 * 
 * @GET
 * 
 * @Path("{id}") // Ok!
 * 
 * @Produces(value = MediaType.APPLICATION_JSON) public Response
 * findProdutoBYid(@PathParam("id") long id) {
 * 
 * Produto produto = produtoservice.findProdutoBYid(id);
 * 
 * try { if (produto.getId() == id) { return Response.ok(produto).build(); }
 * else { throw new Exception(); }
 * 
 * } catch (Exception e) { return
 * Response.status(Response.Status.NOT_FOUND).build(); } }
 * 
 * @POST // ok,
 * 
 * @Consumes(value = MediaType.APPLICATION_JSON)
 * 
 * @Produces(MediaType.APPLICATION_JSON) public Response postProduto(Produto
 * produto) {
 * 
 * try { produtoservice.postProduto(produto); return
 * Response.status(Response.Status.CREATED).entity(produto).build(); } catch
 * (Exception e) { return
 * Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage())
 * .build(); } }
 * 
 * @PUT
 * 
 * @Path("{id}")// nao muito
 * 
 * @Consumes(value = MediaType.APPLICATION_JSON)
 * 
 * @Produces(MediaType.APPLICATION_JSON) public Response
 * putProduto(@PathParam("id") long id, Produto produto) { Produto produtoid =
 * null; try { produtoid = produtoservice.putProduto(id, produto); return
 * Response.status(Response.Status.OK).entity(produtoid).build(); } catch
 * (Exception e) { return
 * Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); }
 * 
 * }
 * 
 * @DELETE
 * 
 * @Path("{id}")// no v public Response deleteProduto(@PathParam("id") long id)
 * { Produto produtoId = produtoservice.findByIdProduto(id);
 * produtoservice.deleteProduto(produtoId); return Response.ok().build(); }
 * 
 */

/*
 * @GET
 * 
 * @Produces(value = MediaType.APPLICATION_JSON) public Response getAllProduto()
 * { return Response.ok(produtoservice.findAllProduto()).build(); }
 * 
 * @GET
 * 
 * @Path("{id}")
 * 
 * @Produces(value = MediaType.APPLICATION_JSON) public Response
 * findProdutoBYid(@PathParam("id") long id) { return
 * Response.ok(produtoservice.findByIdProduto(id)).build(); }
 * 
 * @POST
 * 
 * 
 * @Consumes(value = MediaType.APPLICATION_JSON) public Response
 * postProduto(Produto produto) { produtoservice.postProduto(produto); return
 * Response.status(201).build(); }
 * 
 * @PUT
 * 
 * @Path("{id}")
 * 
 * @Consumes(value = MediaType.APPLICATION_JSON) public Response
 * putProduto(@PathParam("id") long id, Produto produto) {
 * produtoservice.putProduto(id,produto); return Response.status(200).build(); }
 * 
 * @DELETE
 * 
 * @Path("{id}") public Response deleteProduto(@PathParam("id") long id) {
 * Produto produtoId = produtoservice.findByIdProduto(id);
 * produtoservice.deleteProduto(produtoId); return Response.ok().build(); }
 */
