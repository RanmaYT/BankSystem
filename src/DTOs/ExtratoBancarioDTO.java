package DTOs;

import java.util.ArrayList;
import java.util.List;

public class ExtratoBancarioDTO {
    private List<OperacaoExtratavelDTO> operacoesRealizadas;

    public ExtratoBancarioDTO(){
        operacoesRealizadas = new ArrayList<>();
    }

    public void adicionarOperacao(OperacaoExtratavelDTO operacaoExtratavelDTO) {
        operacoesRealizadas.add(operacaoExtratavelDTO);
    }

    @Override
    public String toString(){
        String textoFormatado = "";

        textoFormatado += "======================\n";

        for(OperacaoExtratavelDTO operacaoRealizada : operacoesRealizadas) {
            textoFormatado += operacaoRealizada;
            textoFormatado += "======================\n";
        }

        return textoFormatado;
    }
}
