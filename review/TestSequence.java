public class TestSequence {
    public static void main(String[] args) {
        Sequence seq = new Sequence(5);  // Tạo Sequence có 5 phần tử

        // Thêm vài phần tử vào mảng
        seq.add("A");
        seq.add("B");
        seq.add("C");

        // In ra các phần tử đã lưu
        Object[] values = seq.getSelector();
        System.out.println("Các phần tử trong Sequence:");
        for (Object val : values) {
            if (val != null) {
                System.out.println(val);
            }
        }
    }
}
