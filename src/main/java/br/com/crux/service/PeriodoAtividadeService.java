package br.com.crux.service;

import br.com.crux.cmd.AlterarPeriodoAtividadeCmd;
import br.com.crux.cmd.CadastrarPeriodoAtividadeCmd;
import br.com.crux.cmd.ExcluirPeriodoAtividadeCmd;
import br.com.crux.cmd.GetPeriodoAtividadeCmd;
import br.com.crux.to.PeriodoAtividadeTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "periodoatividade")
public class PeriodoAtividadeService
{

    @Autowired
    private GetPeriodoAtividadeCmd       getCmd;
    @Autowired
    private CadastrarPeriodoAtividadeCmd cadastrarCmd;
    @Autowired
    private AlterarPeriodoAtividadeCmd   alterarCmd;
    @Autowired
    private ExcluirPeriodoAtividadeCmd   excluirCmd;

    @GetMapping
    public List<PeriodoAtividadeTO> getAll()
    {
        return getCmd.getAllTO();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PeriodoAtividadeTO getById(@PathVariable(name = "id") Long id)
    {
        return getCmd.getTOById(id);
    }

    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PeriodoAtividadeTO> findByFilters(@RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "descricao", required = false) String descricao)
    {
        return getCmd.findByFilters(nome, descricao);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody PeriodoAtividadeTO to)
    {
        cadastrarCmd.cadastrar(to);
    }

    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void alterar(@RequestBody PeriodoAtividadeTO to)
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
