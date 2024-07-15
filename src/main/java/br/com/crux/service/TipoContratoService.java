package br.com.crux.service;

import br.com.crux.cmd.AlterarTipoContratoCmd;
import br.com.crux.cmd.CadastrarTipoContratoCmd;
import br.com.crux.cmd.ExcluirTipoContratoCmd;
import br.com.crux.cmd.GetTipoContratoCmd;
import br.com.crux.to.TipoContratoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "tipocontrato")
public class TipoContratoService
{

    @Autowired
    private GetTipoContratoCmd       getCmd;
    @Autowired
    private CadastrarTipoContratoCmd cadastrarCmd;
    @Autowired
    private AlterarTipoContratoCmd   alterarCmd;
    @Autowired
    private ExcluirTipoContratoCmd   excluirCmd;

    @GetMapping
    public List<TipoContratoTO> getAll()
    {
        return getCmd.getAllTO();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TipoContratoTO getById(@PathVariable(name = "id") Long id)
    {
        return getCmd.getTOById(id);
    }

    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TipoContratoTO> findByFilters(@RequestParam(name = "descricao", required = false) String descricao)
    {
        return getCmd.getTOByDescricao(descricao);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody TipoContratoTO to)
    {
        cadastrarCmd.cadastrar(to);
    }

    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void alterar(@RequestBody TipoContratoTO to)
    {
        alterarCmd.alterar(to);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public void excluir(@PathVariable(name = "id") Long id)
    {
        excluirCmd.excluir(id);
    }

}
