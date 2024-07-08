// Базовый класс ГорячийНапиток
class HotBeverage {
    private String name;
    private int volume;

    public HotBeverage(String name, int volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "HotBeverage{" +
                "name='" + name + '\'' +
                ", volume=" + volume +
                '}';
    }
}

// Наследник класса ГорячийНапиток с дополнительным полем температура
class HotBeverageWithTemperature extends HotBeverage {
    private int temperature;

    public HotBeverageWithTemperature(String name, int volume, int temperature) {
        super(name, volume);
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "HotBeverageWithTemperature{" +
                "name='" + getName() + '\'' +
                ", volume=" + getVolume() +
                ", temperature=" + temperature +
                '}';
    }
}

// Интерфейс ТорговыйАвтомат
interface VendingMachine {
    HotBeverage getProduct(String name, int volume, int temperature);
}

// Класс ГорячихНапитковАвтомат, реализующий интерфейс ТорговыйАвтомат
class HotBeverageVendingMachine implements VendingMachine {
    private List<HotBeverageWithTemperature> products = new ArrayList<>();

    public void addProduct(HotBeverageWithTemperature product) {
        products.add(product);
    }

    @Override
    public HotBeverage getProduct(String name, int volume, int temperature) {
        for (HotBeverageWithTemperature product : products) {
            if (product.getName().equals(name) && product.getVolume() == volume && product.getTemperature() == temperature) {
                return product;
            }
        }
        return null; // Продукт не найден
    }
}

// Главный класс для демонстрации работы
public class Main {
    public static void main(String[] args) {
        HotBeverageVendingMachine machine = new HotBeverageVendingMachine();

        HotBeverageWithTemperature coffee = new HotBeverageWithTemperature("Coffee", 250, 80);
        HotBeverageWithTemperature tea = new HotBeverageWithTemperature("Tea", 200, 75);
        HotBeverageWithTemperature hotChocolate = new HotBeverageWithTemperature("Hot Chocolate", 300, 85);

        machine.addProduct(coffee);
        machine.addProduct(tea);
        machine.addProduct(hotChocolate);

        HotBeverage product = machine.getProduct("Tea", 200, 75);
        if (product != null) {
            System.out.println("Product found: " + product);
        } else {
            System.out.println("Product not found");
        }
    }
}
