package com.example.TestProjectForCompany.services;

import com.example.TestProjectForCompany.entities.employees;
import com.example.TestProjectForCompany.exceptions.dbException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CrossOrigin
public class employeesService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<employees> getAll() {
            String sql = "SELECT empID,empName,empActive,emp_dpID,dpName FROM tblEmployees e join tblDepartments d on e.emp_dpID = d.dpID;";
        List<employees> customers = jdbcTemplate.query(
                sql, new ResultSetExtractor<List<employees>>() {
                    @Override
                    public List<employees> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<employees> employees = new ArrayList<>();
                        while (rs.next()) {
                            employees e = new employees();
                            e.setEmpID(rs.getLong(1));
                            e.setEmpname(rs.getString(2));
                            e.setEmpactive(rs.getBoolean(3));
                            e.getEmp_dpID().setDpID(rs.getLong(4));
                            e.getEmp_dpID().setDpname(rs.getString(5));
                            employees.add(e);
                        }
                        return employees;
                    }
                });
        return customers;
    }


    public employees getById( Long id) {
        String sql = "SELECT empID,empName,empActive,emp_dpID,dpName FROM tblEmployees e join tblDepartments d on e.emp_dpID = d.dpID where empID = " + id;
        employees employees = jdbcTemplate.queryForObject(sql, new RowMapper<com.example.TestProjectForCompany.entities.employees>() {
            @Override
            public com.example.TestProjectForCompany.entities.employees mapRow(ResultSet rs, int rowNum) throws SQLException {
                employees e = new employees();
                e.setEmpID(rs.getLong(1));
                e.setEmpname(rs.getString(2));
                e.setEmpactive(rs.getBoolean(3));
                e.getEmp_dpID().setDpID(rs.getLong(4));
                e.getEmp_dpID().setDpname(rs.getString(5));
                return e;
            }
        });
        return employees;
    }


    public employees save( employees employees1) throws dbException {
        if (employees1.getEmpID() == null)//insert
        {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            insert.withTableName("tblEmployees");
            insert.usingGeneratedKeyColumns("empID");
            insert.usingColumns("empName", "empActive", "emp_dpID");
            Map<String, Object> values = new HashMap<>();
            values.put("empName", employees1.getEmpname());
            values.put("empActive", employees1.getEmpactive());
            values.put("emp_dpID", employees1.getEmp_dpID().getDpID());
            Long key = insert.executeAndReturnKey(values).longValue();
            return new employees(key, employees1.getEmpname(), employees1.getEmpactive(), employees1.getEmp_dpID());
        } else {//update
            String sql = "UPDATE tblEmployees SET empName= ?, empActive = ?, emp_dpID = ? WHERE c= ?";
            int countOfRow = jdbcTemplate.update(sql, employees1.getEmpname(), employees1.getEmpactive(), employees1.getEmp_dpID().getDpID(), employees1.getEmpID());
            if (countOfRow == 1) {
                return employees1;
            } else {
                throw new dbException("Cannot update data");
            }
        }
    }


    public employees delete( Long id) throws dbException {
        String sql = "Delete from tblEmployees where empID = ?";
        employees deleteEmployee = getById(id);
        int deleteRow = jdbcTemplate.update(sql, id);
        if (deleteRow == 1) {
            return deleteEmployee;
        } else {
            throw new dbException("Cannot delete data");
        }
    }

    public List<employees> search(String partOfName) {
        String sql = "SELECT empID,empName,empActive,emp_dpID,dpName FROM tblEmployees e join tblDepartments d on e.emp_dpID = d.dpID where empName like\"%"+partOfName+"%\"";
        List<employees> customers = jdbcTemplate.query(
                sql, new ResultSetExtractor<List<employees>>() {
                    @Override
                    public List<employees> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<employees> employees = new ArrayList<>();
                        while (rs.next()) {
                            employees e = new employees();
                            e.setEmpID(rs.getLong(1));
                            e.setEmpname(rs.getString(2));
                            e.setEmpactive(rs.getBoolean(3));
                            e.getEmp_dpID().setDpID(rs.getLong(4));
                            e.getEmp_dpID().setDpname(rs.getString(5));
                            employees.add(e);
                        }
                        return employees;
                    }
                });
        return customers;
    }
}
