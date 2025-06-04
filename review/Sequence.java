public class Sequence {
    private Object[] objects; // Mảng chứa các đối tượng
    private int next = 0;     // Vị trí tiếp theo sẽ thêm phần tử

    // Constructor: khởi tạo mảng với kích thước cho trước
    public Sequence(int size) {
        objects = new Object[size];
    }

    // Thêm phần tử vào mảng
    public void add(Object x) {
        if (next < objects.length) {
            objects[next] = x;
            next++;
        }
    }

    // Trả về toàn bộ mảng đã lưu
    public Object[] getSelector() {
        return objects;
    }
}
