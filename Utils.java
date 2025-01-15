import java.util.Scanner;

public abstract class Utils {
  public static void pausa(long timeMilis) throws InterruptedException {
    try {
      Thread.sleep(timeMilis);
    } catch (Exception e) {
      System.out.println("Ocorreu um erro do tipo: " + e);
    }
  }

  public static void menu() {
    String line = "\n=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n";

    String titleElements[] = { line, "Bem vindo ao nosso sistema hospitalar.", "Experimente nossas funcionalidades!", line };
    System.out.println(String.join("\n", titleElements));

    System.out.println("O que deseja fazer hoje?\n");

    String options[] = {
      "1  - Alterar Nomes", "2  - Alterar Endereços", "3  - Atualizar Altura do Paciente", "4  - Atualizar Peso do Paciente", 
      "5  - Listar Número de Pacientes internados", "6  - Listar Número de Funcionários", "7  - Cadastrar Funcionário", 
      "8  - Localizar Funcionários", "9  - Confirmar Nome", "10 - Soltar uma Alta da UTI", "11 - Cadastrar Paciente",
      "12 - Localizar Paciente", "13 - Listar Pacientes Internados", "14 - Listar Funcionários", "15 - Atendimento",
      "16 - Confirmar Endereço", "17 - Internar Paciente", "18 - Fechar o Programa"
    };

    System.out.println(String.join("\n", options));

  }

  public static void title(String title) {
    String line = "\n=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=\n";
    int quantSpaces = (43 - title.length()) / 2;

    System.out.println(line);
    System.out.println(" ".repeat(quantSpaces) + title + " ".repeat(quantSpaces));
    System.out.println(line);
  }

  public static int leValida(Scanner s, int limMin, int limMax) {
    int val;

    do {
      System.out.print("\nDigite a sua escolha: ");
      val = s.nextInt();
    } while (val < limMin || val > limMax);

    return val;
  }
}
