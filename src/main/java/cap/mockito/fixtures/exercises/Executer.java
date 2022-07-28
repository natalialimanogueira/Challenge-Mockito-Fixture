package cap.mockito.fixtures.exercises;

import cap.mockito.fixtures.exercises.domain.Address;
import cap.mockito.fixtures.exercises.domain.DetailedPerson;
import cap.mockito.fixtures.exercises.usecases.DetailedPersonUC;

public class Executer {
    private DetailedPersonUC detailedPersonUC;

    public Executer() {
    }

    public Executer(DetailedPersonUC detailedPersonUC) {
        this.detailedPersonUC = detailedPersonUC;
    }

    public Address getAddressOf(DetailedPerson detailedPerson) {
        return detailedPersonUC.getPersonAddress(detailedPerson);
    }

    public boolean isRelative(DetailedPerson detailedPerson, String relative) {
        return detailedPersonUC.searchPersonRelatives(detailedPerson, relative);
    }

    public void drinkAlcohol(DetailedPerson detailedPerson) {
        if (detailedPerson.getAge() >= 18) {
            detailedPersonUC.drinkAlcohol(detailedPerson);
        } else {
            throw new RuntimeException("You're not allowed to drink alcohol yet");
        }
    }

    public void printStrAndInt() {
        detailedPersonUC.printStringAndInt("str", 12);
        detailedPersonUC.printStringAndInt("str2", 21);
    }

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

    private boolean canDrink(int age) {
        return age >= 18;
    }
}
