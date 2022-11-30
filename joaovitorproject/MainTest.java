import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;
import junit.*;
public class MainTest {
    //RF1

    // RF6 - Casos 2,5,6,7,8 não podem ser testados
    @Test
    public void testCadastroPaciente(){
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraPaciente("987.234.234-09", "Ana", "Rua Vanessa da Mata");

        assertEquals(sistema.localizaPaciente("987.234.234-09").nome, "Ana");
    }
    @Test
    public void testNomeInvalido(){
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraPaciente("987.234.234-09", "", "Rua Vanessa da Mata");

        assertFalse(sistema.localizaPaciente("987.234.234-09").nome == "");
    }
    @Test
    public void testCPFForaFormato(){
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();
        //Se o CPF no formato inválido foi adicionado ao sistema
        sistema.cadastraPaciente("2278459857297845", "Ana", "Rua Vanessa da Mata");
        assertFalse(sistema.localizaPaciente("2278459857297845").cpf == "2278459857297845");
    }
    @Test
    public void testCPFExistente(){
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        // Depende que o sistema já tenha um paciente válido cadastrado
        sistema.cadastraPaciente("987.234.234-09", "Ana", "Rua Vanessa da Mata");

        //Tentativa de cadastrar um paciente com o mesmo CPF
        sistema.cadastraPaciente("987.234.234-09", "Paola Bracho", "Rua aleatória na cidade do méxico");

        assertFalse(sistema.localizaPaciente("987.234.234-09").nome == "Paola Bracho");

    }

    @Test
    public void testEnderecoInvalido(){
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraPaciente("987.234.234-09", "Ana", "");

        assertFalse(sistema.localizaPaciente("987.234.234-09").endereco == "");
    }
    //RF4

    //RF13

    //RF11 - NÃO IMPLEMENTADO
  
    //RF12 - NÃO IMPLEMENTADO

}
