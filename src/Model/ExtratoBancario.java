package Model;

import java.util.ArrayList;
import java.util.List;

public class ExtratoBancario {
    private List<OperacaoExtratavel> operacoesRealizadas;

    public ExtratoBancario() {
        operacoesRealizadas = new ArrayList<>();
    }

    public void adicionarOperacao(OperacaoExtratavel operacao){
        operacoesRealizadas.add(operacao);
    }
}
