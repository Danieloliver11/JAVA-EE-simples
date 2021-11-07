package br.com.magnasistemas.cachacariaapi.soap;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.modelmapper.ModelMapper;

import br.com.magnasistemas.cachacariaapi.dto.MarcaDTO;
import br.com.magnasistemas.cachacariaapi.entity.Marca;
import br.com.magnasistemas.cachacariaapi.service.MarcaService;

//-?wsdl serviceName ="MarcaSoap"
@SOAPBinding(style = Style.DOCUMENT,use = Use.LITERAL, parameterStyle = ParameterStyle.WRAPPED)
@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
@WebService(endpointInterface ="br.com.magnasistemas.cachacariaapi.soap.MarcaSoap")
public class MarcaControllerSoap implements MarcaSoap{
	
	@Inject
	private MarcaService marcaService;

	ModelMapper modelmapperMarc = new ModelMapper();
	
	@Override
	@Produces(value = MediaType.APPLICATION_XML)
	public List<MarcaDTO> getAllMarcasSoap() {
		List<Marca> marca = null;
		List<MarcaDTO> marcaDTO = null;

		try {
			marca = marcaService.findAllMarca();
			marcaDTO = marca.stream().map(marcs -> modelmapperMarc.map(marcs, MarcaDTO.class))
					.collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return marcaDTO;
	}

	@Override
	@Produces(value = MediaType.APPLICATION_XML)
	public MarcaDTO getByIdMarcaSoap(long id) {
		MarcaDTO marcaDTO = modelmapperMarc.map(marcaService.findByIdMarca(id), MarcaDTO.class);
		try {
			if (marcaDTO != null) {
				return marcaDTO;
			} else {
				throw new Exception("Id não encontrado!");
			}

		} catch (Exception e) {
			return marcaDTO;
		}
	}

	@Override
	public MarcaDTO postMarcaSoap(MarcaDTO marcaDTO) {
		
		try {
			
			return marcaService.postMarca(modelmapperMarc.map(marcaDTO, Marca.class));
		} catch (Exception e) {
			return null;
		}
		
	}

}
