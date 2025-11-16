package Model;

import SingletonRepositories.IStorable;

import java.util.List;

public class ExtratoBancario implements IStorable {
    private List<OperacaoExtratavel> operacoesRealizadas;
    private String cpf;

    public ExtratoBancario(List<OperacaoExtratavel> listaDeOperacoes, String cpf) {
        this.operacoesRealizadas = listaDeOperacoes;
        this.cpf = cpf;
    }

    public void adicionarOperacao(OperacaoExtratavel operacao){
        operacoesRealizadas.add(operacao);
    }

    public List<OperacaoExtratavel> getOperacoesRealizadas(){
        return operacoesRealizadas;
    }

    public String getCpf() {
        return cpf;
    }

}
