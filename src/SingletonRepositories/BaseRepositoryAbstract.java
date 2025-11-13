package SingletonRepositories;

import com.google.gson.Gson;

import java.io.*;

public abstract class BaseRepositoryAbstract<T extends IStorable>{
    // Gambiarra: Mudar para injeção de dependência?
    protected Gson gson = new Gson();

    public void salvar(T entidade) {
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(getFileName(), true))) {
            String entidadeJson = gson.toJson(entidade);

            escritor.write(entidadeJson);
            escritor.newLine();
        }
        catch(IOException e) {
            System.out.println("Falha ao salvar no arquivo: " + getFileName());
        }
    }

    public String buscarLinhaComItem(String identificadorLinha) {
        // Lê o arquivo buscando a linha por um identificador
        try(BufferedReader leitor = new BufferedReader(new FileReader(getFileName()))) {
            String linhaAtual;
            while((linhaAtual = leitor.readLine()) != null){
                if(linhaAtual.contains(identificadorLinha)) {
                    return linhaAtual;
                }
            }
        }
        catch(IOException e) {
            System.out.println("Falha ao ler o arquivo");
            return null;
        }

        // Caso não ache nada no arquivo
        return null;
    }

    public void atualizarLinha(String identificadorLinhaAntiga, T entidade) {
        File arquivoOriginal = new File(getFileName());
        File arquivoTemporario = new File("tempFile.txt");
        String linhaAntiga = buscarLinhaComItem(identificadorLinhaAntiga);

        try(BufferedReader leitor = new BufferedReader(new FileReader(getFileName()))) {
            String linhaLida;
            BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemporario, true));

            // Verifica cada linha, caso seja uma linha diferente da do item que está sendo atualizado, só reescreve ela
            // no arquivo temporário
            while((linhaLida = leitor.readLine()) != null) {
                if(!linhaLida.equals(linhaAntiga)) {
                    escritor.write(linhaLida);
                }
                else {
                    escritor.write(gson.toJson(entidade));
                }

                escritor.newLine();
            }
            escritor.close();
            leitor.close();

            // Apaga o arquivo antigo
            arquivoOriginal.delete();
            arquivoTemporario.renameTo(arquivoOriginal);
        }
        catch(IOException e) {
            System.out.println("Erro ao atualizar arquivo");
        }
    }

    public void deletar(T entidade) {
        String linhaParaDeletar = gson.toJson(entidade);

        File arquivoOriginal = new File(getFileName());
        File arquivoTemporario = new File("tempFile.txt");

        try(BufferedReader leitor = new BufferedReader(new FileReader(getFileName()))) {
            String linhaLida;
            BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemporario, true));

            // Verifica cada linha, caso seja uma linha diferente da que será deletada, escreve a linha
            while((linhaLida = leitor.readLine()) != null) {
                if(!linhaLida.equals(linhaParaDeletar)) {
                    escritor.write(linhaLida);
                }
                escritor.newLine();
            }
            escritor.close();
            leitor.close();

            // Apaga o arquivo antigo
            arquivoOriginal.delete();
            arquivoTemporario.renameTo(arquivoOriginal);

            System.out.println("Linha apagada com sucesso:" + getFileName());
        }
        catch(IOException e) {
            System.out.println("Erro ao atualizar arquivo");
        }
    }

    public abstract T carregarEntidade(String textoArmazenado);
    public abstract String getFileName();
}
