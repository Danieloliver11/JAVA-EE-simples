package br.com.magnasistemas.cachacariaapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.modelmapper.ModelMapper;

import br.com.magnasistemas.cachacariaapi.dao.ClienteDAO;
import br.com.magnasistemas.cachacariaapi.dto.ClienteDTO;
import br.com.magnasistemas.cachacariaapi.entity.Cliente;

@Stateless
public class ClienteService {
	@Inject
	private ClienteDAO dao;

	ModelMapper modelmapperCli = new ModelMapper();

	public ClienteDTO postCliente(Cliente cliente) {

		Cliente cli = dao.postCliente(cliente);

		return modelmapperCli.map(cli, ClienteDTO.class);
	}

	public List<Cliente> findAllCliente() {

		return dao.findAllCliente();
	}

	public Cliente findByIdCliente(long id) {

		Cliente clienteId = dao.findClienteBYid(id);
		return clienteId;
	}

	public List<ClienteDTO> findWithName(String nome) {

		List<Cliente> cliente = dao.findClienteWithName(nome);
		List<ClienteDTO> clienteDTO = cliente.stream().map(prods -> modelmapperCli.map(prods, ClienteDTO.class))
				.collect(Collectors.toList());

		if (cliente.isEmpty()) {
			throw new NotFoundException();
		}
		return clienteDTO;
	}

	public ClienteDTO putCliente(long id, Cliente cliente) {

		Cliente clienteAchado = dao.findClienteBYid(id);
		clienteAchado.setNome(cliente.getNome());
		clienteAchado.setCpf(cliente.getCpf());
		clienteAchado.setAnoNascimento(cliente.getAnoNascimento());
		clienteAchado.setEndereco(cliente.getEndereco());
		clienteAchado.setProdutos(cliente.getProdutos());

		dao.putCliente(clienteAchado);

		ClienteDTO clienteDTO = modelmapperCli.map(clienteAchado, ClienteDTO.class);
		return clienteDTO;
	}

	public void deleteProduto(Cliente cliente) {
		dao.deleteCliente(cliente);
	}

}
