package br.com.magnasistemas.cachacariaapi.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.magnasistemas.cachacariaapi.entity.Produto;

@Stateless
public class ProdutoDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Produto> findAllProduto() {
		return em.createQuery("SELECT ae FROM Produto ae", Produto.class).getResultList();
	}

	public Produto findProdutoBYid(long id) {

		return em.find(Produto.class, id);
	}

	public List<Produto> findWithName(String name) {
		return em.createQuery("SELECT c FROM Produto c WHERE c.nome LIKE :custName")
				.setParameter("custName", name + "%").getResultList();
	}

	public Produto postProduto(Produto produto) {
		em.persist(produto);
		return produto;
	}

	public Produto putProduto(Produto produto) {
		// IllegalArgumentException TransactionRequiredException
		return em.merge(produto);
	}

	public void deleteProduto(Produto produto) {

		if (!em.contains(produto)) {
			produto = em.merge(produto);
		}
		em.remove(produto);
	}

}
