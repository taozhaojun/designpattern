import java.util.Scanner;

public class DecoratorPattern   {

    public static void main(String[] args) {

        Computer currentComputer = new basicComputer();
//		Computer pcWithGPU = new GPU(defaultPC);
//		Computer benWillByComputer = new GPU(new GPU(new CPU(defaultPC)));
//
//		System.out.println(defaultPC.getDescription() + " price is " + defaultPC.getPrice());
//		System.out.println(pcWithGPU.getDescription() + " price is " + pcWithGPU.getPrice());
//		System.out.println(benWillByComputer.getDescription() + " price is " + benWillByComputer.getPrice());

        Scanner scan = new Scanner(System.in);
        System.out.println("Welcoeme to my tech store, here is your default computer: ");
        System.out.println(currentComputer.getDescription() + " price is " + currentComputer.getPrice());

        while (true) {
            System.out.println("Please add accessory e.g. GPU or CPU");
            String option = scan.nextLine();

            if (option.equals("GPU")) {
                currentComputer = new GPU(currentComputer);
            }else {
                currentComputer = new CPU(currentComputer);
            }
            System.out.println(currentComputer.getDescription() + " price is " + currentComputer.getPrice());
        }

    }

}

interface Computer {
    String getDescription();

    int getPrice();
}

class basicComputer implements Computer {

    @Override
    public String getDescription() {
        return "Default Computer";
    }

    @Override
    public int getPrice() {
        return 500;
    }

}

class ComuterDecorator implements Computer {

    Computer currentComputerSetting;

    public ComuterDecorator(Computer c) {
        currentComputerSetting = c;
    }

    @Override
    public String getDescription() {
        return currentComputerSetting.getDescription();
    }

    @Override
    public int getPrice() {
        return currentComputerSetting.getPrice();
    }

}

class GPU extends ComuterDecorator {

    public GPU(Computer c) {
        super(c);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + 4090TI";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 4000;
    }

}

class CPU extends ComuterDecorator {

    public CPU(Computer c) {
        super(c);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + I9 13900k";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 1500;
    }

}
