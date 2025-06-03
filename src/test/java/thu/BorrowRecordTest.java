package test.java.thu;

import model.BorrowRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowRecordTest {

    @Test
    public void testConstructorAndGetters() {
        BorrowRecord record = new BorrowRecord("1", "B100", "R200", "2025-06-01", "2025-06-10");
        assertEquals("1", record.getId());
        assertEquals("B100", record.getBookId());
        assertEquals("R200", record.getReaderId());
        assertEquals("2025-06-01", record.getBorrowDate());
        assertEquals("2025-06-10", record.getReturnDate());
    }

    @Test
    public void testSetters() {
        BorrowRecord record = new BorrowRecord("2", "B101", "R201", "2025-06-02", "2025-06-12");
        record.setBorrowDate("2025-06-03");
        record.setReturnDate("2025-06-13");
        assertEquals("2025-06-03", record.getBorrowDate());
        assertEquals("2025-06-13", record.getReturnDate());
    }

    @Test
    public void testToString() {
        BorrowRecord record = new BorrowRecord("3", "B102", "R202", "2025-06-04", "2025-06-14");
        String expected = "BorrowRecord{ID='3', bookId='B102', readerId='R202', borrowDate='2025-06-04', returnDate='2025-06-14'}";
        assertEquals(expected, record.toString());
    }
}
