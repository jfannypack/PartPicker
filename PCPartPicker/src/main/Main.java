package main;
import java.util.*;

// Creational Pattern - Factory Method
abstract class ComponentFactory {
    public abstract Component createComponent(String brand, String model, double ghzSpeed, double price, int ramSize, String socket);
}

class CPUFactory extends ComponentFactory {
    @Override
    public Component createComponent(String brand, String model, double ghzSpeed, double price, int ramSize, String socket) {
        return new CPU(brand, model, ghzSpeed, price, socket);
    }
}

class GPUFactory extends ComponentFactory {
    @Override
    public Component createComponent(String brand, String model, double ghzSpeed, double price, int ramSize, String socket) {
        return new GPU(brand, model, price, ramSize);
    }
}

class RAMFactory extends ComponentFactory {
    @Override
    public Component createComponent(String brand, String model, double ghzSpeed, double price, int ramSize, String socket) {
        return new RAM(brand, model, price, ramSize);
    }
}
class MOBOFactory extends ComponentFactory {
    @Override
    public Component createComponent(String brand, String model, double ghzSpeed, double price, int ramSize, String socket) {
        return new MOBO(brand, model, price, socket);
    }
}

// Component interface
interface Component {
    String getName();
    String getBrand();
    double getPrice();
    int getRamSize();
    String getSocket();
}

class CPU implements Component {
    private String brand;
    private String model;
    private double ghzSpeed;
    private double price;
    private String socket;

    public CPU(String brand, String model, double ghzSpeed, double price, String socket) {
        this.brand = brand;
        this.model = model;
        this.ghzSpeed = ghzSpeed;
        this.price = price;
        this.socket = socket;
    }

