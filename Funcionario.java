public abstract class Funcionario extends Pessoa{

  public Funcionario(String novoCpf, String novoNome) {
    super(novoCpf, novoNome);
  }
  public abstract void atende(Paciente p);
}