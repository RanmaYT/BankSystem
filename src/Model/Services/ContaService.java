package Model.Services;

import Model.Conta;
import SingletonRepositories.ContaRepository;
import SingletonSession.SessionManager;

public class ContaService {
    private SessionManager sessionManager;
    private ContaRepository contaRepo;
    private PagamentoService pagamentoService;

    public ContaService(SessionManager sessionManager, ContaRepository contaRepo, PagamentoService pagamentoService) {
        this.sessionManager = sessionManager;
        this.contaRepo = contaRepo;
        this.pagamentoService = pagamentoService;
    }

    public void sacar(double valor){

    }

    public void depositar(double valor){

    }

    public void realizarPagamento(double valor) {
        // Conta conta = VAI PEGAR A CONTA
        // pagamentoService.realizarPagamento(conta, valor); VAI DELEGAR O TRABLHO PRO SISTEMA DE PAGAMENTO
    }
}
