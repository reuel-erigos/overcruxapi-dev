package br.com.crux.service;

import br.com.crux.cmd.AlterarContratoCmd;
import br.com.crux.cmd.CadastrarContratoCmd;
import br.com.crux.cmd.ExcluirContratoCmd;
import br.com.crux.cmd.GetContratoCmd;
import br.com.crux.to.ContratoTO;
import br.com.crux.to.TurmasTO;
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
@RequestMapping(value = "contrato")
public class ContratoService
{

    @Autowired
    private GetContratoCmd       getCmd;
    @Autowired
    private CadastrarContratoCmd cadastrarCmd;
    @Autowired
    private AlterarContratoCmd   alterarCmd;
    @Autowired
    private ExcluirContratoCmd   excluirCmd;

    @GetMapping
    public List<ContratoTO> getAll()
    {
        return getCmd.getAllTO();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ContratoTO getById(@PathVariable(name = "id") Long id)
    {
        return getCmd.getTOById(id);
    }

    @GetMapping(path = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContratoTO> findByFilters(@RequestParam(name = "empresa", required = false) Long idEmpresa,
            @RequestParam(name = "programa", required = false) Long idPrograma,
            @RequestParam(name = "projeto", required = false) Long idProjeto,
            @RequestParam(name = "dataInicio", required = false) Long dataInicio,
            @RequestParam(name = "dataFim", required = false) Long dataFim,
            @RequestParam(name = "numeroContrato", required = false) String numeroContrato,
            @RequestParam(name = "valorContrato", required = false) Double valorContrato)
    {
        return getCmd.findByFilters(idEmpresa, idPrograma, idProjeto, dataInicio, dataFim, numeroContrato, valorContrato);
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody ContratoTO to)
    {
        cadastrarCmd.cadastrar(to);
    }

    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void alterar(@RequestBody ContratoTO to)
    {
        alterarCmd.alterar(to);
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public void excluir(@PathVariable(name = "id") Long id)
    {
        excluirCmd.excluir(id);
    }

    @GetMapping(path = "/combo", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContratoTO> getAllCombo() {
        return getCmd.getAllCombo();
    }

}
