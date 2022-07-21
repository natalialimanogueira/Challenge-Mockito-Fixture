package cap.mockito.fixtures.apresentacao;

public class PrintValues {
    public PrintValues() {
    }

    public void print(int a, int b) {
        System.out.println("a + b = " + (a + b));
    }

    public void printStrings(String str1, String str2) {
        System.out.println(str1 + " " + str2);
    }
}
