package Model;

import SingletonRepositories.IStorable;

public class OperacaoExtratavel implements IStorable {
    private String nomeOperacao;
    private double valorOperacao;

    public OperacaoExtratavel(String nomeOperacao, double valorOperacao){
        this.nomeOperacao = nomeOperacao;
        this.valorOperacao = valorOperacao;
    }

    public String getNomeOperacao(){
        return nomeOperacao;
    }

    public double getValorOperacao(){
        return valorOperacao;
    }

    @Override
    public String converterParaStringArmazenavel() {
        return String.format("{nomeOperacao=%s,valorOperacao=%.2f}", nomeOperacao, valorOperacao);
    }

    @Override
    public int getId() {
        return 0;
    }
}
