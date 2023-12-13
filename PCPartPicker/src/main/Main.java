package main;
import java.util.*;

// Creational Pattern - Factory Method
abstract class ComponentFactory {
    public abstract Component createComponent(String brand, String model, String category, double ghzSpeed, double price, int ramSize, String socket, String storageType, String storageSize);
}

class CPUFactory extends ComponentFactory {
    @Override
    public Component createComponent(String brand, String model, String category, double ghzSpeed, double price, int ramSize, String socket, String storageType, String storageSize) {
        return new CPU(brand, model, category, ghzSpeed, price, socket);
    }
}

class GPUFactory extends ComponentFactory {
    @Override
    public Component createComponent(String brand, String model, String category, double ghzSpeed, double price, int ramSize, String socket, String storageType, String storageSize) {
        return new GPU(brand, model, category, price, ramSize);
    }
}

class RAMFactory extends ComponentFactory {
    @Override
    public Component createComponent(String brand, String model, String category, double ghzSpeed, double price, int ramSize, String socket, String storageType, String storageSize) {
        return new RAM(brand, model, category, price, ramSize);
    }
}
class MOBOFactory extends ComponentFactory {
    @Override
    public Component createComponent(String brand, String model, String category, double ghzSpeed, double price, int ramSize, String socket, String storageType, String storageSize) {
        return new MOBO(brand, model, category,  price, socket);
    }
}
class STORAGEFactory extends ComponentFactory {
    @Override
    public Component createComponent(String brand, String model, String category, double ghzSpeed, double price, int ramSize, String socket, String storageType, String storageSize) {
        return new STORAGE(brand, model, category,price,  storageType, storageSize);
    }
}

// Component interface
interface Component {
    String getName();
    String getBrand();
    double getPrice();
    int getRamSize();
    String getSocket();
    String getStorageSize();
    String getCategory();
}

class CPU implements Component {
    private String brand;
    private String model;
    private double ghzSpeed;
    private double price;
    private String socket;
    private String storageSize;
    private String category;

    public CPU(String brand, String model, String category, double ghzSpeed, double price, String socket) {
        this.brand = brand;
        this.model = model;
        this.category = category;
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
    public String getStorageSize(){
        return storageSize;
    }
    public String getCategory(){
        return category;
    }
}

class GPU implements Component {
    private String brand;
    private String model;
    private double price;
    private int ramSize;
    private String socket;
    private String storageSize;
    private String category;

    public GPU(String brand, String model, String category, double price, int ramSize) {
        this.brand = brand;
        this.model = model;
        this.category = category;
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
    public String getStorageSize(){
        return storageSize;
    }
    public String getCategory(){
        return category;
    }
}

class RAM implements Component {
    private String brand;
    private String model;
    private double price;
    private int ramSize;
    public String socket;
    public String storageSize;
    public String category;

    public RAM(String brand, String model, String category, double price, int ramSize) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.ramSize = ramSize;
    }

    @Override
    public String getName() {
        return "RAM: " + brand + " " + model;
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
    public String getStorageSize(){
        return storageSize;
    }
    public String getCategory(){
        return category;
    }
}
class STORAGE implements Component {
    private String brand;
    private String model;
    private double price;
    public String storageType;
    public String storageSize;
    public String category;

    public STORAGE(String brand, String model, String category, double price, String storageType, String storageSize) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.price = price;
        this.storageType = storageType;
        this.storageSize = storageSize;
    }

    @Override
    public String getName() {
        return "Storage: " + brand + " " + model;
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
        return "";
    }
    public String getStorageSize(){
        return storageSize;
    }
    public String getCategory(){
        return category;
    }
}
class MOBO implements Component {
    private String brand;
    private String model;
    private double price;
    public String socket;
    public String storageSize;
    public String category;

