package Model.Services;

import DTOs.ExtratoBancarioDTO;
import Factory.ContaFactory;
import Mappers.ExtratoMapper;
import Model.Conta;

import Model.ExtratoBancario;
import Model.Usuario;

import SingletonRepositories.ContaRepository;
import SingletonSession.SessionManager;
import State.ContaBloqueada;
import State.ContaNegativada;
import State.ContaPositiva;

public class ContaService {
    private SessionManager sessionManager;
    private ContaRepository contaRepo;
    private ExtratoMapper extratoMapper;

    public ContaService(SessionManager sessionManager, ContaRepository contaRepo, ExtratoMapper extratoMapper) {
        this.sessionManager = sessionManager;
        this.contaRepo = contaRepo;
        this.extratoMapper = extratoMapper;
    }

    public void criarConta(Usuario usuario, ContaFactory contaFactory){
        Conta conta = contaFactory.criarConta(usuario);

        contaRepo.salvar(conta);

        System.out.println("Conta criada e cadastrada com sucesso!");
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

    public ExtratoBancarioDTO pegarExtrato(){
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        return extratoMapper.converterEmDTO(conta.getExtrato());
    }

    public double pegarSaldo(){
        Conta conta = contaRepo.acharPorTitular(sessionManager.getUsuarioLogado());

        return conta.getSaldo();
    }
}
