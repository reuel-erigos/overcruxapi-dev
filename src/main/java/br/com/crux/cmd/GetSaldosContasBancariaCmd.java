package br.com.crux.cmd;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.SaldosContasBancariaTOBuilder;
import br.com.crux.dao.SaldoContaBancariaDao;
import br.com.crux.dao.dto.SaldoContaBancariaDTO;
import br.com.crux.dao.repository.SaldosContasBancariaRepository;
import br.com.crux.entity.SaldosContasBancaria;
import br.com.crux.exception.NotFoundException;
import br.com.crux.infra.util.Java8DateUtil;
import br.com.crux.rule.SaldoContaBancariaRule;
import br.com.crux.to.SaldosContasBancariaTO;

@Component
public class GetSaldosContasBancariaCmd {
	
	@Autowired private SaldosContasBancariaRepository repository;
	@Autowired private SaldosContasBancariaTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;
	@Autowired private SaldoContaBancariaDao saldoContaBancariaDao;
	@Autowired private SaldoContaBancariaRule saldoContaBancariaRule;
	 
	
	public SaldoContaBancariaDTO getSaldoContaBancaria(LocalDateTime dataInicio, LocalDateTime dataFinal, Long idContaBancaria) {
		saldoContaBancariaRule.verificar(dataInicio, dataFinal, idContaBancaria);
		return saldoContaBancariaDao.getSaldoContaBancaria(dataInicio.toLocalDate(), dataFinal.toLocalDate(), idContaBancaria);
	}

	
	public List<SaldosContasBancariaTO> getAllFilter( String tipoContaBancaria, 
												          String nomeBanco, 
												          String numeroAgencia, 
												          String numeroContaBancaria,
												          final LocalDateTime dataInicio,
												          final LocalDateTime dataFim) {
		Long idUnidade = null;
		
		Optional<List<SaldosContasBancaria>> entitys = Optional.empty();
		
		tipoContaBancaria   = StringUtils.isEmpty(tipoContaBancaria.trim())? null : tipoContaBancaria;
		nomeBanco           = StringUtils.isEmpty(nomeBanco.trim())? null : nomeBanco;
		numeroAgencia       = StringUtils.isEmpty(numeroAgencia.trim())? null : numeroAgencia;
		numeroContaBancaria = StringUtils.isEmpty(numeroContaBancaria.trim())? null : numeroContaBancaria;
		
		if (StringUtils.isAllBlank(tipoContaBancaria, nomeBanco, numeroAgencia, numeroContaBancaria) && Objects.isNull(dataInicio) && Objects.isNull(dataFim)) {
			idUnidade = getUnidadeLogadaCmd.get().getId();
		}		
		
		entitys = repository.findByFilter(idUnidade, tipoContaBancaria, nomeBanco, numeroAgencia, numeroContaBancaria);
		
		if(entitys.isPresent()) {
			List<SaldosContasBancariaTO> saldos = toBuilder.buildAll(entitys.get());
			
			if(Objects.nonNull(dataInicio) || Objects.nonNull(dataFim)) {
				return saldos.stream().filter( saldo -> {
					return Java8DateUtil.isVigente(saldo.getDataSaldo().toLocalDate(),  
							                       dataInicio.toLocalDate(), 
							                       dataFim.toLocalDate());
				}).collect(Collectors.toList());			
			}
			
			return saldos;
		}
		
		return new ArrayList<SaldosContasBancariaTO>();
	}

	public SaldosContasBancariaTO getTOById(Long id) {
		SaldosContasBancaria entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Saldo da conta bancaria não encontrado."));
		return toBuilder.buildTO(entity);
	}

	public SaldosContasBancaria getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}

	public SaldosContasBancariaTO getPorConta(Long id) {
		SaldosContasBancaria entity = repository.getPorConta(id).orElseThrow(() -> new NotFoundException("Saldo da conta bancaria não encontrado."));
		return toBuilder.buildTO(entity);
	}	

}
