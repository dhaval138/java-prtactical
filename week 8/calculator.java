import java.util.Scanner;

public class calculator{
    public static double calculation(double num1,double num2,char num) throws DivideByZeroException,invalidnumber
    {
        if(num == '+') return num1 + num2;
        if(num == '-') return num1 - num2;
        if(num == '*') return num1*num2;
        if(num == '/')
        {
            if(num2 == 0)
            {
                throw new DivideByZeroException("Are you mentaly ill?");
            }
            return num1/num2;
        }
        throw new invalidnumber("Error:" + num + "is an invalid operator!");
    }
}


class DivideByZeroException extends Exception 
{
    public DivideByZeroException(String msg) 
    {
        super(msg);
    }
}
class invalidnumber extends Exception 
{
    public invalidnumber(String msg) 
    {
        super(msg);
    }
}

class Main
{
    public static void main(String[] args)
    {
        System.out.println("25AIML039");
        Scanner sc = new Scanner(System.in);
        boolean bb = false;
        int count = 0;
        while(!bb)
        {
            count++;
           try { 
                    System.out.println("-------Attemp:" + count + "-------"); 
                    System.out.println("Enter number 1: "); 
                    double num1 = sc.nextDouble(); 
                    System.out.println("Enter operator (+, -, *, /): "); 
                    char operator = sc.next().charAt(0);
                    System.out.println("Enter number 2: "); 
                    double num2 = sc.nextDouble(); 
                    double result = calculator.calculation(num1, num2, operator);
                    System.out.println("Result: " + result);    
                    bb = true;
                }


            catch (DivideByZeroException e)
            {
                System.out.println(e.getMessage() + " Please try again."); 

            }
            catch (invalidnumber e)
            {
                System.out.println(e.getMessage() + " Please try again."); 

            }
            catch (Exception e) 
            {
                System.out.println("Invalid input. Please enter valid numbers.");
                sc.nextLine();
            }
        }
        sc.close();
    }
}