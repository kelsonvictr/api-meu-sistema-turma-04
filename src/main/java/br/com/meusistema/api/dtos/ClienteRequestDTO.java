package br.com.meusistema.api.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;


public record ClienteRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100)
        String nome,

        //@NotBlank(message = "CPF é obrigatório")
        //@Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
        @CPF
        String cpf,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @Valid
        EnderecoDTO endereco
) {}
