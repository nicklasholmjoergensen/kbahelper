package io.peqo.kbahelper.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Class modelling the department requesting the bloodwork.
 */

@Entity
public class Requestor {

    @Id(autoincrement = true)
    private Long id;

    private String name;
    private String department;
    private String address;
    private String postalCode;
    private String country;

    public Requestor() {}

    @Generated(hash = 2119932030)
    public Requestor(Long id, String name, String department, String address,
            String postalCode, String country) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
