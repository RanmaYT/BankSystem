package SingletonRepositories;

import Model.Cliente;
import Model.Usuario;

import java.util.HashMap;

public class UserRepository extends BaseRepository<Usuario>{
    private static UserRepository instance;
    private static final String fileName = "Usuarios.txt";

    private UserRepository() {}

    public static UserRepository getInstance(){
        if(instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    public Usuario pegarPorEmail(String email){
        String linha = buscarLinhaComItem(email);

        return carregarEntidade(linha);
    }

    @Override
    public Usuario carregarEntidade(String textoArmazenado) {
        textoArmazenado = textoArmazenado.substring(1, textoArmazenado.length() - 1);
        String[] partesPareadas = textoArmazenado.split(";");
        HashMap<String, String> map = new HashMap<>();

        for(String partePareada : partesPareadas) {
            String[] par = partePareada.split("=");
            map.put(par[0], par[1]);
        }

        double rendaMensal = Double.parseDouble(map.get("rendaMensal"));
        Usuario usuario = new Cliente(map.get("nome"), map.get("senha"), map.get("email"), map.get("cpf"), rendaMensal);

        return usuario;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

}
