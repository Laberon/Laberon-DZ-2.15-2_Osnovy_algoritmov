package LaberonLSDZ2;

public class Main {
    public static void main(String[] args) {
        IntegerList integerList = new IntegerListImpl();
        integerList.add(2);
        integerList.add(5);
        integerList.add(8);
        integerList.add(-1);

        integerList.add(3, 6);
        System.out.println(integerList.contains(8));
        System.out.println(integerList.contains(6));
    }
}
