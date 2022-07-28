package cap.mockito.fixtures.exercises.usecases;

import cap.mockito.fixtures.exercises.domain.Address;
import cap.mockito.fixtures.exercises.domain.DetailedPerson;

public class DetailedPersonUC {
    public Address getPersonAddress(DetailedPerson detailedPerson) {
        return detailedPerson.getAddress();
    }

    public boolean searchPersonRelatives(DetailedPerson detailedPerson, String relative) {
        return detailedPerson.getRelatives().contains(relative);
    }

    public void drinkAlcohol(DetailedPerson detailedPerson) {
        System.out.println(detailedPerson.getName() + " is drinking alcohol");
    }

    public void printStringAndInt(String str, int number) {
        System.out.println("str = " + str);
        System.out.println("number = " + number);
    }
}
