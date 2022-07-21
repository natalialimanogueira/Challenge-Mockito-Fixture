package cap.mockito.fixtures.exercises.usecases;

import cap.mockito.fixtures.exercises.domain.Address;
import cap.mockito.fixtures.exercises.domain.DetailedPerson;

public class DetailedPersonUC {
    public Address getPersonAddress(DetailedPerson detailedPerson) {
        return detailedPerson.getAddress();
    }

    public boolean searchPersonRelatives(DetailedPerson detailedPerson, String relative){
        return detailedPerson.getRelatives().contains(relative);
    }

    public void drinkAlcohol(DetailedPerson detailedPerson){
        if (detailedPerson.getAge() >= 18) {
            System.out.println("Drinking alcohol");
        }
        else {
            throw new RuntimeException("You're not allowed to drink alcohol yet");
        }
    }
}
