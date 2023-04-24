import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
public class StrategyPattern{

    public static void makePayment(PaymentMethod method, int amount) {
        method.pay(amount);
    }

    public static void main(String[] args) {
//		int amount = 0;
//
        Scanner scan = new Scanner(System.in);
//		System.out.println("Please enter amount you need to pay");
//		amount = Integer.parseInt(scan.nextLine());
//
//		System.out.println("Enter payment method (c for credit or p for PayPAl)");
//		String method = scan.nextLine();
//
//		if(method.equals("c")) {
//			makePayment(new PayByCreditCard(), amount);
//		}else {
//			makePayment(new PayByPayPal(), amount);
//		}

        Random rnd = new Random();

        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            numbers.add(rnd.nextInt(500));
        }
        System.out.println("Here is your original numbers " + numbers);
        System.out.println("Enter sorting method (i for inreasing or d for decreasing): ");
        String method = scan.nextLine();

        if(method.equals("i")) {
            new SortByIncreasingOrder().sort(numbers);
        }else {
            new SortByDecreasingOrder().sort(numbers);
        }

        System.out.println("after sorting: " + numbers);



    }

}

class SortByIncreasingOrder implements SortStrategey{

    @Override
    public void sort(ArrayList<Integer> list) {
        Collections.sort(list);

    }

}

class SortByDecreasingOrder implements SortStrategey{

    @Override
    public void sort(ArrayList<Integer> list) {
        Collections.sort(list);
        Collections.reverse(list);

    }

}

interface SortStrategey{
    void sort(ArrayList<Integer> list);
}

interface PaymentMethod{
    void pay(int amount);
}

class PayByCreditCard implements PaymentMethod{

    @Override
    public void pay(int amount) {
        System.out.println("Scanning your credit card");
        System.out.println("Pay " + amount + " from your card");

    }

}

class PayByPayPal implements PaymentMethod{

    @Override
    public void pay(int amount) {
        System.out.println("Please login to your PayPal account");
        System.out.println("Withdraw " + amount + " from your account");
    }

}





