package Controller;

import DTOs.UserDTOs.UserDTO;
import Model.Services.AdminService;

public class AdminController {
    private AdminService admService;

    public AdminController(AdminService admService){
        this.admService = admService;
    }

    public void cadastrarCliente(String nome, String senha, String email, String cpf, double rendaMensal, String tipoConta) {
        // Delega o trabalho com os dados pro serviço
        admService.cadastrarCliente(nome, senha, email, cpf, rendaMensal, tipoConta);
    }

    public UserDTO getUserInfo(String cpf){
        return admService.getUserInfo(cpf);
    }

    public void bloquearCliente(String cpf){
        admService.bloquearCliente(cpf);
    }

    public void desbloquearCliente(String cpf) {
        admService.desbloquearCliente(cpf);
    }
}
