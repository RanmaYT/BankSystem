package Model;

import SingletonRepositories.IStorable;

public abstract class Usuario implements IStorable {
    private int id;
    private static int idCount = 1;

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

        this.id = idCount;
        idCount++;
    }

    public boolean validarSenha(String senha){
        return false;
    }

    public void atualizarDados(){

    }

    public String getNome(){ return nome; }

    public String getSenha(){ return senha; }

    public String getEmail(){ return email; }

    public String getCpf(){ return cpf; }

    public NivelUsuario getCargo(){ return cargo; }

    public abstract String converterParaStringArmazenavel();

    public int getId() { return id; }
}
