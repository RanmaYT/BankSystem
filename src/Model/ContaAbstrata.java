package Model;

import SingletonRepositories.ContaRepository;
import SingletonRepositories.IStorable;
import State.ContaNegativada;
import State.ContaPositiva;
import State.IContaState;

public abstract class ContaAbstrata implements IStorable {
    private int id;
    private static int idCount = 1;

    private double saldo;
    private String emailTitular;
    private IContaState estadoConta;

    public ContaAbstrata(double saldo, String emailTitular, IContaState state) {
        this.saldo = saldo;
        this.emailTitular = emailTitular;
        this.estadoConta = state;

        this.id = idCount;
        idCount++;
    }

    public void mudarEstado(IContaState novoEstado){
        estadoConta = novoEstado;

        // gambiarra: classe de dados fazendo lógica de négocio
        ContaRepository.getInstance().atualizarLinha(emailTitular, converterParaStringArmazenavel());
    }

    public void creditar(double valor){
        if(!estadoConta.podeCreditar()) { return; }

        saldo += valor;

        if(saldo >= 0 && estadoConta.getClass() != ContaPositiva.class) {
            System.out.println("A conta agora está positivada");
            mudarEstado(new ContaPositiva());
        }

        // gambiarra: classe de dados fazendo lógica de négocio
        ContaRepository.getInstance().atualizarLinha(emailTitular, converterParaStringArmazenavel());
    }

    public void debitar(double valor){
        if(!estadoConta.podeDebitar()) { return; }

        saldo -= valor;

        if(saldo < 0) {
            System.out.println("A conta agora está negativada");
            mudarEstado(new ContaNegativada());
        }

        // gambiarra: classe de dados fazendo lógica de négocio
        ContaRepository.getInstance().atualizarLinha(emailTitular, converterParaStringArmazenavel());
    }

    public void deletarConta(){
        if(!estadoConta.podeDeletarConta()) { return; }
    }

    // Getters e Setters
    public double getSaldo(){
        return saldo;
    }

    public String getEmailTitular() { return emailTitular; }

    @Override
    public String converterParaStringArmazenavel() {
        String textoArmazenavel = String.format("{saldo=%.2f;emailTitular=%s;estadoConta=%s}",
                saldo, emailTitular, estadoConta.getStateName()).replace(",", ".");;

        return textoArmazenavel;
    }

    @Override
    // Não está funcionando ainda
    public int getId() { return id; }

    @Override
    public String toString(){
        return String.format("Saldo: %.2f\n" +
                "Email titular: %s\n" +
                "Estado da conta: %s", saldo, emailTitular, estadoConta.getStateName());
    }
}
