package br.com.crux.service;

import br.com.crux.cmd.AlterarEixoAtividadeCmd;
import br.com.crux.cmd.CadastrarEixoAtividadeCmd;
import br.com.crux.cmd.ExcluirEixoAtividadeCmd;
import br.com.crux.cmd.GetEixoAtividadeCmd;
import br.com.crux.to.EixoAtividadeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "eixoatividade")
public class EixoAtividadeService
{

    @Autowired
    private GetEixoAtividadeCmd       getCmd;
    @Autowired
    private CadastrarEixoAtividadeCmd cadastrarCmd;
    @Autowired
    private AlterarEixoAtividadeCmd   alterarCmd;
    @Autowired
    private ExcluirEixoAtividadeCmd   excluirCmd;

    @GetMapping
    public List<EixoAtividadeTO> getAll()
    {
        return getCmd.getAllTO();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EixoAtividadeTO getById(@PathVariable(name = "id") Long id)
    {
        return getCmd.getTOById(id);
    }

    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EixoAtividadeTO> findByFilters(@RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "descricao", required = false) String descricao)
    {
        return getCmd.findByFilters(nome, descricao);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody EixoAtividadeTO to)
    {
        cadastrarCmd.cadastrar(to);
    }

    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void alterar(@RequestBody EixoAtividadeTO to)
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
