package Model;

public class OperacaoExtratavel {
    private String nomeOperacao;
    private double valorDaOperacao;

    public OperacaoExtratavel(String nomeOperacao, double valorDaOperacao){
        this.nomeOperacao = nomeOperacao;
        this.valorDaOperacao = valorDaOperacao;
    }

    public String getNomeOperacao(){
        return nomeOperacao;
    }

    public double getValorDaOperacao(){
        return valorDaOperacao;
    }
}
