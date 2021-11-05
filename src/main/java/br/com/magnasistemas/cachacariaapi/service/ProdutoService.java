package br.com.magnasistemas.cachacariaapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.modelmapper.ModelMapper;

import br.com.magnasistemas.cachacariaapi.dao.ProdutoDAO;
import br.com.magnasistemas.cachacariaapi.dto.ProdutoDTO;
import br.com.magnasistemas.cachacariaapi.entity.Produto;

@Stateless
public class ProdutoService {

	@Inject
	private ProdutoDAO dao;

	ModelMapper modelmapper = new ModelMapper();
	//ok com dto
	public ProdutoDTO postProduto(Produto produto) {

		Produto prod = dao.postProduto(produto);

		return modelmapper.map(prod, ProdutoDTO.class);
	}

	public List<Produto> findAllProduto() {

		return dao.findAllProduto();
	}

	public Produto findByIdProduto(long id) {

		Produto produtoId = dao.findProdutoBYid(id);
		return produtoId;
	}

	public List<ProdutoDTO> findWithName(String nome) {

		List<Produto> produto = dao.findWithName(nome);
		List<ProdutoDTO> produtosDTO = produto.stream().map(prods -> modelmapper.map(prods, ProdutoDTO.class))
				.collect(Collectors.toList());
		return produtosDTO ; 
	}

	public ProdutoDTO putProduto(long id, Produto produto) {

		Produto produtoAchado = dao.findProdutoBYid(id);
		produtoAchado.setNome(produto.getNome());
		produtoAchado.setPreco(produto.getPreco());
		produtoAchado.setSabor(produto.getSabor());
		dao.putProduto(produtoAchado);
		
		ProdutoDTO produtoDTO;
		produtoDTO = modelmapper.map(produtoAchado, ProdutoDTO.class);
		return produtoDTO;
	}

	public void deleteProduto(Produto produto) {
		
		
		if (produto != null) {
			 dao.deleteProduto(produto);
		}else {
			 
		}
		
	}

}

/*
 * public List<Produto> findAllProduto() {
 * 
 * return dao.findAllProduto(); }
 * 
 * public Produto findByIdProduto(long id) {
 * 
 * Produto produtoId = dao.findProdutoBYid(id); return produtoId; }
 * 
 * public List<Produto> findWithName(String nome) {
 * 
 * return dao.findWithName(nome); }
 * 
 * 
 * 
 * 
 * public ProdutoDTO postProduto(Produto produto) {
 * 
 * Produto prod = dao.postProduto(produto);
 * 
 * return modelmapper.map(prod, ProdutoDTO.class); }
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
 * public void deleteProduto(Produto produto) {
 * 
 * dao.deleteProduto(produto); }
 * 
 * }
 * 
 * 
 * public Produto postProduto(Produto produto) { dao.postProduto(produto);
 * return produto; }
 * 
 * 
 */