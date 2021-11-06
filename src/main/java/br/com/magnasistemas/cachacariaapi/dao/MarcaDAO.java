package br.com.magnasistemas.cachacariaapi.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.magnasistemas.cachacariaapi.entity.Marca;

@Stateless
public class MarcaDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Marca> findAllMarca() {
		return em.createQuery("SELECT ae FROM Marca ae", Marca.class).getResultList();
	}

	public Marca findMarcaByid(long id) {

		return em.find(Marca.class, id);
	}

	public List<Marca> findMarcaWithName(String name) {
		return em.createQuery("SELECT c FROM Marca c WHERE c.nome LIKE :custName ").setParameter("custName", name + "%")
				.getResultList();
	}
	

	public Marca postMarca(Marca marca) {
		em.persist(marca);
		return marca;
	}

	public Marca putCliente(Marca marca) {
		return em.merge(marca);
	}

	public void deleteMarca(Marca marca) {

		if (!em.contains(marca)) {
			marca = em.merge(marca);
		}
		em.remove(marca);
	}
}
