package Controller;

import DTOs.UserDTO;
import Factory.ContaFactory.ContaCorrenteFactory;
import Model.Services.AdminService;

public class AdminController {
    private AdminService admService;

    public AdminController(AdminService admService){
        this.admService = admService;
    }

    public void cadastrarCliente(String nome, String senha, String email, String cpf, double rendaMensal) {
        // Delega o trabalho com os dados pro serviço
        admService.cadastrarCliente(nome, senha, email, cpf, rendaMensal, new ContaCorrenteFactory());
    }

    public UserDTO getUserInfo(String clienteEmail){
        return admService.getUserInfo(clienteEmail);
    }

    public void bloquearCliente(String clienteEmail){
        admService.bloquearCliente(clienteEmail);
        System.out.println("Conta bloqueada!");
    }

    public void desbloquearCliente(String clienteEmail) {
        admService.desbloquearCliente(clienteEmail);
        System.out.println("Conta desbloqueada!");
    }
}
