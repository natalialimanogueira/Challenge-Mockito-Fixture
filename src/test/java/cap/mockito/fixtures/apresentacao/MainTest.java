package cap.mockito.fixtures.apresentacao;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import cap.mockito.fixtures.apresentacao.Main;
import cap.mockito.fixtures.apresentacao.Person;
import cap.mockito.fixtures.apresentacao.PrintValues;
import cap.mockito.fixtures.apresentacao.Values;
import cap.mockito.fixtures.templates.PersonTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static cap.mockito.fixtures.templates.PersonTemplate.ANDREOLI;
import static cap.mockito.fixtures.templates.PersonTemplate.XPTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainTest {


    // @Spy faz chamada da classe real.
    @Mock
    PrintValues printValues/* = mock(PrintValues.class)*/;

    @Spy
    Values values/* = mock(Values.class)*/;

    //  @InjectMocks = criar uma intancia e injeta as dependências necessárias que estão anotadas com @Mock.

    @InjectMocks// ou uso o Spy, pois ele é uma alternativa ao  @InjectMocks
    Main home;

    @Captor
    ArgumentCaptor<String> str1Captor/* = ArgumentCaptor.forClass(String.class)*/;
    @Captor
    ArgumentCaptor<String> str2Captor/* = ArgumentCaptor.forClass(String.class)*/;

    @BeforeAll
    static void init() {
        FixtureFactoryLoader.loadTemplates(PersonTemplate.PATH_LOADER);
        FixtureFactoryLoader.loadTemplates("cap.mockito.fixtures.templates");
    }

    @Test
    void printSum() {
        this.home.printSum(1, 4);

//      O método verify (Mockito.verify) é usado para testar a chamada de um método mockado.
        verify(printValues).print(1, 4);
/*
        Esses são outras possibilidades do uso do verify para testar um número específico de chamadas
        (mínimo/máximo) ou mesmo se ela nunca foi chamada:

        verify(printValues, times(1)).print(1, 4);
        verify(printValues, atMostOnce()).print(1, 4);
        verify(printValues, atLeastOnce()).print(1, 4);
        verify(printValues, atLeast(1)).print(1, 4);
        verify(printValues, atMost(1)).print(1, 4);
        verify(printValues, never()).print(1, 4);
*/
    }

    @Test
    void printValue() {
//      O método when (Mockito.when) é usado para fazer stubs, mockar o valor retornado de um método.
        when(values.getValue()).thenReturn(3);
/*
        Também é possível lançar exceções com stubs:
        when(values.getValue()).thenThrow(new RuntimeException());
        doThrow(new RuntimeException()).when(values).getValue();
*/
/*
        O stub também pode ser feito dessa forma:
        doReturn(3).when(values).getValue();
*/
        int result = this.home.printValue();
        verify(values, times(1)).getValue();
        assertEquals(result, 3);
    }

    @Test
    void valuePlusOne() {
//      Neste stub, se o método thenReturn() for chamado com o ArgumentMatcher anyInt(), então o retorno é 3
        when(values.returnValue(anyInt())).thenReturn(3);
//      Já neste, se o método thenReturn() for chamado com 1 (especificamente), então o retorno é 2
        when(values.returnValue(1)).thenReturn(2);

        int result = this.home.valuePlusOne(anyInt());
        int result1 = this.home.valuePlusOne(1);

//      Aqui o ArgumentMatcher anyInt() é usado para verificar se o método testado foi chamado independentemente do
//      parâmetro
        verify(values, times(2)).returnValue(anyInt());

        assertEquals(4, result);
        assertEquals(3, result1);
    }

    @Test
    void printString() {
        this.home.printString("Mockito", "Fixtures");
        verify(printValues).printStrings(str1Captor.capture(), str2Captor.capture());
        assertEquals("Mockito", str1Captor.getValue());
        assertEquals("Fixtures", str2Captor.getValue());
    }

    @Test
    void testFixtures() {
        Person p = Fixture.from(Person.class).gimme(ANDREOLI);
        Person person = PersonTemplate.gimmeAndreoli();
        System.out.println("person = " + person);

        List<Person> persons = Fixture.from(Person.class).gimme(2, ANDREOLI, XPTO);
        System.out.println("persons = " + persons);

        List<Person> andreolis = Fixture.from(Person.class).gimme(4, ANDREOLI);
        System.out.println("andreolis = " + andreolis);
    }
}
