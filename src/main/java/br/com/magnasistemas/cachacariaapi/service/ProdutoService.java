package br.com.magnasistemas.cachacariaapi.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;

import br.com.magnasistemas.cachacariaapi.dao.ProdutoDAO;
import br.com.magnasistemas.cachacariaapi.dto.ProdutoDTO;
import br.com.magnasistemas.cachacariaapi.entity.Produto;
import br.com.magnasistemas.cachacariaapi.settings.ProdutoMapper;

@Stateless
public class ProdutoService {

	@Inject
	private ProdutoDAO dao;

	// public ProdutoMapper mapper;

	/*
	 * public ProdutoService(ProdutoMapper mapper) { super(); this.mapper = mapper;
	 * }
	 */

	public List<Produto> findAllProduto() {

		return dao.findAllProduto();
	}

	public Produto findByIdProduto(long id) {

		Produto produtoId = dao.findProdutoBYid(id);
		return produtoId;
	}
	
	public List<Produto> findWithName(String nome){
		
		return dao.findWithName(nome);
	}

	public Produto postProduto(Produto produto) {
		dao.postProduto(produto);
		return produto;
	}

	/*
	 * public ProdutoDTO postProduto(ProdutoDTO produtoDTO) {
	 * dao.postProduto(mapper.paraEntity(produtoDTO)); return produtoDTO; }
	 */

	public Produto putProduto(long id, Produto produto) {

		Produto produtoAchado = dao.findProdutoBYid(id);
		produtoAchado.setNome(produto.getNome());
		produtoAchado.setPreco(produto.getPreco());
		produtoAchado.setSabor(produto.getSabor());
		dao.putProduto(produtoAchado);

		return produtoAchado;
	}

	public Produto findProdutoBYid(long id) {

		return dao.findProdutoBYid(id);
	}

	public void deleteProduto(Produto produto) {

		dao.deleteProduto(produto);
	}
	
	
}

/*
 * public List<Produto> findAllProduto() { return dao.findAllProduto(); }
 * 
 * public Produto findByIdProduto(long id) {
 * 
 * Produto produtoId = dao.findProdutoBYid(id); return produtoId; }
 * 
 * public Produto postProduto(Produto produto) { dao.postProduto(produto);
 * return produto; }
 * 
 * public Produto putProduto(long id, Produto produto) {
 * 
 * Produto produtoAchado = dao.findProdutoBYid(id);
 * produtoAchado.setNome(produto.getNome());
 * produtoAchado.setPreco(produto.getPreco());
 * produtoAchado.setSabor(produto.getSabor()); dao.putProduto(produtoAchado);
 * 
 * return produtoAchado; }
 * 
 * public Produto findProdutoBYid(long id) {
 * 
 * return dao.findProdutoBYid(id); }
 * 
 * public void deleteProduto(Produto produto) {
 * 
 * dao.deleteProduto(produto); }
 */
