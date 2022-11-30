public class Fisioterapeuta extends Funcionario   {
public Fisioterapeuta(String cpf, String nome) {
    super(cpf,nome);
  }

   public void atende(Paciente p) {
    System.out.println("\nDr. " +getNome() + " está atendendo paciente " + p.getNome());
    System.out.println("Examinando a coluna..");
    System.out.println("Examinando a respiração..");

  }
}