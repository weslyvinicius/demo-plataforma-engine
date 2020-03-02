package br.com.itau.co8.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class AtualizaPropostaTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Thread.sleep(5000);
	}

}
