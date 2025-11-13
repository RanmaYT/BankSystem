package Model;

import SingletonRepositories.IStorable;

public abstract class UsuarioAbstrato implements IStorable {
    private int id;
    private static int idCount = 1;

    private String nome;
    private String senha;
    private String email;
    private String cpf;
    private NivelUsuarioEnum cargo;

    public UsuarioAbstrato(String nome, String senha, String email, String cpf, NivelUsuarioEnum cargo){
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
        this.cargo = cargo;

        this.id = idCount;
        idCount++;
    }

    public void atualizarDados(){

    }

    public String getNome(){ return nome; }

    public String getSenha(){ return senha; }

    public String getEmail(){ return email; }

    public String getCpf(){ return cpf; }

    public NivelUsuarioEnum getCargo(){ return cargo; }

    @Override
    public String converterParaStringArmazenavel() {
        String textoArmazenavel = String.format("{nome=%s;senha=%s;email=%s;cpf=%s;cargo=%s}",
                nome, senha, email, cpf, cargo.toString());

        return textoArmazenavel;
    }

    @Override
    public int getId() { return id; }
}
