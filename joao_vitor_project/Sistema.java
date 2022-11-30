import java.util.*;

public class Sistema implements FuncionalidadesIF {
  Hospital h;

  /**
   * Esse método deve inicializar o banco de dados e carregar a informação do
   * hospital em um objeto do tipo Hospital.
   */
  public void inicializaSistema() {
   h = new Hospital("Hospital de Campina Grande");
  }

  public void finalizaSistema() {
  }

  // Funcionalidade que cadastra o paciente no sistema
  public void cadastraPaciente(String novoCpf, String novoNome, String novoEndereco) {
    h.addPaciente(new Paciente(novoCpf, novoNome, novoEndereco));
    
  }

  public Paciente localizaPaciente(String cpf) {
   Iterator it = h.getPacientes().iterator();
    while (it.hasNext()) {
      Paciente p = (Paciente) it.next();
      if (p.getCpf().equals(cpf))
        return p;
    }
      
    return null;
  }

  // verifica o valor dos atributos da pessoa que está cadastrada no sistema com
  // esse cpf
  public String recuperaNome(String cpf) {
    Paciente p = localizaPaciente(cpf);
    if (p == null) {
      Enfermeiro e = localizaEnfermeiro(cpf);
      if (e == null) {
        Fisioterapeuta f = localizaFisioterapeuta(cpf);
        if (f != null) return f.getNome();
          
      } else return e.getNome();
      } else return p.getNome();
    return null;
  }

  public String recuperaEndereco(String cpf) {
  Paciente p = localizaPaciente(cpf);
    if (p == null) {
      Enfermeiro e = localizaEnfermeiro(cpf);
      if (e == null) {
        Fisioterapeuta f = localizaFisioterapeuta(cpf);
        if (f != null) return f.getEndereco();
          
      } else return e.getEndereco();
      } else return p.getEndereco();
    return null;
  }

  public void cadastraMedico(String cpf, String nome, String crm) {
   h.addFuncionario(new Medico(cpf, nome, crm));
  }

  public Medico localizaMedico(String crm) {
    Iterator it = h.getFuncionarios().iterator();
    while (it.hasNext()) {
      Funcionario f = (Funcionario) it.next();
      if (f instanceof Medico) {
        Medico m = (Medico) f;
        if (m.getCrm().equals(crm))
            return m;
      }
      
    }
   
    return null;
  }

  public void cadastraEnfermeiro(String novoCpf, String novoNome) {
      h.addFuncionario(new Enfermeiro(novoCpf, novoNome));

  }

  public Enfermeiro localizaEnfermeiro(String cpf) {
   Iterator it = h.getFuncionarios().iterator();
    while (it.hasNext()) {
      Funcionario f = (Funcionario) it.next();
      if (f.getCpf().equals(cpf))
            return (Enfermeiro)f;
    }
    return null;
  }

  public void cadastraFisioterapeuta(String cpf, String nome) {
         h.addFuncionario(new Fisioterapeuta(cpf, nome));

  }

  public Fisioterapeuta localizaFisioterapeuta(String cpf) {
   Iterator it = h.getFuncionarios().iterator();
    while (it.hasNext()) {
      Funcionario f = (Funcionario) it.next();
      if (f.getCpf().equals(cpf))
            return (Fisioterapeuta)f;
    }
    return null;
  }

  public ArrayList<Paciente> listagemDePacientesInternados() {
    return h.getPacientes();
  }

  public ArrayList<Funcionario> listagemDeFuncionarios() {
    return  h.getFuncionarios();
  }

  public int getNumeroDePacientesInternados() {
    return  h.getPacientes().size();
  }

  public int getNumeroDeFuncionarios() {
    return  h.getFuncionarios().size();
  }

  public void atendimento(String cpfFuncionario, String cpfPaciente) {
  }

  // TODO NÃO SERÁ NECESSÁRIO IMPLEMENTAR NESSE PRIMEIRO MOMENTO
  public double atendimentoMedico(String crmMedico, String cpfPaciente) {
    return 0;
  }

}