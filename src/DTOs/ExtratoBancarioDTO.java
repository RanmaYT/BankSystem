package DTOs;

import java.util.ArrayList;
import java.util.List;

public class ExtratoBancarioDTO {
    private List<OperacaoExtratavelDTO> operacoesRealizadas;

    public ExtratoBancarioDTO() {
        operacoesRealizadas = new ArrayList<>();
    }

    public void adicionarOperacao(OperacaoExtratavelDTO operacao){

    }
}
