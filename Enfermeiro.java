public class Enfermeiro extends Funcionario  {
  public Enfermeiro(String n, String c) {
    super(n,c);
  }

  public void atende(Paciente p) {
    System.out.println("\nEnf. " +getNome() + " está atendendo o paciente " + p.getNome());
    System.out.println("Medindo a pressão..");
    System.out.println("Medindo saturação..");
    System.out.println("Medindo temperatura..");
  }
}