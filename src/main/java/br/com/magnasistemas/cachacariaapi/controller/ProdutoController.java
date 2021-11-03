package br.com.magnasistemas.cachacariaapi.controller;

import java.util.List;

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

import br.com.magnasistemas.cachacariaapi.entity.Produto;
import br.com.magnasistemas.cachacariaapi.service.ProdutoService;

@Path("produto")
public class ProdutoController {

	@Inject
	private ProdutoService produtoservice;

	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getAllProduto() {
		List<Produto> produto = null;
		try {
			produto = produtoservice.findAllProduto();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(produto).build();
	}

	@GET
	@Path("{id}") // Ok!
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response findProdutoBYid(@PathParam("id") long id) {

		Produto produto = produtoservice.findProdutoBYid(id);

		try {
			if (produto.getId() == id) {
				return Response.ok(produto).build();
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST // ok
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postProduto(Produto produto) {

		try {
			produtoservice.postProduto(produto);
			return Response.status(Response.Status.CREATED).entity(produto).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Path("{id}")// nao muito
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putProduto(@PathParam("id") long id, Produto produto) {
		Produto produtoid = null;
		try {
			produtoid = produtoservice.putProduto(id, produto);
			return Response.status(Response.Status.OK).entity(produtoid).build();
		} catch (Exception e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

	}

	@DELETE
	@Path("{id}")// no v
	public Response deleteProduto(@PathParam("id") long id) {
		Produto produtoId = produtoservice.findByIdProduto(id);
		produtoservice.deleteProduto(produtoId);
		return Response.ok().build();
	}

}

/*
 * @GET
 * 
 * @Produces(value = MediaType.APPLICATION_JSON) public Response getAllProduto()
 * { List<Produto> produto = null; try { produto =
 * produtoservice.findAllProduto(); } catch (Exception e) { e.printStackTrace();
 * } return Response.ok(produto).build(); }
 * 
 * @GET
 * 
 * @Path("{id}") // no muito
 * 
 * @Produces(value = MediaType.APPLICATION_JSON) public Response
 * findProdutoBYid(@PathParam("id") long id) { try { return
 * Response.ok(produtoservice.findByIdProduto(id)).build(); } catch (Exception
 * e) { e.printStackTrace(); }
 * 
 * return Response.status(Status.HTTP_VERSION_NOT_SUPPORTED).build(); }
 * 
 * @GET
 * 
 * @Path("{id}") // no muito
 * 
 * @Produces(value = MediaType.APPLICATION_JSON) public Response
 * findProdutoBYid(@PathParam("id") long id) {
 * 
 * Produto produto = produtoservice.findProdutoBYid(id);
 * 
 * try { if (produto.getId() == id) { return Response.ok(produto).build(); }else
 * { throw new Exception(); }
 * 
 * } catch (Exception e) { return
 * Response.status(Response.Status.NOT_FOUND).build(); }
 * 
 * @POST // ok
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
 * @Path("{id}")
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
 * @Path("{id}") public Response deleteProduto(@PathParam("id") long id) {
 * Produto produtoId = produtoservice.findByIdProduto(id);
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
