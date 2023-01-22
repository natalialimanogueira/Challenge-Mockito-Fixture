package cap.mockito.fixtures.apresentacao;

import cap.mockito.fixtures.exercises.Executer;
import cap.mockito.fixtures.exercises.domain.Address;
import cap.mockito.fixtures.exercises.domain.DetailedPerson;
import cap.mockito.fixtures.exercises.usecases.DetailedPersonUC;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/*
    Crie testes usando @Mock e @Spy para a classe Executer,
    utilize o Fixture-Factory para facilitar a criação de objetos para testes. (utilize apenas o fixture para a criação de instâncias, mas se for um objeto vazio não é necessário)
ok  No método getAddressOf() do Executer, faça ele retornar um endereço mesmo passando um objeto vazio para ele (objeto vazio = new DetailedPerson())
ok  No método isRelative() do Executer, faça os 2 cenários abaixo:
ok       Se a string passada for "Java" ou "JUnit", retorne true;
         Se a string passada for qualquer outra string, retorne false;                      ?????

ok  No método printStrAndInt() do Executer, teste se o método detailedPersonUC.printStringAndInt() foi chamado duas vezes
    e cada parâmetro em cada chamada.                                                       ????? verificar se está certo

    No método execute() do Executer, faça o cenário de sucesso e de erro.
ok      Se a idade for menor que 18, faça o teste para assegurar que o detailedPersonUC.printStringAndInt() e detailedPersonUC.getPersonAddress() não são chamados.
ok      Teste se o método printStringAndInt() é chamado em cada caso e com os parâmetros corretos. (age <18)
ok      Teste se o método printStringAndInt() é chamado em cada caso e com os parâmetros corretos. (age >18)
        Faça com que a variável address dentro do método execute() nunca fique vazia.   ?????? verificar se está certo

 */

@ExtendWith(MockitoExtension.class)
public class ExecuterTest {
    @InjectMocks
    private Executer service;
    @Mock
    private DetailedPersonUC detailedPersonUC;
    @Mock
    private DetailedPerson person;
    @Mock
    private Address address;

    private List<String> string = new ArrayList<>();
    String firt = new String("testando");

/*    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        //
        this.service = new Executer(detailedPersonUC);
        this.address = new Address("av", "Gyn", "Go", "Brazil");
        //                                                   street, city, state,cautry
        //                nome, age, List<String> relatives, address
        this.person = new DetailedPerson("Natalia", 30, Collections.singletonList("Single"), address);
    }
 */

    /*
       No método getAddressOf() do Executer, faça ele retornar um endereço mesmo passando um objeto
       vazio para ele (objeto vazio = new DetailedPerson())
    */
    @Test
    void returnAddressAnyWay() {
        DetailedPerson person = new DetailedPerson();
        Mockito.when(service.getAddressOf(person)).thenReturn(address);
        Address retorno = service.getAddressOf(person);
        Assert.assertNotNull(retorno);
    }

    /*    No método isRelative() do Executer, faça os 2 cenários abaixo:
          Se a string passada for "Java" ou "JUnit", retorne true;
          Se a string passada for qualquer outra string, retorne false;
     */
    @Test
    void returnTrueJava() {
        Mockito.when(detailedPersonUC.searchPersonRelatives(person, "Java")).thenReturn(true);
        Boolean retorno = service.isRelative(person, "Java");
        Assert.assertTrue(retorno);
    }

    @Test
    void returnTrueJUnit() {
        Mockito.when(detailedPersonUC.searchPersonRelatives(person, "JUnit")).thenReturn(true);
        Boolean retorno = service.isRelative(person, "JUnit");
        Assert.assertTrue(retorno);
    }

    /*
    No método isRelative() do Executer, faça os 2 cenários abaixo:
       Se a string passada for qualquer outra string, retorne false;
  */


//    @Test// Não está rodando
//    void returnfalseAnyway() {// aqui pode entrar Java ou JUnit, como resolver? any((!java)|| (!JUnit))
//        Mockito.when(detailedPersonUC.searchPersonRelatives(person, any())).thenReturn(false);
//        Boolean retorno = service.isRelative(person, "teste");
//        Assert.assertFalse(retorno);
//    }



    // Mockito.verify verificar varias coisas: numero de vezes que executou,
    //                                         parâmetros recebidos e etc.


    // ok  No método printStrAndInt() do Executer, teste se o método detailedPersonUC.printStringAndInt() foi chamado duas vezes
    //     e cada parâmetro em cada chamada.
    @Test
    void checkIsdetailedPersonUCPrintStringAndIntIsCollTwoTimes() {
        service.printStrAndInt();
        verify(detailedPersonUC, times(2)).printStringAndInt(anyString(), anyInt());
    }

//    @Test
//    void whatsParametersCallFirt() {
//        service.printStrAndInt();
//        verify(detailedPersonUC, times(1)).printStringAndInt(anyString(), anyInt());
//    }
//    @Test
//    void whatsParametersCallSecond() {
//        service.printStrAndInt();
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
    @Test
    void youngThemAge18NotCallPrintStringAndIntAndGetPersonAddress() {
        DetailedPerson person = new DetailedPerson();//instanciando o objeto da classe mocada
        person.setAge(17);//setando o valor
        person.setName("Nat");//seando o valor
        service.execute(person);// chamando o métado execute e passando o parametro

        verify(detailedPersonUC, never()).getPersonAddress(any(DetailedPerson.class));
        verify(detailedPersonUC, never()).printStringAndInt(anyString(), anyInt());


    }

    // Faça com que a variável address dentro do método execute() nunca fique vazia.
    @Test
    void varAddresNerverIsEmpty(){

        DetailedPerson person = new DetailedPerson();
        service.execute(person);
        Mockito.when(service.getAddressOf(isNotNull())).thenReturn(address);
        Address returnAddress = service.getAddressOf(person);
        Assert.assertNotNull(returnAddress);

    }

    @Test
    void youngThemAge18CheckIfPrintStringAndIntIsCall() {
        DetailedPerson person = new DetailedPerson();
        person.setAge(17);
        person.setName("Nat");
        service.execute(person);
        verify(detailedPersonUC, never()).getPersonAddress(any(DetailedPerson.class));
        verify(detailedPersonUC, never()).printStringAndInt(anyString(), anyInt());
        verify(detailedPersonUC, times(0)).printStringAndInt(anyString(), anyInt());
        // aqui, a quantidade de vezez que deve ser é zero chamadas, pq a idade <18.
    }


//      @Test
//    void olderOrIgualsAge18() {
//
//          DetailedPerson person = new DetailedPerson("Natalia", 30, Collections.singletonList("teste"), address);
//          Address address = new Address("av", "Gyn", "Go", "Brazil");
//          address.setStreet("Rua feliz");
//          service.execute(person);// aqui vai chegar a idade e o nome(30 e "Natalia")
//          service.getAddressOf(person);
//
//          detailedPersonUC.printStringAndInt(person.getName(), address.getStreet(), person.getAge());
//      }


}






