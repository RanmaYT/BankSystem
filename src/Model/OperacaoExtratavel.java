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
}
