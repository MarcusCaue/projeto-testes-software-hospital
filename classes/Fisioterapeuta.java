package classes;
public class Fisioterapeuta extends Funcionario   {
  public Fisioterapeuta(String novoCpf, String novoNome) {
    super(novoCpf, novoNome);
  }

   public void atende(Paciente p) {
    System.out.println("\nFisio. " +getNome() + " está atendendo paciente " + p.getNome());
    System.out.println("Examinando a coluna...");
    System.out.println("Examinando a respiração...");
  }
}