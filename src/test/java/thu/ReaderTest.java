package thu;

import model.Reader;

public class ReaderTest {
    public static void main(String[] args) {
        Reader reader = null;

        try {
            reader = new Reader("R001", "Nguyen Van A", "123456789", "0987654321", "01/01/2000");

            System.out.println("=== Kiem thu lop Reader ===");
            System.out.println("Ma: " + reader.getId());
            System.out.println("Ten: " + reader.getName());
            System.out.println("CCCD: " + reader.getCccd());
            System.out.println("So dien thoai: " + reader.getPhone());
            System.out.println("Ngay sinh: " + reader.getBirthDate());

        } catch (Exception e) {
            System.out.println("❌ Đã xảy ra lỗi khi kiểm thử Reader: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("✅ Kết thúc kiểm thử lớp Reader (khối finally).");
        }
    }
}
