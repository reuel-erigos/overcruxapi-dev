package br.com.crux.service;

import br.com.crux.cmd.AlterarTiposContratosCmd;
import br.com.crux.cmd.CadastrarTiposContratosCmd;
import br.com.crux.cmd.ExcluirTiposContratosCmd;
import br.com.crux.cmd.GetTiposContratosCmd;
import br.com.crux.to.GrupoAcoesTO;
import br.com.crux.to.TiposContratosTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "tiposcontratos")
public class TiposContratosService
{

    @Autowired
    private GetTiposContratosCmd       getCmd;
    @Autowired
    private CadastrarTiposContratosCmd cadastrarCmd;
    @Autowired
    private AlterarTiposContratosCmd   alterarCmd;
    @Autowired
    private ExcluirTiposContratosCmd   excluirCmd;

    @GetMapping
    public List<TiposContratosTO> getAll()
    {
        return getCmd.getAllTO();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TiposContratosTO getById(@PathVariable(name = "id") Long id)
    {
        return getCmd.getTOById(id);
    }

    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TiposContratosTO> findByFilters(@RequestParam(name = "descricao", required = false) String descricao)
    {
        return getCmd.getTOByDescricao(descricao);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody TiposContratosTO to)
    {
        cadastrarCmd.cadastrar(to);
    }

    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void alterar(@RequestBody TiposContratosTO to)
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
