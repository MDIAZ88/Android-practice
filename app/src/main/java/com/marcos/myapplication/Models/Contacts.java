package com.marcos.myapplication.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Table;
import com.activeandroid.annotation.Column;

@Table(name = "Contacts")
public class Contacts extends Model {

    @Column(name = "Name")
    private String name;
    @Column(name = "Celphone")
    private String celphone;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Address")
    private String address;
    @Column(name = "Birthday")
    private String birthday;
    @Column(name = "Email")
    private String email;
    @Column(name = "Cgroup")
    private String cgroup;
    @Column(name = "User")
    private String user;

    public Contacts(){

    }

    public Contacts(String name, String celphone, String phone, String address, String birthday,
                    String email, String cgroup, String user) {
        this.name = name;
        this.celphone = celphone;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.cgroup = cgroup;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCelphone() {
        return celphone;
    }

    public void setCelphone(String celphone) {
        this.celphone = celphone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCgroup() {
        return cgroup;
    }

    public void setCgroup(String cgroup) {
        this.cgroup = cgroup;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
