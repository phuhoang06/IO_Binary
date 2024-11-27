package bt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManager {
    private static final String FILE_NAME = "products.dat";
    private List<Product> products = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // Thêm sản phẩm
    public void addProduct() {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Clear buffer
        System.out.print("Enter manufacturer: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Product product = new Product(id, name, price, manufacturer, description);
        products.add(product);
        System.out.println("Product added successfully!");
    }

    // Hiển thị danh sách sản phẩm
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Tìm kiếm sản phẩm theo ID
    public void searchProduct() {
        System.out.print("Enter product ID to search: ");
        String id = scanner.nextLine();
        boolean found = false;
        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(id)) {
                System.out.println(product);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found!");
        }
    }

    // Lưu sản phẩm ra file nhị phân
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(products);
            System.out.println("Products saved to file successfully!");
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    // Đọc sản phẩm từ file nhị phân
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            products = (List<Product>) ois.readObject();
            System.out.println("Products loaded from file successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty list.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading from file: " + e.getMessage());
        }
    }

    // Menu chính
    public void menu() {
        int choice;
        do {
            System.out.println("\n--- Product Manager ---");
            System.out.println("1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Search Product");
            System.out.println("4. Save to File");
            System.out.println("5. Load from File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> displayProducts();
                case 3 -> searchProduct();
                case 4 -> saveToFile();
                case 5 -> loadFromFile();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}
