package cap.mockito.fixtures.exercises.domain;

import java.util.List;

public class DetailedPerson {
    private String name;
    private int age;
    private List<String> relatives;
    private Address address;

    public DetailedPerson() {
    }

    public DetailedPerson(String name, int age, List<String> relatives, Address address) {
        this.name = name;
        this.age = age;
        this.relatives = relatives;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<String> relatives) {
        this.relatives = relatives;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DetailedPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", relatives=" + relatives +
                ", address=" + address +
                '}';
    }
}
