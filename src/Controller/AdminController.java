package Controller;

import DTOs.UserDTO;
import Factory.ContaCorrenteFactory;
import Model.Services.AdminService;
import Util.InputUtil;
import View.Menu;

public class AdminController {
    private AdminService admService;

    public AdminController(AdminService admService){
        this.admService = admService;
    }

    public void cadastrarCliente(String nome, String senha, String email, String cpf, double rendaMensal) {
        // Delega o trabalho com os dados pro serviço
        admService.cadastrarCliente(nome, senha, email, cpf, rendaMensal, new ContaCorrenteFactory());
    }

    public UserDTO getUserInfo(int clienteId){
        return admService.getUserInfo(clienteId);
    }

    public void bloquearCliente(int clienteId){
        admService.bloquearCliente(clienteId);
        System.out.println("Conta bloqueada!");
    }

    public void desbloquearCliente(int clienteId) {
        admService.desbloquearCliente(clienteId);
        System.out.println("Conta desbloqueada!");
    }
}
