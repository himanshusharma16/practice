package test.practice;

public interface Tester {
    public int getInt();

    public static String getName(){
        return "Name";
    };

    public default boolean getValue(){
        return false;
    }

}
