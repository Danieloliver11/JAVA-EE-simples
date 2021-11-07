package br.com.magnasistemas.cachacariaapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.modelmapper.ModelMapper;

import br.com.magnasistemas.cachacariaapi.dao.MarcaDAO;
import br.com.magnasistemas.cachacariaapi.dto.MarcaDTO;
import br.com.magnasistemas.cachacariaapi.entity.Marca;

@Stateless
public class MarcaService {
	@Inject
	private MarcaDAO dao;

	ModelMapper modelmapperMarc = new ModelMapper();

	public MarcaDTO postMarca(Marca marca) {

		Marca marc = dao.postMarca(marca);

		return modelmapperMarc.map(marc, MarcaDTO.class);
	}

	public List<Marca> findAllMarca() {

		return dao.findAllMarca();
	}

	public Marca findByIdMarca(long id) {

		Marca marcaId = dao.findMarcaByid(id);
		return marcaId;
	}

	public List<MarcaDTO> findWithNameMarca(String nome) {

		List<Marca> marca = dao.findMarcaWithName(nome);
		List<MarcaDTO> marcaDTO = marca.stream().map(marcs -> modelmapperMarc.map(marcs, MarcaDTO.class))
				.collect(Collectors.toList());

		if (marca.isEmpty()) {
			throw new NotFoundException();
		}
		return marcaDTO;
	}

	public MarcaDTO putMarca(long id, Marca marca) {

		Marca marcaAchada = dao.findMarcaByid(id);
		marcaAchada.setNome(marca.getNome());
		marcaAchada.setFundacao(marca.getFundacao());
		marcaAchada.setProdutos(marca.getProdutos());

		dao.putCliente(marcaAchada);

		MarcaDTO marcaDTO = modelmapperMarc.map(marcaAchada, MarcaDTO.class);
		return marcaDTO;
	}

	public void deleteMarca(Marca marca) {
		dao.deleteMarca(marca);
	}

}
