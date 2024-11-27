package bt;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        manager.loadFromFile(); // Load dữ liệu từ file nếu tồn tại
        manager.menu();         // Hiển thị menu
    }
}
