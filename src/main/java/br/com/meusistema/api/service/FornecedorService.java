package br.com.meusistema.api.service;

import br.com.meusistema.api.model.Endereco;
import br.com.meusistema.api.model.Fornecedor;
import br.com.meusistema.api.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FornecedorService {
    // Injeção de Dependencia do Repository no Service
    private final FornecedorRepository fornecedorRepository;

    public void criarFornecedor(Fornecedor fornecedor) {

        // antes de salvar no banco de dados
        // preciso anexar o endereco no fornecedor
        fornecedor.setEndereco(
                new Endereco(
                        null,
                        fornecedor.getEndereco().getLogradouro(),
                        fornecedor.getEndereco().getNumero(),
                        fornecedor.getEndereco().getComplemento(),
                        fornecedor.getEndereco().getBairro(),
                        fornecedor.getEndereco().getCidade(),
                        fornecedor.getEndereco().getEstado(),
                        fornecedor.getEndereco().getPais(),
                        fornecedor.getEndereco().getCep()
                )
        );

        fornecedorRepository.save(fornecedor);
    }

    public List<Fornecedor> listarTodosFornecedores() {
        return fornecedorRepository.findAll();
    }

    public Optional<Fornecedor> buscarFornecedorPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public void deletarFornecedorPorId(Long id) {
        fornecedorRepository.deleteById(id);
    }

    public void atualizarFornecedorPorId(Long id, Fornecedor fornecedor) {
        // PRIMEIRO PASSO: VER SE O FORNECEDOR EXISTE, PARA
        // DEPOIS SOBRESCREVER NELE
        Optional<Fornecedor> fornecedorDoBancoDeDados = buscarFornecedorPorId(id);

        // E SE NÃO EXISTIR??
        if (fornecedorDoBancoDeDados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado!");
        }

        // SE CHEGAR AQUI, SIGNIFICA QUE EXISTE FORNECEDOR COM ESSE ID!
        // ENTÃO VAMOS ATUALIZAR!
        Fornecedor fornecedorSobrescrito = fornecedorDoBancoDeDados.get();

        // COM ESSE FORNECEDOR QUE VAI SER SOBRESCRITO ACIMA,
        // FAÇO OS SETS NECESSÁRIOS PARA ATUALIZAR
        fornecedorSobrescrito.setNomeFantasia(fornecedor.getNomeFantasia());
        fornecedorSobrescrito.setEmail(fornecedor.getEmail());
        fornecedorSobrescrito.setTipoFornecedor(fornecedor.getTipoFornecedor());

        fornecedorRepository.save(fornecedorSobrescrito);
    }





}
