package thu;

import org.junit.jupiter.api.Test;

import com.example.model.BorrowRecord;

import static org.junit.jupiter.api.Assertions.*;

public class BorrowRecordTest {

    @Test
    public void testConstructorAndGetters() {
        try {
            BorrowRecord record = new BorrowRecord("1", "B1", "R1", "2023-01-01", "2023-01-10");
            assertEquals("1", record.getId());
            assertEquals("B1", record.getBookId());
            assertEquals("R1", record.getReaderId());
            assertEquals("2023-01-01", record.getBorrowDate());
            assertEquals("2023-01-10", record.getReturnDate());
        } catch (Exception e) {
            fail("Constructor or getter failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testSetters() {
        try {
            BorrowRecord record = new BorrowRecord("1", "B1", "R1", "2023-01-01", "2023-01-10");
            record.setId("2");
            record.setBookId("B2");
            record.setReaderId("R2");
            record.setBorrowDate("2023-02-01");
            record.setReturnDate("2023-02-10");

            assertEquals("2", record.getId());
            assertEquals("B2", record.getBookId());
            assertEquals("R2", record.getReaderId());
            assertEquals("2023-02-01", record.getBorrowDate());
            assertEquals("2023-02-10", record.getReturnDate());
        } catch (Exception e) {
            fail("Setter test failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testToCSV() {
        try {
            BorrowRecord record = new BorrowRecord("1", "B1", "R1", "2023-01-01", "2023-01-10");
            String expected = "1,B1,R1,2023-01-01,2023-01-10";
            assertEquals(expected, record.toCSV());
        } catch (Exception e) {
            fail("toCSV test failed with exception: " + e.getMessage());
        }
    }
}