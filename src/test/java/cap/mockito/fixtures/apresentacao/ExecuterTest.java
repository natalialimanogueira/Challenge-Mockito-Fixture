package cap.mockito.fixtures.apresentacao;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import cap.mockito.fixtures.exercises.Executer;
import cap.mockito.fixtures.exercises.domain.Address;
import cap.mockito.fixtures.exercises.domain.DetailedPerson;
import cap.mockito.fixtures.exercises.usecases.DetailedPersonUC;
import cap.mockito.fixtures.templates.DetailPersonTemplate;
import cap.mockito.fixtures.templates.PersonTemplate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Random;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)//1

public class ExecuterTest {

    @InjectMocks
    private Executer executer;
    @Mock
    private DetailedPersonUC detailedPersonUC;

    @Mock//2
    private DetailedPerson person;

    @BeforeAll
    static void init() {
        //FixtureFactoryLoader.loadTemplates(PersonNatTemplate.PATH_LOADERR);
        FixtureFactoryLoader.loadTemplates("cap.mockito.fixtures.templates");
    }

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    //No método getAddressOf() do Executer, faça ele retornar um endereço mesmo passando um objeto vazio
    void returnAddressAnyWay() {

        DetailedPerson detailedPerson = new DetailedPerson();
        Address address = Fixture.from(Address.class).gimme("valido");
        Mockito.when(executer.getAddressOf(detailedPerson)).thenReturn(address);
        Address retorno = executer.getAddressOf(detailedPerson);
        Assert.assertNotNull(retorno);
        Assert.assertEquals(address, retorno);
        System.out.println(retorno);
    }
    @Test
    //No método isRelative() do Executer, passando"Java" retorne true
    void returnTrueJava() {
        Mockito.when(detailedPersonUC.searchPersonRelatives(person, "Java")).thenReturn(true);
        Boolean retorno = executer.isRelative(person, "Java");
        Assert.assertTrue(retorno);
    }


    @Test
        //No método isRelative() do Executer, passando"JUnit" retorne true
    void returnTrueJUnit() {
        Mockito.when(detailedPersonUC.searchPersonRelatives(person, "JUnit")).thenReturn(true);
        Boolean retorno = executer.isRelative(person, "JUnit");
        Assert.assertTrue(retorno);
    }
    @Test
    void returnfalseAnyway() {
        //Se a string passada for qualquer outra string, retorne false
        Mockito.when(detailedPersonUC.searchPersonRelatives(any(), any())).thenReturn(false);
        Boolean retorno = executer.isRelative(person, "Teste");
        Assert.assertFalse(retorno);
        System.out.println(retorno);//false
    }

    @Test
    //No método printStrAndInt() do Executer, teste se o método detailedPersonUC.printStringAndInt()
        // foi chamado duas vezes
    void checkIsDetailedPersonUCPrintStringAndIntIsCollTwoTimes() {
        executer.printStrAndInt();
        verify(detailedPersonUC, times(2)).printStringAndInt(anyString(), anyInt());
    }
    @Test

    //cada parâmetro em cada chamada.
    void whatsParametersCallSecond() {
//        Random random = new Random();
//        int numero = random.nextInt(100);
        executer.printStrAndInt();
        verify(detailedPersonUC).printStringAndInt("str", 12);
        verify(detailedPersonUC).printStringAndInt( "str2", 21);
    }

    @Test
    //Se a idade for menor que 18,não chamar  detailedPersonUC.printStringAndInt() e
        // detailedPersonUC.getPersonAddress()
    void youngThemAge18NotCallPrintStringAndIntAndGetPersonAddress() {
        DetailedPerson person = DetailPersonTemplate.gimmeNatalia();
        executer.execute(person);
        verify(detailedPersonUC, never()).getPersonAddress(any(DetailedPerson.class));
        verify(detailedPersonUC, never()).printStringAndInt(anyString(), anyInt());
    }
    @Test
        //Se a idade for maior que 18,chamar  detailedPersonUC.printStringAndInt() e
        // detailedPersonUC.getPersonAddress()
    void egualOrOldThemAge18CallPrintStringAndIntAndGetPersonAddress() {
        DetailedPerson person = DetailPersonTemplate.gimmeNatalia();
        executer.execute(person);
        verify(detailedPersonUC, atMostOnce()).getPersonAddress(any(DetailedPerson.class));
        verify(detailedPersonUC, atMostOnce()).printStringAndInt(anyString(), anyInt());
    }
    @Test
    //Teste se o método printStringAndInt() é chamado (verificada acima. chamado 2x)
        // em cada caso e com os parâmetros corretos (chacado no métado whatsParametersCallSecond).
    void isPrintStringAndIntIsCall(){
        executer.printStrAndInt();
        verify(detailedPersonUC, atLeast(1)).printStringAndInt(anyString(), anyInt());
        verify(detailedPersonUC).printStringAndInt("str", 12);
        verify(detailedPersonUC).printStringAndInt( "str2", 21);
    }

    @Test
    //Faça com que a variável address dentro do método execute() nunca fique vazia.
    void varAddresNerverIsEmpty(){
        DetailedPerson person = new DetailedPerson();
        executer.execute(person);
        Address address = Fixture.from(Address.class).gimme("valido");
        Mockito.when(executer.getAddressOf(isNotNull())).thenReturn(address);
        Address returnAddress = executer.getAddressOf(person);
        Assert.assertNotNull(returnAddress);
        System.out.println(returnAddress);
    }

    @Test
    void testFixtures() {
        Person p = PersonTemplate.gimmeNatalia();
        System.out.println("person = " + person);

        List<Person> natalias = Fixture.from(Person.class).gimme(4, PersonTemplate.Natalia);
        System.out.println("nathalia = " + natalias);
    }

}


