package SingletonRepositories;

import Model.ExtratoBancario;

public class ExtratoRepository extends BaseRepositoryAbstract<ExtratoBancario> {
    private static ExtratoRepository instance;
    private static final String fileName = "Extratos.txt";

    private ExtratoRepository() {};

    public static ExtratoRepository getInstance() {
        if(instance == null) {
            instance = new ExtratoRepository();
        }

        return instance;
    }

    public ExtratoBancario pegarPorTitular(String cpf) {
        String linhaSalva = buscarLinhaComItem(cpf);

        return carregarEntidade(linhaSalva);
    }

    @Override
    public ExtratoBancario carregarEntidade(String textoArmazenado) {
        return gson.fromJson(textoArmazenado, ExtratoBancario.class);
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
