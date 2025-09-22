package Lambda;

public class ejemplo2 {
    interface Greeting{
        void SayHello(String name);
    }

    public static void main(String[] args) {
        Greeting greeting = (name -> System.out.println("hello "+name));
        greeting.SayHello("juan");
    }
}