    public MOBO(String brand, String model, String category, double price, String socket) {
        this.brand = brand;
        this.model = model;
        this.category = category;
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
    public String getStorageSize(){
        return storageSize;
    }
    public String getCategory(){
        return category;
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
    public static void printComputerASCIIArt(PCConfiguration configuration) {
        // ASCII art generation logic here
        System.out.println(" ______________________________");
        System.out.println("|     Gaming PC Configuration     |");
        System.out.println("|--------------------------------|");
        configuration.displayComponents();
        System.out.println("|");
        System.out.println("|");
        System.out.println("|");
        System.out.println("|--------------------------------|");
        System.out.println("|       Total Price: $" + configuration.calculateTotalPrice());
        System.out.println("|________________________________|");
    }
    public static String getBuildType(Scanner scanner) {
        System.out.println("Select the type of build:");
        System.out.println("1. Gaming");
        System.out.println("2. Productivity");

        int choice = getValidInput(1, 2, "Enter your choice (1 for Gaming, 2 for Productivity):", scanner);
        return choice == 1 ? "Gaming" : "Productivity";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String buildType = getBuildType(scanner);

        // Create component factories
        ComponentFactory cpuFactory = new CPUFactory();
        ComponentFactory gpuFactory = new GPUFactory();
        ComponentFactory ramFactory = new RAMFactory();
        ComponentFactory moboFactory = new MOBOFactory();
        ComponentFactory storageFactory = new STORAGEFactory();

        // Create lists to store CPU, GPU, and RAM options
        List<Component> cpuOptions = new ArrayList<>();
        List<Component> gpuOptions = new ArrayList<>();
        List<Component> ramOptions = new ArrayList<>();
        List<Component> moboOptions = new ArrayList<>();
        List<Component> compatibleMoboOptions = new ArrayList<>();
        List<Component> storageOptions = new ArrayList<>();
        List<Component> filteredCpuOptions = new ArrayList<>();
        List<Component> filteredGpuOptions = new ArrayList<>();


        // Create CPU options with random GHz speeds
        cpuOptions.add(cpuFactory.createComponent("Intel", "i7 12700K", "Gaming",3.6, 279.99, 0, "LGA 1700", "", "0"));
        cpuOptions.add(cpuFactory.createComponent("Intel", "i3 13100", "Productivity",3.4, 147.99, 0, "LGA 1700", "", "0"));
        cpuOptions.add(cpuFactory.createComponent("Intel", "Celeron G5925", "Productivity",3.6, 60.00, 0, "LGA 1200", "", "0"));
        cpuOptions.add(cpuFactory.createComponent("AMD", "Ryzen 5 3600  ", "Gaming",3.6, 119.99, 0, "AM5", "", "0"));
        cpuOptions.add(cpuFactory.createComponent("AMD", "Ryzen 5 7600X  ", "Gaming",4.7, 218.99, 0, "AM5", "", "0"));
        cpuOptions.add(cpuFactory.createComponent("AMD", "Ryzen Threadripper PRO 5955WX", "Productivity",4.0, 1034.99, 0, "sWRX8", "", "0"));

        // Create GPU options
        gpuOptions.add(gpuFactory.createComponent("NVIDIA", "GeForce RTX 3080", "Gaming",0, 699.00, 0, "", "", "0"));
        gpuOptions.add(gpuFactory.createComponent("AMD", "Radeon RX 6900 XT", "Gaming",0, 999.00, 0, "", "", "0"));
        gpuOptions.add(gpuFactory.createComponent("NVIDIA", "GeForce RTX 3060", "Gaming",0, 299.00, 0, "", "", "0"));
        gpuOptions.add(gpuFactory.createComponent("NVIDIA", "RTX Titan", "Productivity",0, 1599.99, 0, "", "", "0"));

        // Create RAM options
        ramOptions.add(ramFactory.createComponent("Corsair", "Vengeance", "Gaming",0, 345, 16, "", "", "0"));
        ramOptions.add(ramFactory.createComponent("G.SKILL", "DDR5", "Gaming", 0, 80, 16, "", "", "0"));


        moboOptions.add(moboFactory.createComponent("ASUS", "B550", "Gaming",0, 99.99, 0, "AM5", "", "0"));
        moboOptions.add(moboFactory.createComponent("ASUS", "Pro WS WRX80E-SAGE", "Productivity",0, 999.00, 0, "sWRX8", "", "0"));
        moboOptions.add(moboFactory.createComponent("ASRock", "Z90", "Gaming",0, 109.99, 0, "LGA 1700", "", "0"));
        moboOptions.add(moboFactory.createComponent("ASRock", "B660M PRO RS", "Gaming",0, 89.99, 0, "LGA 1700", "", "0"));
        moboOptions.add(moboFactory.createComponent("GIGABYTE", "Z790 GAMING X AX", "Gaming",0, 229.99, 0, "LGA 1700", "", "0"));


        storageOptions.add(storageFactory.createComponent("Samsung", "870 EVO", "Productivity",0, 74.99, 0, "", "SSD", "1TB"));
        storageOptions.add(storageFactory.createComponent("AORUS", "GEN4 5000E", "Gaming",0, 89.99, 0, "", "SSD", "2TB"));
        storageOptions.add(storageFactory.createComponent("Kingston", "A400", "Gaming",0, 24.49, 0, "", "SSD", "256GB"));


        for (Component cpu : cpuOptions) {
            if (cpu.getCategory().equalsIgnoreCase(buildType)) {
                filteredCpuOptions.add(cpu);
            }
        }

        for (Component gpu : gpuOptions) {
            if (gpu.getCategory().equalsIgnoreCase(buildType)) {
                filteredGpuOptions.add(gpu);
            }
        }

        // Ask the user to select one CPU
        System.out.println("Select one CPU:");
        for (int i = 0; i < filteredCpuOptions.size(); i++) {
            System.out.println(i + 1 + ". " + filteredCpuOptions.get(i).getName());
        }

        int selectedCpuIndex = getValidInput(1, filteredCpuOptions.size(), "Enter the number corresponding to your choice:", scanner) - 1;
        Component selectedCpu = filteredCpuOptions.get(selectedCpuIndex);

        for (Component mobo : moboOptions) {
            if (selectedCpu.getSocket().equals(mobo.getSocket())) {
                compatibleMoboOptions.add(mobo);
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
        for (int i = 0; i < filteredGpuOptions.size(); i++) {
            System.out.println(i + 1 + ". " + filteredGpuOptions.get(i).getName());
        }

        int selectedGpuIndex = getValidInput(1, filteredGpuOptions.size(), "Enter the number corresponding to your choice:", scanner) - 1;
        Component selectedGpu = filteredGpuOptions.get(selectedGpuIndex);

        System.out.println("Select one Storage:");
        for (int i = 0; i < storageOptions.size(); i++) {
            System.out.println(i + 1 + ". " + storageOptions.get(i).getName() + storageOptions.get(i).getStorageSize());
        }
        int selectedStorageIndex = getValidInput(1, storageOptions.size(), "Enter the number corresponding to your choice:", scanner) - 1;
        Component selectedStorage = storageOptions.get(selectedStorageIndex);

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
        configuration.addComponent((selectedStorage));

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
        printComputerASCIIArt(configuration);
    }
}
