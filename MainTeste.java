import java.util.*;
import classes.*;

public class MainTeste {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        
        Sistema sistemaHospital = new Sistema();
        sistemaHospital.inicializaSistema();

        System.out.print("Digite o RG (se necessário): ");
        String rg = s.nextLine();

        System.out.println(sistemaHospital.validaRG(rg));



        
    }
}
