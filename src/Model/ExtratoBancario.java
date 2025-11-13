package Model;

import SingletonRepositories.ExtratoRepository;
import SingletonRepositories.IStorable;

import java.util.List;

public class ExtratoBancario implements IStorable {
    private List<OperacaoExtratavel> operacoesRealizadas;
    private String emailTitular;

    public ExtratoBancario(List<OperacaoExtratavel> tipoDeLista, String emailTitular) {
        operacoesRealizadas = tipoDeLista;
        this.emailTitular = emailTitular;
    }

    public void adicionarOperacao(OperacaoExtratavel operacao){
        operacoesRealizadas.add(operacao);
    }

    public List<OperacaoExtratavel> getOperacoesRealizadas(){
        return operacoesRealizadas;
    }

    public String getEmailTitular() {
        return emailTitular;
    }

}
