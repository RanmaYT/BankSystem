package Model;

import SingletonRepositories.ContaRepository;
import SingletonRepositories.IStorable;
import State.ContaEncerrada;
import State.ContaNegativada;
import State.ContaPositiva;
import State.IContaState;

public abstract class ContaAbstrata implements IStorable {
    private double saldo;
    private String tipoConta;
    private String emailTitular;
    private String nomeEstado;
    private transient IContaState estadoConta;

    public ContaAbstrata(double saldo, String emailTitular, IContaState state, String tipoConta) {
        this.saldo = saldo;
        this.emailTitular = emailTitular;
        this.estadoConta = state;
        this.nomeEstado = state.getStateName();
        this.tipoConta = tipoConta;
    }

    public void mudarEstado(IContaState novoEstado){
        estadoConta = novoEstado;
        nomeEstado = estadoConta.getStateName();

        // GAMBIARRA MUITO LOUCA
        ContaRepository.getInstance().atualizarLinha(emailTitular, this);
    }

    public void creditar(double valor){
        if(!estadoConta.podeCreditar()) { return; }

        saldo += valor;

        if(saldo >= 0 && estadoConta.getClass() != ContaPositiva.class) {
            System.out.println("A conta agora está positivada");
            mudarEstado(new ContaPositiva());
        }

        // GAMBIARRA MUITO LOUCA
        ContaRepository.getInstance().atualizarLinha(emailTitular, this);
    }

    public void debitar(double valor){
        if(!estadoConta.podeDebitar()) { return; }

        saldo -= valor;

        if(saldo < 0) {
            System.out.println("A conta agora está negativada");
            mudarEstado(new ContaNegativada());
        }

        // GAMBIARRA MUITO LOUCA
        ContaRepository.getInstance().atualizarLinha(emailTitular, this);
    }

    public void deletarConta(){
        if(!estadoConta.podeDeletarConta()) { return; }

        if(saldo != 0) {
            System.out.println("A conta deve estar zerada para ser encerrada!");
            return;
        }

        mudarEstado(new ContaEncerrada());
    }

    // Getters e Setters
    public double getSaldo(){
        return saldo;
    }

    public String getEmailTitular() { return emailTitular; }

    public String getNomeEstado() { return nomeEstado; }

    public String getTipoConta(){ return tipoConta; }

    public IContaState getEstadoConta(){ return estadoConta; }
}
