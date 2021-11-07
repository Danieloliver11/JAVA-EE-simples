package br.com.magnasistemas.cachacariaapi.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.magnasistemas.cachacariaapi.dto.MarcaDTO;

@WebService(targetNamespace = "http://soap.cachacariaapi.magnasistemas.com.br/")
public interface MarcaSoap {
	
	@WebMethod(operationName = "todasAsMarcas")
	@WebResult(name = "Marca")
	List<MarcaDTO> getAllMarcasSoap();
	
	@WebMethod(operationName = "todasAsMarcasPorId")
	@WebResult(name = "Marca")
	MarcaDTO getByIdMarcaSoap(@WebParam(name ="id") long id);
	
	
	@WebMethod(operationName = "postarMarca")
	@WebResult(name = "Marca")
	MarcaDTO postMarcaSoap(MarcaDTO marcaDTO);
}
