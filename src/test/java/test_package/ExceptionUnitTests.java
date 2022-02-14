package test_package;

import org.junit.jupiter.api.Test;

import management.error_handler.ResourceNotFoundException;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionUnitTests {

    @Test
    void individualExceptionMessage() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Test Message");
        String response = exception.getMessage();
        assertEquals("Test Message", response);
    }

    @Test
    void setMessageErrorDetails(){
        Date date = new Date(20121212);
        ErrorDetails info = new ErrorDetails(date, "Test Message", "some more description");
        info.setMessage("This is a new message");
        assertEquals("This is a new message", info.getMessage());
    }

    @Test
    void setDetailsErrorDetails(){
        Date date = new Date(20121212);
        ErrorDetails info = new ErrorDetails(date, "Test Message", "some more description");
        info.setDetails("These are new details");
        assertEquals("These are new details", info.getDetails());
    }

    @Test
    void setTimestampErrorDetails(){
        Date date = new Date(20121212);
        Date new_date = new Date(20201212);
        ErrorDetails info = new ErrorDetails(date, "Test Message", "some more description");
        info.setTimestamp(new_date);
        assertEquals(new_date, info.getTimestamp());
    }
}