import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// функцию main писали вместе
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarShop carShop = new CarShop();

        System.out.println("Who are you? (admin/client)");
        String role = scanner.nextLine();

        if (role.equalsIgnoreCase("admin")) {
            System.out.println("Admin menu:");
            System.out.println("1. Add car");
            System.out.println("2. Remove car");
            System.out.println("3. Display cars");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter car model:");
                    String model = scanner.nextLine();
                    System.out.println("Enter car price:");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    carShop.addCar(new Car(model, price));
                    break;
                case 2:
                    System.out.println("Enter car model to remove:");
                    String modelToRemove = scanner.nextLine();
                    Car carToRemove = null;
                    for (Car car : carShop.getCars()) {
                        if (car.getModel().equalsIgnoreCase(modelToRemove)) {
                            carToRemove = car;
                            break;
                        }
                    }
                    if (carToRemove != null) {
                        carShop.removeCar(carToRemove);
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;
                case 3:
                    carShop.displayCars();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } else if (role.equalsIgnoreCase("client")) {
            System.out.println("Enter your budget:");
            int budget = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Person.Client client = new Person.Client("Client", 30, "client", budget);

            System.out.println("Client menu:");
            System.out.println("1. Display available cars");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    carShop.displayCars();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } else {
            System.out.println("Invalid role");
        }

        scanner.close();
    }
}
// Person писал iKokich(Никита Сметанко)
class Person {
    private String name;
    private int age;
    private String position;

    public Person(String name, int age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public void display() {
        System.out.printf("Person: %s, Age: %d, Position: %s\n", name, age, position);
    }

    // Вложенный класс Admin писал iKokich (Никита Сметанко)
    public static class Admin extends Person {
        private int exp;

        public Admin(String name, int age, String position, int exp) {
            super(name, age, position);
            this.exp = exp;
        }

        @Override
        public void display() {
            System.out.printf("Admin: %s has exp %d\n", getName(), exp);
        }

        public int getExp() {
            return exp;
        }
    }
// писал Женя Рассомакин (youngpika)
    public static class Seller extends Person {
        public int countOfSales;

        public Seller(String name, int age, String position) {
            super(name, age, position);
        }

        public int getCountOfSales() {
            return countOfSales;
        }
    }
// писал Женя Рассомакин (youngpika)
    public static class Client extends Person {
        private int wallet;

        public Client(String name, int age, String position, int wallet) {
            super(name, age, position);
            this.wallet = wallet;
        }

        public int getWallet() {
            return wallet;
        }

        public void setWallet(int wallet) {
            this.wallet = wallet;
        }
    }
}
// писал Кирилл Бабенко (K-A-Babenko)
class Car {
    private String model;
    private double price;

    public Car(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Model: " + model + ", Price: $" + price;
    }
}
//Писал Кирилл Бабенко (K-A-Babenko)
class CarShop {
    private List<Car> cars;

    public CarShop() {
        cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    public void displayCars() {
        System.out.println("Available cars: ");
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    public List<Car> getCars() {
        return cars;
    }
}

