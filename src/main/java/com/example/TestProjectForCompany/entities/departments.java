package com.example.TestProjectForCompany.entities;

import lombok.Data;


@Data
public class departments {
    private Long dpID;
    private String dpname;

    public departments(Long dpID, String dpname) {
        this.dpID = dpID;
        this.dpname = dpname;
    }

    public departments() {
    }
}
