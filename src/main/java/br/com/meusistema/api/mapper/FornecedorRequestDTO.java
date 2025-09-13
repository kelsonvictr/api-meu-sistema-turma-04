package br.com.meusistema.api.mapper;

import br.com.meusistema.api.dtos.EnderecoDTO;
import br.com.meusistema.api.enums.TipoFornecedorEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;


public record FornecedorRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100)
        String nome,


        @CNPJ
        String cnpj,

        @NotNull(message = "Tipo de fornecedor é obrigatório")
        TipoFornecedorEnum tipoFornecedor,


        @Valid
        EnderecoDTO endereco

) {}
