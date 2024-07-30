package br.com.crux.builder;

import br.com.crux.cmd.GetEmpresaCmd;
import br.com.crux.cmd.GetTipoContratoCmd;
import br.com.crux.cmd.GetUnidadeLogadaCmd;
import br.com.crux.cmd.GetUsuarioLogadoCmd;
import br.com.crux.entity.Contrato;
import br.com.crux.to.ContratoTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ContratoTOBuilder
{

    @Autowired
    private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
    @Autowired
    private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
    @Autowired
    private GetTipoContratoCmd  getTipoContratoCmd;
    @Autowired
    private GetEmpresaCmd       getEmpresaCmd;

    @Autowired
    private TipoContratoTOBuilder     tipoContratoTOBuilder;
    @Autowired
    private EmpresaTOBuilder          empresaTOBuilder;
    @Autowired
    private ProgramaContratoTOBuilder programaContratoTOBuilder;
    @Autowired
    private ProjetoContratoTOBuilder  projetoContratoTOBuilder;
    @Autowired
    private ObjetivoContratoTOBuilder objetivoContratoTOBuilder;

    public ContratoTO buildTO(Contrato entity)
    {
        ContratoTO to = new ContratoTO();

        if (Objects.isNull(entity))
            return to;

        BeanUtils.copyProperties(entity, to);

        to.setTipoContrato(tipoContratoTOBuilder.buildTO(entity.getTipoContrato()));
        to.setEmpresa(empresaTOBuilder.buildTO(entity.getEmpresa()));

        if (entity.getDataInicioVigencia() != null)
            to.setDataInicioVigencia(entity.getDataInicioVigencia().toLocalDate());

        if (entity.getDataFimVigencia() != null)
            to.setDataFimVigencia(entity.getDataFimVigencia().toLocalDate());

        if (entity.getProgramasContrato() != null)
        {
            to.setProgramasContrato(entity.getProgramasContrato().stream().map(p ->
            {
                p.setContrato(entity);
                return programaContratoTOBuilder.buildTO(p);
            }).collect(Collectors.toList()));
        }

        if (entity.getProjetosContrato() != null)
        {
            to.setProjetosContrato(entity.getProjetosContrato().stream().map(p ->
            {
                p.setContrato(entity);
                return projetoContratoTOBuilder.buildTO(p);
            }).collect(Collectors.toList()));
        }

        if (entity.getObjetivosContrato() != null)
        {
            to.setObjetivosContrato(entity.getObjetivosContrato().stream().map(o ->
            {
                o.setContrato(entity);
                return objetivoContratoTOBuilder.buildTO(o);
            }).collect(Collectors.toList()));
        }

        return to;
    }

    public List<ContratoTO> buildAll(List<Contrato> lista)
    {
        return lista.stream().map(this::buildTO).collect(Collectors.toList());
    }

    public Contrato build(ContratoTO to)
    {
        Contrato entity = new Contrato();

        BeanUtils.copyProperties(to, entity);

        Long idInstituicao = getUnidadeLogadaCmd.getUnidadeTO().getInstituicao().getId();
        entity.setIdInstituicao(idInstituicao);
        entity.setUsuarioAlteracao(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());

        entity.setTipoContrato(getTipoContratoCmd.getById(to.getTipoContrato().getId()));
        entity.setEmpresa(getEmpresaCmd.getById(to.getEmpresa().getId()));

        if (to.getDataInicioVigencia() != null)
            entity.setDataInicioVigencia(to.getDataInicioVigencia().atStartOfDay());
        if (to.getDataFimVigencia() != null)
            entity.setDataFimVigencia(to.getDataFimVigencia().atStartOfDay());

        if (to.getProgramasContrato() != null)
        {
            entity.setProgramasContrato(to.getProgramasContrato().stream().map(p ->
            {
                p.setContrato(to);
                return programaContratoTOBuilder.build(p);
            }).collect(Collectors.toList()));
        }

        if (to.getProjetosContrato() != null)
        {
            entity.setProjetosContrato(to.getProjetosContrato().stream().map(p ->
            {
                p.setContrato(to);
                return projetoContratoTOBuilder.build(p);
            }).collect(Collectors.toList()));
        }

        if (to.getObjetivosContrato() != null)
        {
            entity.setObjetivosContrato(to.getObjetivosContrato().stream().map(o ->
            {
                o.setContrato(to);
                return objetivoContratoTOBuilder.build(o);
            }).collect(Collectors.toList()));
        }

        return entity;
    }

}
