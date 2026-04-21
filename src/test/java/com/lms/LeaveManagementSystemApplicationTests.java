package com.lms;

import org.junit.Test;
import static org.junit.Assert.*;
import com.lms.models.UserInfo;
import com.lms.models.LeaveDetails;
import java.util.Date;

public class LeaveManagementSystemApplicationTests {

    // Test 1: test UserInfo's setter/getter
    @Test
    public void testUserInfoSettersGetters() {
        UserInfo user = new UserInfo();
        user.setId(1);
        user.setEmail("manager@email.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setRole("MANAGER");
        user.setActive(true);
        
        assertEquals(1, user.getId());
        assertEquals("manager@email.com", user.getEmail());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("MANAGER", user.getRole());
        assertTrue(user.isActive());
    }
    
    // Test 2: test UserInfo default values
    @Test
    public void testUserInfoDefaultValues() {
        UserInfo user = new UserInfo();
        assertNotNull(user);
        assertFalse(user.isActive()); // boolean 默认 false
    }
    
    // Test 3: test LeaveDetails setter/getter
    @Test
    public void testLeaveDetailsSettersGetters() {
        LeaveDetails leave = new LeaveDetails();
        leave.setId(100);
        leave.setLeaveType("Annual");
        leave.setReason("Family vacation");
        leave.setDuration(5);
        leave.setActive(true);
        
        assertEquals(100, leave.getId());
        assertEquals("Annual", leave.getLeaveType());
        assertEquals("Family vacation", leave.getReason());
        assertEquals(5, leave.getDuration());
        assertTrue(leave.isActive());
    }
    
    // Test 4: test LeaveDetails date fields
    @Test
    public void testLeaveDetailsWithDates() {
        LeaveDetails leave = new LeaveDetails();
        Date startDate = new Date();
        leave.setFromDate(startDate);
        
        assertNotNull(leave.getFromDate());
        assertEquals(startDate, leave.getFromDate());
    }
    
    // Test 5: test UserInfo password setting
    @Test
    public void testUserInfoPassword() {
        UserInfo user = new UserInfo();
        user.setPassword("123456");
        assertNotNull(user.getPassword());
        assertTrue(user.getPassword().length() >= 5);
    }
}