import java.util.*;

public class Hospital {
  private ArrayList<Paciente> pacientes;
  private ArrayList<Funcionario> funcionarios;
  private String nome;

  public Hospital(String nome) {
    this.nome = nome;
    pacientes = new ArrayList<Paciente>();
    funcionarios = new ArrayList<Funcionario>();
  }

  public void addPaciente(Paciente p) {
    pacientes.add(p);
  }

  public void addFuncionario(Funcionario p) {
    funcionarios.add(p);
  }

  public ArrayList<Funcionario> getFuncionarios() {
    return funcionarios;
  }

   public ArrayList<Paciente> getPacientes() {
    return pacientes;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

}