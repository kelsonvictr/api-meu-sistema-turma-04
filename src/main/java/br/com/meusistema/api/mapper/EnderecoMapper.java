package br.com.meusistema.api.mapper;

import br.com.meusistema.api.dtos.EnderecoDTO;
import br.com.meusistema.api.model.Endereco;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring") // Indica que este Mapper será gerenciado pelo Spring como um Bean
public interface EnderecoMapper {


    Endereco toEntity(EnderecoDTO dto);

    EnderecoDTO toDTO(Endereco endereco);
}
