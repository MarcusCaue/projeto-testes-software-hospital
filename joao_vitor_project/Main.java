import java.util.*;

class Main {
  public static void main(String[] args) {
    System.out.println(
        "Use esse programa para realizar testes do projeto de outra equipe. Todos os prints abaixo devem conter a palavra *True*. Se houver algo diferente disso, o programa não está correto. ");
    Sistema sistema = new Sistema();
    sistema.inicializaSistema();

    // Teste de cadastro de paciente
    sistema.cadastraPaciente("1234", "Maria Chiquinha", "Rua do Sol, nº 1");
    System.out.println(sistema.getNumeroDePacientesInternados() == 1);
    sistema.cadastraPaciente("1234", "Maria Chiquinha", "Rua do Sol, nº 1");
    System.out.println(sistema.getNumeroDePacientesInternados() == 1);
    
    Paciente p1 = sistema.localizaPaciente("1234");
    Paciente p2 = new Paciente("1234", "Maria Chiquinha", "Rua do Sol, nº 1");
    System.out.println(p1.equals(p2));
    ArrayList pacientes = new ArrayList();
    ArrayList funcionarios = new ArrayList();

    pacientes.add(p2);

    sistema.cadastraPaciente("5678", "Homem de Ferro", "Rua do Ferro, nº 2");
    System.out.println(sistema.getNumeroDePacientesInternados() == 2);
    Paciente p3 = sistema.localizaPaciente("5678");
    Paciente p4 = new Paciente("5678", "Homem de Ferro", "Rua do Ferro, nº 2");
    pacientes.add(p4);

    System.out.println(p3.equals(p4));
    System.out.println(!p1.equals(p3));
    String n1 = sistema.recuperaNome("1234");
    System.out.println(n1.equals("Maria Chiquinha"));
    String e1 = sistema.recuperaEndereco("5678");

    System.out.println(e1.equals("Rua do Ferro, nº 2"));

    sistema.cadastraPaciente("5678", "Incrível Hulk", "Rua Verde, nº 10"); // nao pode cadastrar dois pacientes com o mesmo cpf
    System.out.println(sistema.getNumeroDePacientesInternados() == 2);
    String n2 = sistema.recuperaNome("5678");
    System.out.println(n2.equals("Homem de Ferro"));

    sistema.cadastraMedico("98765", "Marilia Mendonça", "001PB");
    Medico m1 = sistema.localizaMedico("001PB");
    Medico m2 = new Medico("98765", "Marilia Mendonça", "001PB");
    funcionarios.add(m2);
    System.out.println(m1.equals(m2));

    sistema.cadastraMedico("12345", "Anitta", "001PB"); // nao pode cadastrar medico com crm igual
    m1 = sistema.localizaMedico("001PB");
    System.out.println(m1.equals(m2));
    System.out.println(sistema.getNumeroDeFuncionarios() == 1);

    sistema.cadastraEnfermeiro("1357", "Fátima Bernardes");
    Enfermeiro enf1 = sistema.localizaEnfermeiro("1357");
    Enfermeiro enf2 = new Enfermeiro("1357", "Fátima Bernardes");
    funcionarios.add(enf2);
    System.out.println(enf1.equals(enf2));

    sistema.cadastraEnfermeiro("1357", "Ana Maria Braga"); // nao pode cadastrar enfermeiro com cpf igual
    enf2 = sistema.localizaEnfermeiro("1357");
    System.out.println(enf1.equals(enf2));

    
    System.out.println(sistema.listagemDePacientesInternados().equals(pacientes));
    System.out.println(sistema.listagemDeFuncionarios().equals(funcionarios));

    System.out.println(sistema.getNumeroDeFuncionarios() == 2);
    System.out.println(sistema.getNumeroDePacientesInternados() == 2);
    
  }
}