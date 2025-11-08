package DTOs;

import Model.NivelUsuario;
import Model.Usuario;

public class UserDTO {
    private int id;
    private String nome;
    private String senha;
    private String email;
    private String cpf;
    private NivelUsuario cargo;

    public UserDTO(Usuario user){
        this.id = user.getId();
        this.nome = user.getNome();
        this.senha = user.getSenha();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.cargo = user.getCargo();
    }

    @Override
    public String toString(){
        return String.format("ID: %d\n" +
                "Nome: %s\n" +
                "Senha: %s\n" +
                "Email: %s\n" +
                "CPF: %s\n" +
                "Cargo: %s",
                id, nome, senha, email, cpf, cargo.toString());
    }

}
