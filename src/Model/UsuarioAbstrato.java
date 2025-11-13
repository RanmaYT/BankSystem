package Model;

import SingletonRepositories.IStorable;

public abstract class UsuarioAbstrato implements IStorable {
    private String nome;
    private String senha;
    private String email;
    private String cpf;
    private NivelUsuarioEnum tipo;

    public UsuarioAbstrato(String nome, String senha, String email, String cpf, NivelUsuarioEnum tipo){
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
        this.tipo = tipo;
    }

    public void atualizarDados(){

    }

    public String getNome(){ return nome; }

    public String getSenha(){ return senha; }

    public String getEmail(){ return email; }

    public String getCpf(){ return cpf; }

    public NivelUsuarioEnum getTipo(){ return tipo; }
}
