package SingletonRepositories;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class BaseRepository<T extends IStorable>{
    public void salvar(T entidade) {
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(getFileName(), true))) {
            String textoArmazenavel = entidade.converterParaStringArmazenavel();

            escritor.write(textoArmazenavel);
            escritor.newLine();
        }
        catch(IOException e) {
            System.out.println("Falha ao salvar no arquivo: " + getFileName());
        }
    }

    public String buscarLinhaComItem(String itemBuscado) {
        try(BufferedReader leitor = new BufferedReader(new FileReader(getFileName()))) {
            String linhaAtual;
            while((linhaAtual = leitor.readLine()) != null){
                if(linhaAtual.contains(itemBuscado)) {
                    return linhaAtual;
                }
            }
        }
        catch(IOException e) {
            System.out.println("Falha ao ler o arquivo");
            return null;
        }

        return null;
    }

    public void deletar(T entidade) {

    }

    public abstract T carregarEntidade(String textoArmazenado);
    public abstract String getFileName();
}
