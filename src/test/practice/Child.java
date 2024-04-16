package test.practice;

public class Child extends Parent{
    public Child(){
        super(0);
        //this(5);
    }

    public Child(int a){
        this();
    }

    public static void main(String... s){
        Parent c = new Child();
        System.out.println(c.getInt());
        Child.getInt();
    }
}
