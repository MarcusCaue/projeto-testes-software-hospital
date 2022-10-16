import java.util.*;
import classes.*;

class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    // Inicializando o Sistema do Hospital
    Sistema sistemaHospital = new Sistema();
    sistemaHospital.inicializaSistema(); 

    String enfeite = "\n=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n";

    while (true) {
      // Menu
      System.out.println(enfeite);
      System.out.println("Bem vindo ao nosso sistema hospitalar. \nExperimente nossas funcionalidades!");
      System.out.println(enfeite);
      System.out.println("O que deseja fazer hoje?\n");
      System.out.println("1 - Alterar nomes\n2 - Alterar endereços\n3 - Atualizar altura do paciente\n4 - Atualizar peso do paciente\n5 - Listar número de pacientes internados \n6 - Listar número de funcionários\n7 - Fechar o Programa");
      
      // Validando a escolha do usuário
      int acao;
      do {
        System.out.print("Digite a sua escolha: ");
        acao = s.nextInt();
      } while (acao < 1 || acao > 7);
      
      // Alterar Nomes
      if (acao == 1) {
        System.out.print("Informe o CPF da pessoa que se deseja alterar o nome: ");
        String cpf = s.nextLine();
        System.out.print("Informe o novo nome: ");
        String novoNome = s.nextLine();
        
        sistemaHospital.alteraNome(cpf, novoNome);        
      } 
      // Alterar Endereços
      else if (acao == 2) {
        System.out.print("Informe o CPF da pessoa que se deseja alterar o endereço: ");
        String cpf = s.nextLine();
        System.out.print("Informe o novo endereço: ");
        String novoEndereco = s.nextLine();
        
        sistemaHospital.alteraEndereco(cpf, novoEndereco);
      }
      // Atualizar altura do paciente
      else if (acao == 3) {
        System.out.print("Informe o CPF do paciente que se deseja alterar a altura: ");
        String cpf = s.nextLine();
        System.out.print("Informe a altura desse paciente: ");
        int altura = s.nextInt();
        
        // Obtendo um objeto de paciente dado o seu CPF
        Paciente p = null;
        ArrayList<Paciente> pacientes = sistemaHospital.hospital.getPacientes();

        for (Paciente paciente: pacientes){
          String cpfPacienteNaLista = paciente.getCpf();

          if (cpfPacienteNaLista.equals(cpf)){
            p = paciente;
            break;
          }       
        }

        sistemaHospital.alteraAltura(p, altura);  
      }
      // Atualizar peso do paciente
      else if (acao == 4) {
        System.out.print("Informe o CPF do paciente que se deseja alterar o peso: ");
        String cpf = s.nextLine();
        System.out.print("Informe o peso desse paciente: ");
        double peso = s.nextDouble();
          
        // Obtendo um objeto de paciente dado o seu CPF
        Paciente p = null;
        ArrayList<Paciente> pacientes = sistemaHospital.hospital.getPacientes();

        for (Paciente paciente: pacientes){
          String cpfPacienteNaLista = paciente.getCpf();

          if (cpfPacienteNaLista.equals(cpf)){
            p = paciente;
          }       
        }

        sistemaHospital.altaPeso(p, peso);  
      }    
      // Listar número de pacientes internados
      else if (acao == 5) {
        int numPacientesInternados = sistemaHospital.getNumeroDePacientesInternados();
        System.out.println("No momento há " + numPacientesInternados + " pacientes internados.");
      }
      // Listar número de funcionários
      else if (acao == 6) {
        int numFuncionarios = sistemaHospital.getNumeroDeFuncionarios();
        System.out.println("No momento há " + numFuncionarios + " Funcionários.");
      } 
      // Encerrando o Programa
      else {
        System.out.println("Sistema Encerrado!");
        break;
      }
    }

    // Finalizando o Sistema do Hospital
    sistemaHospital.finalizaSistema();
    System.out.println("\nSistema finalizado com sucesso! Obrigado e volte sempre!");
    System.out.println(enfeite);
  }
}