package br.com.magnasistemas.cachacariaapi.settings;

import java.util.List;
import java.util.stream.Collectors;



import org.modelmapper.ModelMapper;

import br.com.magnasistemas.cachacariaapi.dto.ProdutoDTO;
import br.com.magnasistemas.cachacariaapi.entity.Produto;


public class ProdutoMapper {
	
	
	
	
	 public ModelMapper modelMapper() {
		 
	      ModelMapper modelMapper = new ModelMapper();
	      return modelMapper;
	   }
	   
	 
	 public ProdutoDTO paraDTO(Produto produto) {
			ModelMapper modelMapper = modelMapper();
			return modelMapper.map(produto, ProdutoDTO.class);
		}

		public Produto paraEntity(ProdutoDTO produtoDTO) {
			ModelMapper modelMapper = modelMapper();
			return modelMapper.map(produtoDTO, Produto.class);
			
		}
		
		public List<ProdutoDTO> paraListaDTO(List<Produto> produtos){
			
			return produtos.stream().map(prods -> paraDTO(prods)).collect(Collectors.toList());
		}
		
		
		
}
