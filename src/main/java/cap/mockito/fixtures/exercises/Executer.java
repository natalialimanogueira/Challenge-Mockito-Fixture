package cap.mockito.fixtures.exercises;

import cap.mockito.fixtures.exercises.domain.Address;
import cap.mockito.fixtures.exercises.domain.DetailedPerson;
import cap.mockito.fixtures.exercises.usecases.DetailedPersonUC;

public class Executer {
    private DetailedPersonUC detailedPersonUC;

    public Executer() {// CONSTRUTOR SEM PARAMENTROS
    }
    public Executer(DetailedPersonUC detailedPersonUC) {// CONSTRUTOR COM PARAMETRO
        this.detailedPersonUC = detailedPersonUC;
    }
    //métado q recebe como parametro um datailePerson e retorna um Address do DetailePerson,
    public Address getAddressOf(DetailedPerson detailedPerson) {
                              // esse metado pega o detailePersom e devolve o endereço dele (detailePerson)
        return detailedPersonUC.getPersonAddress(detailedPerson);
    }
    // esse metado recebe um detailePerson e uma string. Retorna verdadeiro se existir/ conter uma relative
    public boolean isRelative(DetailedPerson detailedPerson, String relative) {
        return detailedPersonUC.searchPersonRelatives(detailedPerson, relative);
    }
    // metado sem retorno mas recebe um detailePerson
    // se a idade de detailePerson for maior ou igual a 18
    //irá printar o nome da pessoa  + is drinking alcohol

    // caso contrario retorna a mensagem You're not allowed to drink alcohol yet

    public void drinkAlcohol(DetailedPerson detailedPerson) {
        if (detailedPerson.getAge() >= 18) {
            detailedPersonUC.drinkAlcohol(detailedPerson);
        } else {
            throw new RuntimeException("You're not allowed to drink alcohol yet");
        }
    }
    //metado não retorna nada e nao recebe nenhum parametro
    // e imprime    System.out.println("str = " + str);
    //              System.out.println("number = " + number);
    public void printStrAndInt() {
        detailedPersonUC.printStringAndInt("str", 12);
        detailedPersonUC.printStringAndInt("str2", 21);
    }
    //metado recebe um detailePerson e não retorna nada
    //verifica se a idade for menor que 18 nao retorna nada
    // tenta executar o métado getAddressOf que retorna um endereço de DetailePerson
    //então imprime System.out.println("str = " + str);
    //              System.out.println("number = " + number); + o nome + Street + idade.

    // se nao conseguir, não vier esses dados, ele lança uma execeção com a frase:
    //"Something went wrong, error code:
    public void execute(DetailedPerson detailedPerson) {
        if (!canDrink(detailedPerson.getAge())) return;
        try {
            Address address = getAddressOf(detailedPerson);
            detailedPersonUC.printStringAndInt(
                    detailedPerson.getName() +
                            " lives in the street" + address.getStreet() + ", ",
                    detailedPerson.getAge());
        } catch (Exception e) {
            detailedPersonUC.printStringAndInt("Something went wrong, error code: ", 505);
        }
    }
 //metado não recebe nada e retorna true quando a idade é maior que 18
    private boolean canDrink(int age) {
        return age >= 18;
    }
}
