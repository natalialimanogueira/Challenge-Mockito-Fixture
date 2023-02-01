package cap.mockito.fixtures.apresentacao;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import cap.mockito.fixtures.exercises.Executer;
import cap.mockito.fixtures.exercises.domain.Address;
import cap.mockito.fixtures.exercises.domain.DetailedPerson;
import cap.mockito.fixtures.exercises.usecases.DetailedPersonUC;
import cap.mockito.fixtures.templates.PersonNatTemplate;
import cap.mockito.fixtures.templates.PersonTemplate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static cap.mockito.fixtures.templates.PersonNatTemplate.Natalia;
import static cap.mockito.fixtures.templates.PersonNatTemplate.ex;
import static cap.mockito.fixtures.templates.PersonTemplate.ANDREOLI;
import static cap.mockito.fixtures.templates.PersonTemplate.XPTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)//1

public class ExecuterTest {
    //como a classe Execute será testada, faz-se necessário a inclusão dessa anotação (@InjectMocks), uma vez que
    // será criado mocks
    @InjectMocks
    private Executer executer;
    //  @Mock essa anotação é usada para fazer o mock de um objeto/classe. Aqui é criada uma cópia
    //  da estrutura dessa classe com uma implementação ficticia.
    @Mock
    private DetailedPersonUC detailedPersonUC;

    @Mock//2
    private DetailedPerson person;
    @Mock
    private Address address1;

    @BeforeAll
    static void init() {
        FixtureFactoryLoader.loadTemplates(PersonNatTemplate.PATH_LOADERR);
        FixtureFactoryLoader.loadTemplates("cap.mockito.fixtures.templates");
    }
    @Before    // habilitando as anotaçoes @InjectMocks e @Mock
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    //mesmo passando um objeto (detailedPerson) vazio, precisa retornar um endereço
    @Test
    void returnAddressAnyWay() {
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
    void returnTrueJava() {
        Mockito.when(detailedPersonUC.searchPersonRelatives(person, "Java")).thenReturn(true);
        Boolean retorno = executer.isRelative(person, "Java");
        Assert.assertTrue(retorno);
    }
//    @Test
//    void IfSearchPersonRelativesWasCall(){
//        verify(detailedPersonUC, times(1)).searchPersonRelatives(person, "Java");
//        assertEquals(true, "Java");
//    }


    @Test
    void returnTrueJUnit() {
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
    @Test
    void checkIsDetailedPersonUCPrintStringAndIntIsCollTwoTimes() {
        executer.printStrAndInt();
        verify(detailedPersonUC, times(2)).printStringAndInt(anyString(), anyInt());
    }

//    @Test
//    void whatsParametersCallFirt() {
//        executer.printStrAndInt();
//        Mockito.verify(detailedPersonUC, times(1)).printStringAndInt(anyString(), anyInt());


//    }
//    @Test
//    void whatsParametersCallSecond() { //rodando
//        executer.printStrAndInt();
//        verify(detailedPersonUC, times(2)).printStringAndInt(anyString(), anyInt());
//    }
    // Se a idade for menor que 18, faça o teste para assegurar que o detailedPersonUC.printStringAndInt() e detailedPersonUC.getPersonAddress() não são chamados.
//    @Test//rodando
    void youngThemAge18NotCallPrintStringAndIntAndGetPersonAddress() {
        DetailedPerson person = new DetailedPerson();//instanciando o objeto da classe mocada
        person.setAge(17);//setando o valor
        person.setName("Nat");//seando o valor
        executer.execute(person);// chamando o métado execute e passando o parametro

        verify(detailedPersonUC, never()).getPersonAddress(any(DetailedPerson.class));
        verify(detailedPersonUC, never()).printStringAndInt(anyString(), anyInt());

    }

//    // Faça com que a variável address dentro do método execute() nunca fique vazia.
    @Test
    void varAddresNerverIsEmpty(){//rodando

        DetailedPerson person = new DetailedPerson();
        executer.execute(person);
        Mockito.when(executer.getAddressOf(isNotNull())).thenReturn(address1);
        Address returnAddress = executer.getAddressOf(person);
        Assert.assertNotNull(returnAddress);
    }

    @Test
    void youngThemAge18CheckIfPrintStringAndIntIsCall() {//rodando
        DetailedPerson person = new DetailedPerson();
        person.setAge(17);
        person.setName("Nat");
        executer.execute(person);
        verify(detailedPersonUC, never()).getPersonAddress(any(DetailedPerson.class));
        verify(detailedPersonUC, never()).printStringAndInt(anyString(), anyInt());
        verify(detailedPersonUC, times(0)).printStringAndInt(anyString(), anyInt());
        // aqui, a quantidade de vezez que deve ser é zero chamadas, pq a idade <18.
    }

      @Test
    void olderOrIgualsAge18() { //rodando

          DetailedPerson person = new DetailedPerson("Natalia", 30, Collections.singletonList("teste"), address1);
          Address address = new Address("av", "Gyn", "Go", "Brazil");
          address.setStreet("Rua feliz");
          executer.execute(person);// aqui vai chegar a idade e o nome(30 e "Natalia")
          executer.getAddressOf(person);
          detailedPersonUC.printStringAndInt(person.getName(), person.getAge());
      }

    @Test
    void testFixtures() {
        Person p = Fixture.from(Person.class).gimme(Natalia);
        Person person = PersonNatTemplate.gimmeNatalia();
        System.out.println("person = " + person);

        List<Person> persons = Fixture.from(Person.class).gimme(2, Natalia, ex);
        System.out.println("persons = " + persons);

        List<Person> natalias = Fixture.from(Person.class).gimme(4, Natalia);
        System.out.println("nathalia = " + natalias);
    }


}






