package com.example.TestProjectForCompany.entities;

import lombok.Data;



@Data
public class employees {
    private Long empID;
    private String empname;
    private Boolean empactive;
    private departments emp_dpID = new departments();

    public employees(Long empID, String empname, Boolean empactive, departments emp_dpID) {
        this.empID = empID;
        this.empname = empname;
        this.empactive = empactive;
        this.emp_dpID = emp_dpID;
    }

    public employees() {

    }
}
