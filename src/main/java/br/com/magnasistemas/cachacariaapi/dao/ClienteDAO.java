package br.com.magnasistemas.cachacariaapi.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.magnasistemas.cachacariaapi.entity.Cliente;

@Stateless
public class ClienteDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Cliente> findAllCliente() {
		return em.createQuery("SELECT ae FROM Cliente ae", Cliente.class).getResultList();
	}

	public Cliente findClienteBYid(long id) {

		return em.find(Cliente.class, id);
	}

	public List<Cliente> findClienteWithName(String name) {
		return em.createQuery("SELECT c FROM Cliente c WHERE c.nome LIKE :custName").setParameter("custName", name)
				.getResultList();
	}

	public Cliente postCliente(Cliente cliente) {
		em.persist(cliente);
		return cliente;
	}

	public Cliente putCliente(Cliente cliente) {
		return em.merge(cliente);
	}

	public void deleteCliente(Cliente cliente) {

		if (!em.contains(cliente)) {
			cliente = em.merge(cliente);
		}
		em.remove(cliente);
	}

}
