package br.com.meusistema.api.controller;

import br.com.meusistema.api.model.Fornecedor;
import br.com.meusistema.api.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedores")
@RequiredArgsConstructor // para injetar no construtor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFornecedor(@RequestBody Fornecedor fornecedor) {
        fornecedorService.criarFornecedor(fornecedor);
    }
}
