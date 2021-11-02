package br.com.magnasistemas.cachacariaapi.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.magnasistemas.cachacariaapi.dao.ProdutoDAO;
import br.com.magnasistemas.cachacariaapi.entity.Produto;

@Stateless
public class ProdutoService {

	@Inject
	private ProdutoDAO dao;

	public List<Produto> findAllProduto()  {
		
		return dao.findAllProduto();
	}
	
	public Produto findByIdProduto(long id) {
		
		 Produto produtoId = dao.findProdutoBYid(id);
		 return produtoId; 
	}

	public Produto postProduto(Produto produto) {
		dao.postProduto(produto);
		return produto;
	}

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

/*	public List<Produto> findAllProduto() {
		return dao.findAllProduto();
	}
	
	public Produto findByIdProduto(long id) {
		
		 Produto produtoId = dao.findProdutoBYid(id);
		 return produtoId; 
	}

	public Produto postProduto(Produto produto) {
		dao.postProduto(produto);
		return produto;
	}

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
	}*/
