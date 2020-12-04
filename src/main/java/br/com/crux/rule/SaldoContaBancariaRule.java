package br.com.crux.rule;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.crux.exception.CamposObrigatoriosException;

@Component
public class SaldoContaBancariaRule {

	public void verificar(LocalDateTime dataInicio, LocalDateTime dataFim, Long idContaBancaria) {
		if (Objects.isNull(dataInicio)) {
			throw new CamposObrigatoriosException("Data Início deve ser informada.");
		}

		if (Objects.isNull(dataFim)) {
			throw new CamposObrigatoriosException("Data Fim deve ser informada.");
		}

		if (Objects.isNull(idContaBancaria)) {
			throw new CamposObrigatoriosException("Conta bancária deve ser informada.");
		}

	}
}
