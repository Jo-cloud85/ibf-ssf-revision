package ibf2023.ibf.revision.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Login {

    @NotEmpty(message = "Username is mandatory")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
    private String username;

    @NotEmpty(message = "Gender cannot be null")
    private String gender;

    @Pattern(regexp = "(8|9)[0-9]{7}", message = "Invalid phone number entered")
    private String phoneNo;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date loginDate;

    public Login() {}

    public Login(String username, String gender, String phoneNo, Date loginDate) {
        this.username = username;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.loginDate = loginDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public String toString() {
        return "Login [username=" + username + ", gender=" + gender + ", phoneNo=" + phoneNo + ", loginDate="
                + loginDate + "]";
    }
}
