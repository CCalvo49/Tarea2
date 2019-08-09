package sample;

public class ExceptionsEx {
    public static void main(String[] args){
        int x,y,z;
        try {
            x = 5;
            y = 2;
            z = x + y;
            System.out.println("The sum is equal to: " + z);
            throw new ArithmeticException();
        }catch (NullPointerException e){
            System.out.println("There is a null pointer exception");
        }
        catch (ArithmeticException e){
            System.out.println("There is an arithmetic expection");
        }
        finally {
            System.out.println("Finally here");
        }

    }
}
