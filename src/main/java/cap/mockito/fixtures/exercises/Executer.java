package cap.mockito.fixtures.exercises;

import cap.mockito.fixtures.exercises.domain.Address;
import cap.mockito.fixtures.exercises.domain.DetailedPerson;
import cap.mockito.fixtures.exercises.usecases.DetailedPersonUC;

import java.util.Arrays;

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
        detailedPersonUC.drinkAlcohol(detailedPerson);
    }
}

