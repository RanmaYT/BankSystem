package Model;

import SingletonRepositories.IStorable;

import java.util.ArrayList;
import java.util.List;

public class ExtratoBancario implements IStorable {
    private List<OperacaoExtratavel> operacoesRealizadas;

    public ExtratoBancario(List<OperacaoExtratavel> tipoDeLista) {
        operacoesRealizadas = tipoDeLista;
    }

    public void adicionarOperacao(OperacaoExtratavel operacao){
        operacoesRealizadas.add(operacao);
    }

    public List<OperacaoExtratavel> getOperacoesRealizadas(){
        return operacoesRealizadas;
    }

    @Override
    public String converterParaStringArmazenavel() {
        return "";
    }

    @Override
    public int getId() {
        return 0;
    }
}
