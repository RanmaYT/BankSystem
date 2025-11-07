package Model;

import SingletonRepositories.IStorable;

public abstract class Usuario implements IStorable {
    private int id;
    private String nome;
    private String senha;
    private String email;
    private String cpf;
    private NivelUsuario cargo;

    public Usuario(String nome, String senha, String email, String cpf, NivelUsuario cargo){
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public boolean validarSenha(String senha){
        return false;
    }

    public void atualizarDados(){

    }

    public abstract String converterParaStringArmazenavel();

    public int pegarId() { return id; }
}
