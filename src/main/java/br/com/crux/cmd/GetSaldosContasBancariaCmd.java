package br.com.crux.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.crux.builder.SaldosContasBancariaTOBuilder;
import br.com.crux.dao.repository.SaldosContasBancariaRepository;
import br.com.crux.entity.SaldosContasBancaria;
import br.com.crux.exception.NotFoundException;
import br.com.crux.to.SaldosContasBancariaTO;

@Component
public class GetSaldosContasBancariaCmd {
	
	@Autowired private SaldosContasBancariaRepository repository;
	@Autowired private SaldosContasBancariaTOBuilder toBuilder;
	@Autowired private GetUnidadeLogadaCmd getUnidadeLogadaCmd;

	
		public List<SaldosContasBancariaTO> getAllFilter( String tipoContaBancaria, 
												          String nomeBanco, 
												          String numeroAgencia, 
												          String numeroContaBancaria) {
		Long idUnidade = null;
		
		Optional<List<SaldosContasBancaria>> entitys = Optional.empty();
		
		tipoContaBancaria   = StringUtils.isEmpty(tipoContaBancaria.trim())? null : tipoContaBancaria;
		nomeBanco           = StringUtils.isEmpty(nomeBanco.trim())? null : nomeBanco;
		numeroAgencia       = StringUtils.isEmpty(numeroAgencia.trim())? null : numeroAgencia;
		numeroContaBancaria = StringUtils.isEmpty(numeroContaBancaria.trim())? null : numeroContaBancaria;
		
		if (StringUtils.isAllBlank(tipoContaBancaria, nomeBanco, numeroAgencia, numeroContaBancaria)) {
			idUnidade = getUnidadeLogadaCmd.get().getId();
		}
		
		
		entitys = repository.findByFilter(idUnidade, tipoContaBancaria, nomeBanco, numeroAgencia, numeroContaBancaria);
		
		if(entitys.isPresent()) {
			return toBuilder.buildAll(entitys.get());
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

}
