## Project nhóm 16

## Thành viên trong  nhóm
- Nguyễn Nhật Minh 23010847 
- Phạm Văn Minh 23010550 
- Phạm Ngọc Tiến 23010010

## Mô tả đối tượng
1. Book – Quản lý thông tin sách
- Thuộc tính: id, title, author
- Phương thức: toString, getId, setTitle, setAuthor, v.v.
2. Reader – Người mượn sách
- Thuộc tính: id, name, phone, cccd, birthDate
- Phương thức: toString, getId, setName, setPhone, v.v.
3. User – Tài khoản đăng nhập
- Thuộc tính: username, password
- Phương thức: getUsername, getPassword, setPassword, v.v.
4. BorrowRecord – Phiếu mượn sách
- Thuộc tính: id, bookId, readerId, borrowDate, returnDate
- Phương thức: toString, getId, setBorrowDate, setReturnDate, v.v.

## Xây dựng ứng dụng: LibraryManagement (Hệ thống quản lý thư viện)

- Giao diện: Java Spring Boot
- Lưu trữ dữ liệu: File nhị phân .data sử dụng ObjectOutputStream và ObjectInputStream.
- Chức năng quản lý:
1. Quản lý sách
2. Quản lý người mượn
3. Quản lý tài khoản người dùng
4. Quản lý phiếu mượn
## Cụ thể
1 Quản lý sách
+ Thêm, sửa, xoá sách
+ Hiển thị danh sách sách
  
2 Quản lý người mượn
+ Thêm, sửa, xoá người mượn
+ Hiển thị danh sách người mượn
  
3 Quản lý tài khoản
+ Đăng ký người dùng mới
+ Đăng nhập hệ thống
+ Cập nhật, xoá tài khoản
  
4 Quản lý phiếu mượn
+ Thêm, sửa, xoá phiếu mượn sách
+ Hiển thị danh sách phiếu mượn

## Các đặc điểm nổi bật
- Hệ thống lưu trữ dữ liệu cục bộ qua file
- Đăng nhập, đăng ký an toàn
- Menu điều khiển dễ sử dụng
- Dữ liệu tự động lưu lại sau mỗi thao tác

## Link Project Repo
[GitHub Repo](https://github.com/nguxnnminh/OOP_N01_Term3_2025_K17_Group16)

## Link README
[README.md](https://github.com/nguxnnminh/OOP_N01_Term3_2025_K17_Group16/edit/main/README.md)

![activitydiagram](https://github.com/user-attachments/assets/4a5eb344-b6cc-4f95-98fd-dfa8e37559dc)

![usecase](https://github.com/user-attachments/assets/fb57b19f-a93e-438b-a3d9-6b95259525ea)

## ✅ Chức năng chính nhóm thực hiện:

### Thống kê danh sách sách đang được mượn và hiển thị chi tiết

#### Các bước thực hiện:

1. Lấy toàn bộ danh sách phiếu mượn
2. Kiểm tra ngày trả > ngày hiện tại => đang được mượn
3. Thêm vào danh sách hiển thị
4. In tổng số lượng và thông tin sách + người mượn

#### Phân công công việc:

- **Sinh viên A**: Lọc các phiếu mượn chưa đến hạn trả
- **Sinh viên B**: Lấy thông tin sách và bạn đọc từ phiếu mượn
- **Sinh viên C**: Tính tổng số sách đang mượn, in báo cáo
- **Cả nhóm**: Kiểm thử, tạo lưu đồ, viết README

#### Lưu đồ thuật toán:
![luudo](https://github.com/user-attachments/assets/af41dc49-9b7d-40bb-919f-4ba40c68c2f8)
