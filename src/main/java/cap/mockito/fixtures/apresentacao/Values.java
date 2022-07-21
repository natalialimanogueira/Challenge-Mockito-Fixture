package cap.mockito.fixtures.apresentacao;

import java.util.Random;

public class Values {
    Random random = new Random();
    public Values() {
    }

    public int getValue(){
        return random.nextInt(10);
    }

    public int returnValue(int i){
        return i;
    }
}
