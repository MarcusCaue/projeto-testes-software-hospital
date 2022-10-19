package classes;
public class Enfermeiro extends Funcionario  {
  public Enfermeiro(String novoCpf, String novoNome) {
    super(novoCpf, novoNome);
  }

  public void atende(Paciente p) {
    System.out.println("\nEnf. " + this.getNome() + " está atendendo o paciente " + p.getNome());
    System.out.println("Medindo a pressão...");
    System.out.println("Medindo saturação...");
    System.out.println("Medindo temperatura...");
  }
}