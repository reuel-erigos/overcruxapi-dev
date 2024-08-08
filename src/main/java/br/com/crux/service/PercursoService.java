package br.com.crux.service;

import br.com.crux.cmd.AlterarPercursoCmd;
import br.com.crux.cmd.CadastrarPercursoCmd;
import br.com.crux.cmd.ExcluirPercursoCmd;
import br.com.crux.cmd.GetPercursoCmd;
import br.com.crux.to.PercursoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "percurso")
public class PercursoService
{

    @Autowired
    private GetPercursoCmd       getCmd;
    @Autowired
    private CadastrarPercursoCmd cadastrarCmd;
    @Autowired
    private AlterarPercursoCmd   alterarCmd;
    @Autowired
    private ExcluirPercursoCmd   excluirCmd;

    @GetMapping
    public List<PercursoTO> getAll()
    {
        return getCmd.getAllTO();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PercursoTO getById(@PathVariable(name = "id") Long id)
    {
        return getCmd.getTOById(id);
    }

    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PercursoTO> findByFilters(@RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "descricao", required = false) String descricao)
    {
        return getCmd.findByFilters(nome, descricao);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody PercursoTO to)
    {
        cadastrarCmd.cadastrar(to);
    }

    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void alterar(@RequestBody PercursoTO to)
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
