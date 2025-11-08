package Controller;

import DTOs.UserDTO;
import Factory.ContaCorrenteFactory;
import Model.Services.AdminService;
import Util.InputUtil;
import View.Menu;

public class AdminController {
    private AdminService admService;
    private InputUtil inputUtil;

    public AdminController(AdminService admService, InputUtil inputUtil){
        this.admService = admService;
        this.inputUtil = inputUtil;
    }

    public void cadastrarCliente() {
        System.out.println("=== Cadastro Cliente ===");

        // Pega os campos necessários
        String nome = inputUtil.getAlphaInput("Nome: ");
        String senha = inputUtil.getStringInput("Senha: ");
        String email = inputUtil.getStringInput("Email: ");
        String cpf = inputUtil.getStringInput("CPF: ");
        double rendaMensal = inputUtil.getDoubleInput("Renda Mensal: ");

        // Delega o trabalho com os dados pro serviço
        admService.cadastrarCliente(nome, senha, email, cpf, rendaMensal, new ContaCorrenteFactory());
    }

    public UserDTO getUserInfo(int clienteId){
        return admService.getUserInfo(clienteId);
    }

    public void bloquearCliente(int clienteId){
        admService.bloquearCliente(clienteId);
    }

    public void desbloquearCliente(int clienteId) {
        admService.desbloquearCliente(clienteId);
    }
}
