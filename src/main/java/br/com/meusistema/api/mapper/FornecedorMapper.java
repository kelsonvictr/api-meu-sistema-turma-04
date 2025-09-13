package br.com.meusistema.api.mapper;

import br.com.meusistema.api.dtos.FornecedorResponseDTO;
import br.com.meusistema.api.model.Fornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring") // Diz ao Spring para gerenciar esse Mapper como um Bean
public interface FornecedorMapper {


    @Mapping(target = "id", ignore = true)
    Fornecedor toEntity(FornecedorRequestDTO dto);

    FornecedorResponseDTO toDTO(Fornecedor fornecedor);
}
