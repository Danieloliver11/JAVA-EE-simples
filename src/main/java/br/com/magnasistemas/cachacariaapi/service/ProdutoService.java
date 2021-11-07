package br.com.magnasistemas.cachacariaapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.modelmapper.ModelMapper;

import br.com.magnasistemas.cachacariaapi.dao.ProdutoDAO;
import br.com.magnasistemas.cachacariaapi.dto.ProdutoDTO;
import br.com.magnasistemas.cachacariaapi.entity.Produto;

@Stateless
public class ProdutoService {

	@Inject
	private ProdutoDAO dao;

	ModelMapper modelmapper = new ModelMapper();
	

	public ProdutoDTO postProduto(Produto produto) {

		Produto prod = dao.postProduto(produto);

		return modelmapper.map(prod, ProdutoDTO.class);
	}

	public List<Produto> findAllProduto() {

		return dao.findAllProduto();
	}

	public Produto findByIdProduto(long id) {

		Produto produtoId = dao.findProdutoBYid(id);
		if (produtoId == null) {
			throw new NotFoundException();
		}
		return produtoId;
	}

	public List<ProdutoDTO> findWithName(String nome) {

		List<Produto> produto = dao.findWithName(nome);
		List<ProdutoDTO> produtosDTO = produto.stream().map(prods -> modelmapper.map(prods, ProdutoDTO.class))
				.collect(Collectors.toList());

		if (produto.isEmpty()) {
			throw new NotFoundException();
		}
		return produtosDTO;
	}

	public ProdutoDTO putProduto(long id, Produto produto) {

		Produto produtoAchado = dao.findProdutoBYid(id);
		produtoAchado.setNome(produto.getNome());
		produtoAchado.setPreco(produto.getPreco());
		produtoAchado.setSabor(produto.getSabor());

		produtoAchado.setCliente(produto.getCliente());
		produtoAchado.setMarca(produto.getMarca());
		dao.putProduto(produtoAchado);

		ProdutoDTO produtoDTO;
		produtoDTO = modelmapper.map(produtoAchado, ProdutoDTO.class);
		return produtoDTO;
	}

	public void deleteProduto(Produto produto) {
		dao.deleteProduto(produto);

	}

}
