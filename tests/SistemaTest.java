import static org.junit.Assert.*;
import org.junit.*;
import junit.*;
import java.util.ArrayList;

public class SistemaTest {
    // RF1 - Casos 2, 5, 6, 7, 8, 9 e 11 não podem ser testados
    /**
     * Não foi possível testar se tinha como cadastrar um Fisioterapeuta/Enfermeiro com Endereço, mesmo que o endereço, 
     * em tese, seja um atributo obrigatório.
     * 
     * Só foram testados 4 em relação aos 11 planejados.
     * 1 deu certo e 3 deram errado. 
    */

    @Test
    public void cadastroFisioterapeutaTest() {
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraFisioterapeuta("987.234.234-09", "Ana");

        Fisioterapeuta fisioterapeutaCadastrado = sistema.localizaFisioterapeuta("987.234.234-09");
        assertEquals(fisioterapeutaCadastrado.getCpf(), "987.234.234-09");
    }
    @Test 
    public void cadFuncNomeInvalidoTest() {
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraFisioterapeuta("987.234.234-09", "");

        Fisioterapeuta fisioterapeutaCadastrado = sistema.localizaFisioterapeuta("987.234.234-09");
        assertFalse(fisioterapeutaCadastrado.getNome().equals(""));
    }
    @Test
    public void cadFuncCpfInvalidoTest() {
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraEnfermeiro("", "Ana");

        Enfermeiro enfermeiroCadastrado = sistema.localizaEnfermeiro("");

        assertFalse(enfermeiroCadastrado.getCpf().equals(""));
    }
    @Test
    public void cadFuncCpfExistenteTest() {
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();
        // Enfermeiro "existente" no sistema
        sistema.cadastraEnfermeiro("987.234.234-09", "Paula");
        Enfermeiro paula = sistema.localizaEnfermeiro("987.234.234-09");

        sistema.cadastraEnfermeiro("987.234.234-09", "Ana");
        Enfermeiro ana = (Enfermeiro) sistema.listagemDeFuncionarios().get(1);

        boolean condition = paula.getCpf().equals(ana.getCpf());
        assertFalse(condition);
    }
    
    // RF2 - MIGUEL
    /**
     * Todos os requisitos foram testados, o único que teve problema foi em relação ao teste de cadastro de cpf, que não funcionava.
    */
    @Test
    public void cadastroMedicoTest() {
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraMedico("332.113.654-34", "Magna", "533009");

        Medico medicoCadastrado = sistema.localizaMedico("332.113.654-34");
        assertEquals(medicoCadastrado.getCpf(), "332.113.654-34");
    }
    @Test
    public void cadMedCrmInvalidoTest() {
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraMedico("332.113.654-34", "Magna", "");

        Medico medicoCadastrado = sistema.localizaMedico("332.113.654-34");
        assertEquals(medicoCadastrado.getCpf(), "332.113.654-34");
    }
    @Test 
    public void cadMedNomeInvalidoTest() {
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraMedico("332.113.654-34", "", "533009");

        Medico medicoCadastrado = sistema.localizaMedico("987.234.234-09");
        assertFalse(medicoCadastrado.getcrm().equals(""));
    }
    @Test
    public void cadMedCpfInvalidoTest() {
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraMedico("", "Magna", "533009");

        Enfermeiro medicoCadastrado = sistema.localizaMedico("");

        assertFalse(medicoCadastrado.getCpf().equals(""));
    }
    @Test
    public void cadMedCpfExistenteTest() {
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();
        // Enfermeiro "existente" no sistema
        sistema.cadastraMedico("987.234.234-09", "Paula", "123456");
        Medico paula = sistema.localizaEnfermeiro("987.234.234-09");

        sistema.cadastraMedico("987.234.234-09", "Ana", "134561");
        Medico ana = (Medico) sistema.listagemDeFuncionarios().get(1);

        boolean condition = paula.getCpf().equals(ana.getCpf());
        assertFalse(condition);
    }
    
    // RF4 - Casos 3 e 4 não podem ser testados
    /**
     * Só foram testados 2 em relação aos 4 planejados
     * Os dois deram certo.
     * Nenhum deu errado.
    */
    
    @Test
    public void listarFuncionariosTest() {
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraFisioterapeuta("987.234.234-09", "Ana");
        sistema.cadastraEnfermeiro("876.323.411-44", "Paloma");
        sistema.cadastraMedico("332.113.654-34", "Magna", "533009");

        ArrayList<Funcionario> funcionarios = sistema.listagemDeFuncionarios();

        boolean temFuncionario = funcionarios.size() != 0;

        assertTrue(temFuncionario);        
    }
    @Test
    public void naoTemFuncCadastradoTest() {
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        ArrayList<Funcionario> funcionarios = sistema.listagemDeFuncionarios();

        boolean naoTemFuncionario = funcionarios.size() == 0;

        assertTrue(naoTemFuncionario);        
    }
    // RF5 - MIGUEL

    /**
     * Todos os testes funcionaram, os recursos estão sendo listados devidamente
    */
    
    // RF6 - Casos 1, 2, 5, 7, 8 e 10 não podem ser testados
    /**
     * Só foi possível testar 5 dos 11 planejados
     * 1 deu certo e 5 deram errado.
    */
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
        Paciente ana = sistema.localizaPaciente("987.234.234-09");
 
        //Tentativa de cadastrar um paciente com o mesmo CPF
        sistema.cadastraPaciente("987.234.234-09", "Paola Bracho", "Rua aleatória na cidade do méxico");
        Paciente paola = sistema.h.getPacientes().get(1);

        boolean condition = paola.getCpf().equals(ana.getCpf());
        assertFalse(condition);
    }
    @Test
    public void testEnderecoInvalido(){
        Sistema sistema = new Sistema();
        sistema.inicializaSistema();

        sistema.cadastraPaciente("987.234.234-09", "Ana", "");

        assertFalse(sistema.localizaPaciente("987.234.234-09").endereco == "");
    }

    // RF8 - MIGUEL
    /**
     * Assim como o teste de listagem passado, todos os testes funcionaram, os recursos estão sendo listados devidamente
    */
    // RF11 - NÃO IMPLEMENTADO
    
    // RF12 - NÃO IMPLEMENTADO
    
    // RF13 - NÃO IMPLEMENTADO

}