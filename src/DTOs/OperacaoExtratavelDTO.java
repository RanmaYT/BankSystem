package DTOs;

import View.TextColor;

public class OperacaoExtratavelDTO {
    private String nomeOperacao;
    private double valorOperacao;

    public OperacaoExtratavelDTO(String nomeOperacao, double valorOperacao) {
        this.nomeOperacao = nomeOperacao;
        this.valorOperacao = valorOperacao;
    }

    @Override
    public String toString(){
        String corUtilizada = valorOperacao < 0 ? TextColor.RED_BOLD : TextColor.GREEN_BOLD;

        return String.format("Operação: %s\n" +
                "Valor da operação: " + corUtilizada + "R$%,.2f\n" + TextColor.ANSI_RESET, nomeOperacao, valorOperacao);
    }
}