    @Override
    public String getName() {
        return "CPU: " + brand + " " + model;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    public double getGhzSpeed() {
        return ghzSpeed;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public int getRamSize() {
        return 0;
    }
    public String getSocket(){
        return socket;
    }
}

class GPU implements Component {
    private String brand;
    private String model;
    private double price;
    private int ramSize;
    private String socket;

    public GPU(String brand, String model, double price, int ramSize) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.ramSize = ramSize;
    }

    @Override
    public String getName() {
        return "GPU: " + brand + " " + model;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public int getRamSize() {
        return ramSize;
    }
    public String getSocket(){
        return socket;
    }
}

class RAM implements Component {
    private String brand;
    private String model;
    private double price;
    private int ramSize;
    public String socket;

    public RAM(String brand, String model, double price, int ramSize) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.ramSize = ramSize;
    }

    @Override
    public String getName() {
        return "RAM: " + brand + " " + model + " "+ ramSize + "GB";
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public int getRamSize() {
        return ramSize;
    }
    public String getSocket(){
        return socket;
    }
}
class MOBO implements Component {
    private String brand;
    private String model;
    private double price;
    public String socket;

    public MOBO(String brand, String model, double price, String socket) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.socket = socket;
    }

    @Override
    public String getName() {
        return "MOBO: " + brand + " " + model;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public int getRamSize() {
        return 0;
    }
    public String getSocket(){
        return socket;
    }
}

// Behavioral Pattern - Strategy
interface CompatibilityChecker {
    boolean isCompatible(List<Component> components);
}
class GamingCompatibilityChecker implements CompatibilityChecker {
    @Override
    public boolean isCompatible(List<Component> components) {
        // Implement gaming compatibility logic
        boolean hasIntelCPU = false;
        boolean hasNvidiaGPU = false;
        boolean hasAMDCPU = false;
        boolean hasAMGGPU = false;

        for (Component component : components) {
            if ("Intel".equalsIgnoreCase(component.getBrand())) {
                hasIntelCPU = true;
            } else if ("NVIDIA".equalsIgnoreCase(component.getBrand())) {
                hasNvidiaGPU = true;
            } else if ("AMD".equalsIgnoreCase(component.getBrand())) {
                hasAMDCPU = true;
            } else if ("AMD".equalsIgnoreCase(component.getBrand())) {
                hasAMGGPU = true;
            }
        }

        return (hasIntelCPU && hasNvidiaGPU) || (hasAMDCPU && hasAMGGPU);
    }
}

// Structural Pattern - Composite
class PCConfiguration {
    private List<Component> components = new ArrayList<>();

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    public void displayComponents() {
        System.out.println("Selected Components:");
        for (Component component : components) {
            System.out.println(component.getName());
        }
    }

    public boolean checkCompatibility(CompatibilityChecker checker) {
        return checker.isCompatible(components);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Component component : components) {
            totalPrice += component.getPrice();
        }
        return totalPrice;
    }
}

public class Main {
    public static int getValidInput(int min, int max, String prompt, Scanner scanner) {
        int userInput = 0;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                System.out.println(prompt);
                userInput = scanner.nextInt();

                if (userInput < min || userInput > max) {
                    System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                } else {
                    isValidInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        return userInput;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Create component factories
        ComponentFactory cpuFactory = new CPUFactory();
        ComponentFactory gpuFactory = new GPUFactory();
        ComponentFactory ramFactory = new RAMFactory();
        ComponentFactory moboFactory = new MOBOFactory();

        // Create lists to store CPU, GPU, and RAM options
        List<Component> cpuOptions = new ArrayList<>();
        List<Component> gpuOptions = new ArrayList<>();
        List<Component> ramOptions = new ArrayList<>();
        List<Component> moboOptions = new ArrayList<>();
        List<Component> compatibleMoboOptions = new ArrayList<>();


        // Create CPU options with random GHz speeds
        cpuOptions.add(cpuFactory.createComponent("Intel", "i7 12700K", 3.6, 279.99, 0, "LGA"));
        cpuOptions.add(cpuFactory.createComponent("AMD", "Ryzen 5 3600  ", 3.6, 119.99, 0, "AM5"));
        cpuOptions.add(cpuFactory.createComponent("Intel", "i3 13100", 3.4, 147.99, 0, "LGA"));

        // Create GPU options
        gpuOptions.add(gpuFactory.createComponent("NVIDIA", "GeForce RTX 3080", 0, 699.00, 0, ""));
        gpuOptions.add(gpuFactory.createComponent("AMD", "Radeon RX 6900 XT", 0, 999.00, 0, ""));
        gpuOptions.add(gpuFactory.createComponent("NVIDIA", "GeForce RTX 3060", 0, 299.00, 0, ""));

        // Create RAM options
        ramOptions.add(ramFactory.createComponent("Corsair", "Vengeance", 0, 345, 16, ""));
        ramOptions.add(ramFactory.createComponent("G.SKILL", "DDR5", 0, 80, 16, ""));


        moboOptions.add(moboFactory.createComponent("ASUS", "B550", 0, 99.99, 0, "AM5"));
        moboOptions.add(moboFactory.createComponent("ASRock", "Z90", 0, 109.99, 0, "LGA"));


        // Ask the user to select one CPU
        System.out.println("Select one CPU:");
        for (int i = 0; i < cpuOptions.size(); i++) {
            System.out.println(i + 1 + ". " + cpuOptions.get(i).getName());
        }

        int selectedCpuIndex = getValidInput(1, cpuOptions.size(), "Enter the number corresponding to your choice:", scanner) - 1;
        Component selectedCpu = cpuOptions.get(selectedCpuIndex);

        for (int i = 0; i < moboOptions.size(); i++) {
            if (selectedCpu.getSocket().equals(moboOptions.get(i).getSocket())) {
                compatibleMoboOptions.add(moboOptions.get(i));

            }
        }
        System.out.println("Select one MOBO:");
        for (int i = 0; i < compatibleMoboOptions.size(); i++) {
            System.out.println(i + 1 + ". " + compatibleMoboOptions.get(i).getName());
        }
        int selectedMoboIndex = getValidInput(1, compatibleMoboOptions.size(), "Enter the number corresponding to your choice:", scanner) - 1;
        Component selectedMobo = compatibleMoboOptions.get(selectedMoboIndex);




        // Ask the user to select one GPU
        System.out.println("Select one GPU:");
        for (int i = 0; i < gpuOptions.size(); i++) {
            System.out.println(i + 1 + ". " + gpuOptions.get(i).getName());
        }

        int selectedGpuIndex = getValidInput(1, gpuOptions.size(), "Enter the number corresponding to your choice:", scanner) - 1;
        Component selectedGpu = gpuOptions.get(selectedGpuIndex);

        // Ask the user to select one RAM
        System.out.println("Select one RAM:");
        for (int i = 0; i < ramOptions.size(); i++) {
            System.out.println(i + 1 + ". " + ramOptions.get(i).getName() + " " + ramOptions.get(i).getRamSize() + "GB");
        }

        int selectedRamIndex = getValidInput(1, ramOptions.size(), "Enter the number corresponding to your choice:", scanner) - 1;
        Component selectedRam = ramOptions.get(selectedRamIndex);

        // Ask the user how many sets of RAM they want
        int numRamSets = getValidInput(1, Integer.MAX_VALUE, "Enter the number of RAM sets you want:", scanner);

        // Create a PC configuration
        PCConfiguration configuration = new PCConfiguration();
        configuration.addComponent(selectedCpu);
        configuration.addComponent(selectedGpu);
        configuration.addComponent(selectedMobo);

        // Add the specified number of RAM sets
        for (int i = 0; i < numRamSets; i++) {
            configuration.addComponent(selectedRam);
        }

        // Use the Strategy pattern to check compatibility for gaming
        CompatibilityChecker gamingChecker = new GamingCompatibilityChecker();
        boolean isGamingCompatible = configuration.checkCompatibility(gamingChecker);

        System.out.println("Gaming Compatibility: " + isGamingCompatible);

        // Display selected components
        configuration.displayComponents();
        double totalPrice = configuration.calculateTotalPrice();
        System.out.println("Total Price: $" + totalPrice);
    }
}
