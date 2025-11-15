package Model;

import SingletonRepositories.IStorable;

import java.util.List;

public class ExtratoBancario implements IStorable {
    private List<OperacaoExtratavel> operacoesRealizadas;
    private String cpfTitular;

    public ExtratoBancario(List<OperacaoExtratavel> listaDeOperacoes, String cpfTitular) {
        this.operacoesRealizadas = listaDeOperacoes;
        this.cpfTitular = cpfTitular;
    }

    public void adicionarOperacao(OperacaoExtratavel operacao){
        operacoesRealizadas.add(operacao);
    }

    public List<OperacaoExtratavel> getOperacoesRealizadas(){
        return operacoesRealizadas;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

}
