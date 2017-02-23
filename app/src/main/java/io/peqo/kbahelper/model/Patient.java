package io.peqo.kbahelper.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Class for representing a patient.
 */

@Entity
public class Patient {

    @Id(autoincrement = true)
    private Long id;

    private String firstName;
    private String lastName;
    private int customerNum;
    private String cprNum;

    public Patient() {}

    @Generated(hash = 391941295)
    public Patient(Long id, String firstName, String lastName, int customerNum,
            String cprNum) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerNum = customerNum;
        this.cprNum = cprNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }

    public String getCprNum() {
        return cprNum;
    }

    public void setCprNum(String cprNum) {
        this.cprNum = cprNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerNum=" + customerNum +
                ", cprNum='" + cprNum + '\'' +
                '}';
    }
}
