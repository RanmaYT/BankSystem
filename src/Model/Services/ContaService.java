package Model.Services;

import Factory.ContaFactory;
import Model.Conta;

import Model.OperacaoExtratavel;
import Model.Usuario;

import SingletonRepositories.ContaRepository;
import SingletonSession.SessionManager;
import State.ContaBloqueada;
import State.ContaNegativada;
import State.ContaPositiva;
import Strategy.EspeciePayment;
import Strategy.IPaymentStrategy;
import Strategy.InternetBankingStrategy;

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

    public void realizarPagamento(IPaymentStrategy strategy, String itemPago, double valor) {
        // Setta a estratégia que será usada
        pagamentoService.setPayStrategy(strategy);

        // Pegar a conta no repo
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        // Realizar a operação
        pagamentoService.realizarPagamento(conta, valor);

        // Salva a operação no extrato
    }

    public void salvarNoExtrato(Conta conta, String nomeOperacao, double valorOperacao){
        // Cria uma operação extratável

        // Pegar o extrato

        // Atualizar o
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
