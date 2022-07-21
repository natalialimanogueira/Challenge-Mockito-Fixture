package cap.mockito.fixtures.apresentacao;

import java.util.List;

public class Person {
    private String name;
    private int age;
    private String phones;

    @Override
    public String   toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phones=" + phones +
                '}';
    }
}
