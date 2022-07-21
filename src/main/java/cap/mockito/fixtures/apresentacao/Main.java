package cap.mockito.fixtures.apresentacao;

public class Main {
    PrintValues printValues;
    Values values;

    public Main(PrintValues printValues, Values values) {
        this.printValues = printValues;
        this.values = values;
    }

    public void printSum(int a, int b) {
        printValues.print(a, b);
    }

    public int printValue() {
        return values.getValue();
    }

    public int valuePlusOne(int i) {
        return values.returnValue(i) + 1;
    }

    public void printString(String str1, String str2) {
        printValues.printStrings(str1, str2);
    }
}
