import java.util.*;
import classes.*;

class Main {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    // System.out.print("Digite seu nome: ");
    // String nome = s.nextLine();

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
      System.out.println("1 - Alterar Nomes\n2 - Alterar Endereços\n3 - Atualizar Altura do Paciente\n4 - Atualizar Peso do Paciente\n5 - Listar Número de Pacientes internados \n6 - Listar Número de Funcionários\n7- Cadastrar Funcionário\n8 - Localizar Funcionários\n9 - Confirmar Nome\n10 - Soltar uma Alta da UTI\n11 - Fechar o Programa");
      
      // Validando a escolha do usuário
      int acao;
      do {
        System.out.print("Digite a sua escolha: ");
        acao = s.nextInt();
      } while (acao < 1 || acao > 7);
      
      // Alterar Nomes
      if (acao == 1) {
        System.out.println(enfeite);
        System.out.println("MODIFICANDO NOME DE UMA PESSOA");
        System.out.println(enfeite);

        System.out.print("Informe o CPF da pessoa que se deseja alterar o nome: ");
        String cpf = s.nextLine();

        // Validando o CPF e o NOME
        if (sistemaHospital.validaCPF(cpf)) {
          System.out.print("Informe o novo nome: ");
          String novoNome = s.nextLine();

          if (sistemaHospital.validaNome(novoNome)) {
            sistemaHospital.alteraNome(cpf, novoNome);        
          } else {
            System.out.println("Nome inválido. O nome não será modificado.");
          }
        } else {
          System.out.println("CPF inválido. O nome não será modificado.");
        }
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
      // Cadastrar Funcionário
      else if (acao == 7) {
        System.out.println("Você tem as seguintes opções de funcionário para cadastrar:\n1 - Médico\n2 - Enfermeiro\n3- Fisioterapeuta");
        System.out.println("\nDigite o número da opção que você deseja: ");
        int resposta = s.nextInt(); s.nextLine();

        if(resposta == 1) {
          // Recebe os dados//
          System.out.print("Digite o CPF do médico que você quer cadastrar: (XXX.XXX.XXX-XX)");
          String ocpf = s.nextLine();
          System.out.print("Digite o Nome do médico que você quer cadastrar: ");
          String onome = s.nextLine();
          System.out.print("Digite o CRM do médico que você quer cadastrar: (XXXXXX)");
          String ocrm = s.nextLine();

          // Valida os dados e usa o comando para enviá-los ao banco //
          if(sistemaHospital.validaCPF(ocpf) && sistemaHospital.validaNome(onome) && sistemaHospital.validaCRM(ocrm)){
            sistemaHospital.cadastraMedico(ocpf, onome, ocrm);   
            System.out.println("Cadastrado!");
          }       
          else{
            System.out.println("Você digitou algum dos seus dados no formato inválido... tente novamente!");
          }
        }
        // Recebe os dados//
        else if (resposta == 2){
          System.out.print("Digite o CPF do enfermeiro que você quer cadastrar: (XXX.XXX.XXX-XX)");
          String ocpf = s.nextLine();
          System.out.print("Digite o Nome do enfermeiro que você quer cadastrar: ");
          String onome = s.nextLine();
          
          // Valida os dados e usa o comando para enviá-los ao banco//
          if(sistemaHospital.validaCPF(ocpf) && sistemaHospital.validaNome(onome)){
            sistemaHospital.cadastraEnfermeiro(ocpf, onome);   
            System.out.println("Cadastrado!");
          }       
          else{
            System.out.println("Você digitou algum dos seus dados no formato inválido... tente novamente!");
          }
        }
        // Recebe os dados//
        else if (resposta == 3) {
          System.out.print("Digite o CPF do  fisioterapeuta que você quer cadastrar: (XXX.XXX.XXX-XX)");
          String ocpf = s.nextLine();
          System.out.print("Digite o Nome do fisioterapeuta que você quer cadastrar: ");
          String onome = s.nextLine();

          // Valida os dados e usa o comando para enviá-los ao banco//
          if(sistemaHospital.validaCPF(ocpf) && sistemaHospital.validaNome(onome)){
            sistemaHospital.cadastraFisioterapeuta(ocpf, onome);   
            System.out.println("Cadastrado!");
          }       
          else{
            System.out.println("Você digitou algum dos seus dados no formato inválido... tente novamente!");
          }
        }
        else {
          System.out.println("Opção inválida... tente novamente!");
        }
      }
      // Localizar Funcionário
      else if (acao == 8) {
        System.out.print("Indique o tipo de funcionário que você deseja localizar:\n 1 - Médico\n 2 - Enfermeiro\n 3 - Fisioterapeuta");
        int resposta = s.nextInt(); s.nextLine();

        // Localizando Médico
        if(resposta == 1){
          System.out.print("Digite o CRM do Médico: ");
          String crm = s.nextLine();

          Medico medicoAtual = sistemaHospital.localizaMedico(crm);
          if (medicoAtual == null) {
            System.out.println("Médico não encontrado");
          } else {
            System.out.println("Dados do médico:\nNome: " + medicoAtual.getNome() + "\nCPF: " + medicoAtual.getCpf());
          }
        }
        // Localizando Enfermeiro
        else if(resposta == 2){
          System.out.print("Digite o CPF do Enfermeiro: ");
          String cpf = s.nextLine();

          Enfermeiro enfermeiroAtual = sistemaHospital.localizaEnfermeiro(cpf);
          if (enfermeiroAtual == null) {
            System.out.println("Enfermeiro não encontrado");
          } else {
            System.out.println("Dados do enfermeiro:\nNome: " + enfermeiroAtual.getNome() + "\nCPF: " + enfermeiroAtual.getCpf() );
          }
        }
        // Localiza Fisioterapeuta
        else if(resposta == 3){
          System.out.print("Digite o CPF do Fisioterapeuta: ");
          String cpf = s.nextLine();

          Fisioterapeuta fisioterapeutaAtual = sistemaHospital.localizaFisioterapeuta(cpf);
          if (fisioterapeutaAtual == null) {
            System.out.println("Fisioterapeuta não encontrado");
          } else {
            System.out.println("Dados do fisioterapeuta:\nNome: " + fisioterapeutaAtual.getNome() + "\nCPF: " + fisioterapeutaAtual.getCpf() );
          }
        }
        else{
          System.out.println("Opção inválida... Tente novamente!");
        }
      }
      // Confimar Nome
      else if (acao == 9) {
        System.out.print("CPF da pessoa: ");
        String cpf = s.nextLine();
        System.out.println("Nome da pessoa: ");
        String nome = s.nextLine();

        if (sistemaHospital.validaCPF(cpf) && sistemaHospital.validaNome(nome)) {
          String resposta = sistemaHospital.confirmaNome(cpf, nome);
          System.out.println(resposta);
        } else {
          System.out.println("Você digitou pelo menos um dos dados de forma inválida. Tente novamente");
        }
      }
      // Alta da UTI
      else if (acao == 10) {
        System.out.print("Digite o CPF do Paciente: ");
        String cpf = s.nextLine();
        boolean cpfValido = sistemaHospital.validaCPF(cpf);

        if (cpfValido) {
          Paciente p = sistemaHospital.localizaPaciente(cpf);
          if (p == null) {
            System.out.println("Paciente não cadastrado no sistema");
          } else {
            sistemaHospital.altaDaUti(p);
          }
        } else {
          System.out.println("CPF inválido. Tente novamente");
        }


        
      }
      // Cadastrar Fisioterapeuta
      else if (acao == 11) {}
      // Localizar Fisioterapeuta
      else if (acao == 12) {}
      // Confirmar Nome
      else if (acao == 13) {}
      // Soltar Alta da UTI
      else if (acao == 14) {}

      else if (acao == 15) {}

      else if (acao == 16) {}

      else if (acao == 17) {}

      else if (acao == 18) {}

      else if (acao == 19) {}

      else if (acao == 20) {}

      else if (acao == 21) {}

      else if (acao == 22) {}
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