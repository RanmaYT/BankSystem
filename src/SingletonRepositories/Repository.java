package SingletonRepositories;

public interface Repository<T extends IStorable>{
    public T acharPorId(int id);
    public void salvar(T entidade);
    public void deletar(T entidade);
}
