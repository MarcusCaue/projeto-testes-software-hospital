import java.util.*;
import classes.*;

public class MainTestes {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);

    Sistema sistemaHospital = new Sistema();
    sistemaHospital.inicializaSistema();

    System.out.println("CPF: ");
    String cpf = s.nextLine();
    System.out.println("RG: ");
    String rg = s.nextLine();
    System.out.println("Nome: ");
    String nome = s.nextLine();
    System.out.println("Endereço: ");
    String endereco = s.nextLine();
    System.out.println("Procedimento: ");
    String procedimento = s.nextLine();
    System.out.println("Esta na Uti? ");
    boolean uti = s.nextBoolean();
    System.out.println("Peso: "); s.nextLine();
    double peso = s.nextDouble();
    System.out.println("Altura: ");
    int alt = s.nextInt();

    if (sistemaHospital.verificaRG(rg)) {
      System.out.println("Já tem pessoa com esse RG, digite outro.");
    } else {
      sistemaHospital.cadastraPaciente(cpf, nome, endereco);
      Paciente p = sistemaHospital.localizaPaciente(cpf);
      p.setAltura(alt); p.setEstaNaUti(uti); p.setPeso(peso); p.setRg(rg); p.setProcedimento(procedimento);
    }
    






    // System.out.println("Função: ");
    // String funcao = s.nextLine();

    // System.out.println("CPF: ");
    // String cpf = s.nextLine();
    
    // System.out.println("RG: ");
    // String rg = s.nextLine();

    // System.out.println("Nome: ");
    // String nome = s.nextLine();

    // System.out.println("Endereço: ");
    // String endereco = s.nextLine();

    // if (sistemaHospital.verificaRG(rg)) {
    //   System.out.println("Já tem uma pessoa cadastrada com esse RG, digite outro.");
    // } else {
    //   if (funcao.equals("Fisioterapeuta")) {
    //     sistemaHospital.cadastraFisioterapeuta(cpf, nome);
    //     Fisioterapeuta f1 = sistemaHospital.localizaFisioterapeuta(cpf);
    //     f1.setRg(rg); f1.setEndereco(endereco);
    //   } else {
    //     sistemaHospital.cadastraEnfermeiro(cpf, nome);
    //     Enfermeiro e1 = sistemaHospital.localizaEnfermeiro(cpf);
    //     e1.setRg(rg); e1.setEndereco(endereco);
    //   }

    //   System.out.println("Médico cadastrado");
    // }

    // sistemaHospital.cadastraPaciente("433.434.544-00", "Weidson Costa", "Rua Matematica");
    // Paciente p1 = sistemaHospital.localizaPaciente("918.321.533-12");
    // System.out.println(p1);

    // sistemaHospital.atendimento("123.456.239-10", "099.322.553-43");

    // sistemaHospital.atendimentoMedico("554233", "099.322.553-43");

    sistemaHospital.finalizaSistema();



  }

}
