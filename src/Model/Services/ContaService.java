package Model.Services;

import Factory.ContaFactory;
import Model.Conta;

import Model.Usuario;

import SingletonRepositories.ContaRepository;
import SingletonSession.SessionManager;
import State.ContaBloqueada;
import State.ContaNegativada;
import State.ContaPositiva;

public class ContaService {
    private SessionManager sessionManager;
    private ContaRepository contaRepo;
    private PagamentoService pagamentoService;

    public ContaService(SessionManager sessionManager, ContaRepository contaRepo, PagamentoService pagamentoService) {
        this.sessionManager = sessionManager;
        this.contaRepo = contaRepo;
        this.pagamentoService = pagamentoService;
    }

    public void criarConta(Usuario usuario, ContaFactory contaFactory){
        Conta conta = contaFactory.criarConta(usuario);

        contaRepo.salvar(conta);

        System.out.println("Conta criada e cadastrada com sucesso!");
    }

    public double verSaldo(){
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        return conta.getSaldo();
    }

    public void sacar(double valor){
        // Pegar a conta no repo
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        // Realizar a operação
        conta.debitar(valor);
    }

    public void depositar(double valor){
        // Pegar a conta no repo
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        // Realizar a operação
        conta.creditar(valor);
    }

    public void realizarPagamento(double valor) {
        // Pegar a conta no repo
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        // Realizar a operação
        pagamentoService.realizarPagamento(conta, valor);
    }

    public void bloquearConta(Usuario cliente) {
        // TODO: Validar se a conta já está bloqueada

        Conta conta = contaRepo.acharPorTitular(cliente);

        conta.mudarEstado(new ContaBloqueada(conta));
    }

    public void desbloquearConta(Usuario cliente) {
        // TODO: Validar se a conta já está desbloqueada

        Conta conta = contaRepo.acharPorTitular(cliente);

        conta.mudarEstado(conta.getSaldo() >= 0 ? new ContaPositiva(conta) : new ContaNegativada(conta));
    }
}
