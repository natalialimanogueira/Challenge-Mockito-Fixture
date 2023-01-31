package cap.mockito.fixtures.apresentacao;

import cap.mockito.fixtures.exercises.Executer;
import cap.mockito.fixtures.exercises.domain.Address;
import cap.mockito.fixtures.exercises.domain.DetailedPerson;
import cap.mockito.fixtures.exercises.usecases.DetailedPersonUC;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)//1

public class ExecuterTest {
    //como a classe Execute será testada, faz-se necessário a inclusão dessa anotação (@InjectMocks), uma vez que
    // será criado mocks
    @InjectMocks
    private Executer executer;
    //  @Mock essa anotação é usada para fazer o mock de um objeto/classe. Aqui é criada uma cópia
    //  da estrutura dessa classe com uma implementação ficticia.
    @Mock//2
    private DetailedPersonUC detailedPersonUC;

    @Mock
    private DetailedPerson person;
    @Mock
    private Address address1;


    @Before    // habilitando as anotaçoes @InjectMocks e @Mock
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    //mesmo passando um objeto (detailedPerson) vazio, precisa retornar um endereço
    @Test
    void returnAddressAnyWay() { //rodando
                                // objeto criado vazio
                // quando for chamado
        DetailedPerson detailedPerson = new DetailedPerson();
        Mockito.when(executer.getAddressOf(detailedPerson)).thenReturn(address1);
       // ou fazer assim:
        // doReturn(address1).when(executer).getAddressOf(new DetailedPerson());
                          //estou chamando o método e ele retorna um endereço
        Address retorno = executer.getAddressOf(detailedPerson);
        //check se o objeto não é nulo
        Assert.assertNotNull(retorno);
        System.out.println(retorno);//address1. Precisava ver o que estava chegando
    }
    @Test
    void returnTrueJava() {//rodando
        Mockito.when(detailedPersonUC.searchPersonRelatives(person, "Java")).thenReturn(true);
        Boolean retorno = executer.isRelative(person, "Java");
        Assert.assertTrue(retorno);
    }

    @Test
    void returnTrueJUnit() {//rodando
        Mockito.when(detailedPersonUC.searchPersonRelatives(person, "JUnit")).thenReturn(true);
        Boolean retorno = executer.isRelative(person, "JUnit");
        Assert.assertTrue(retorno);
    }
    @Test// rodando
    void returnfalseAnyway() {// aqui pode entrar Java ou JUnit, como resolver? any((!java)|| (!JUnit))
        Mockito.when(detailedPersonUC.searchPersonRelatives(any(), any())).thenReturn(false);
        Boolean retorno = executer.isRelative(person, "Teste");
        Assert.assertFalse(retorno);
        System.out.println(retorno);//false

    }

    // Mockito.verify verificar varias coisas: numero de vezes que executou,
    //                                         parâmetros recebidos e etc.


    // ok  No método printStrAndInt() do Executer, teste se o método detailedPersonUC.printStringAndInt() foi chamado duas vezes
    //     e cada parâmetro em cada chamada.
    @Test
    void checkIsdetailedPersonUCPrintStringAndIntIsCollTwoTimes() { //rodando
        executer.printStrAndInt();
        verify(detailedPersonUC, times(2)).printStringAndInt(anyString(), anyInt());
    }
//
//    @Test
//    void whatsParametersCallFirt() {// rodando
//        executer.printStrAndInt();
//        verify(detailedPersonUC, times(1)).printStringAndInt(anyString(), anyInt());
//    }
//    @Test
//    void whatsParametersCallSecond() { //rodando
//        executer.printStrAndInt();
//        verify(detailedPersonUC, times(2)).printStringAndInt(anyString(), anyInt());
//    }


    /*
       No método execute() do Executer, faça o cenário de sucesso e de erro.
ok           Se a idade for menor que 18, faça o teste para assegurar que o detailedPersonUC.printStringAndInt() e detailedPersonUC.getPersonAddress() não são chamados.
ok           Teste se o método printStringAndInt() é chamado em cada caso e com os parâmetros corretos. (age <18)
ok           Teste se o método printStringAndInt() é chamado em cada caso e com os parâmetros corretos. (age >18)
             Faça com que a variável address dentro do método execute() nunca fique vazia.

   */

    // Se a idade for menor que 18, faça o teste para assegurar que o detailedPersonUC.printStringAndInt() e detailedPersonUC.getPersonAddress() não são chamados.
//    @Test//rodando
//    void youngThemAge18NotCallPrintStringAndIntAndGetPersonAddress() {
//        DetailedPerson person = new DetailedPerson();//instanciando o objeto da classe mocada
//        person.setAge(17);//setando o valor
//        person.setName("Nat");//seando o valor
//        executer.execute(person);// chamando o métado execute e passando o parametro
//
//        verify(detailedPersonUC, never()).getPersonAddress(any(DetailedPerson.class));
//        verify(detailedPersonUC, never()).printStringAndInt(anyString(), anyInt());
//
//    }
//
//    // Faça com que a variável address dentro do método execute() nunca fique vazia.
//    @Test
//    void varAddresNerverIsEmpty(){//rodando
//
//        DetailedPerson person = new DetailedPerson();
//        executer.execute(person);
//        Mockito.when(executer.getAddressOf(isNotNull())).thenReturn(address);
//        Address returnAddress = executer.getAddressOf(person);
//        Assert.assertNotNull(returnAddress);
//    }
//
//    @Test
//    void youngThemAge18CheckIfPrintStringAndIntIsCall() {//rodando
//        DetailedPerson person = new DetailedPerson();
//        person.setAge(17);
//        person.setName("Nat");
//        executer.execute(person);
//        verify(detailedPersonUC, never()).getPersonAddress(any(DetailedPerson.class));
//        verify(detailedPersonUC, never()).printStringAndInt(anyString(), anyInt());
//        verify(detailedPersonUC, times(0)).printStringAndInt(anyString(), anyInt());
//        // aqui, a quantidade de vezez que deve ser é zero chamadas, pq a idade <18.
//    }
//
//      @Test
//    void olderOrIgualsAge18() { //rodando
//
//          DetailedPerson person = new DetailedPerson("Natalia", 30, Collections.singletonList("teste"), address);
//          Address address = new Address("av", "Gyn", "Go", "Brazil");
//          address.setStreet("Rua feliz");
//          executer.execute(person);// aqui vai chegar a idade e o nome(30 e "Natalia")
//          executer.getAddressOf(person);
//
//          detailedPersonUC.printStringAndInt(person.getName(), person.getAge());
//      }



/*  ok = Linha 53 do código tem uma String sendo declarada que não está sendo utilizada - Remover ela.
       = Desafio de implementar Fixture-Factory para a classe DetailedPerson presente nos testes
    youngThemAge18NotCallPrintStringAndIntAndGetPersonAddress e
    youngThemAge18CheckIfPrintStringAndIntIsCall, já existe exemplos de implementação
    e utilização no mesmo projeto.

       = Por qual motivo ou trecho de código não temos um BeforeEach com o trecho de código
    MockitoAnnotations.initMocks(this) fazendo inicialização dos mocks?


 */
}






