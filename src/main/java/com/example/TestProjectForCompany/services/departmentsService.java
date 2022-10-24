package com.example.TestProjectForCompany.services;

import com.example.TestProjectForCompany.entities.departments;
import com.example.TestProjectForCompany.exceptions.dbException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class departmentsService {

    private final JdbcTemplate jdbcTemplate;

    public departmentsService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<departments> getAll() {
        String sql = "SELECT * FROM tblDepartments";
        List<departments> departments = jdbcTemplate.query(
                sql, new ResultSetExtractor<List<departments>>() {
                    @Override
                    public List<departments> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<departments> departments = new ArrayList<>();
                        while (rs.next()) {
                            departments e = new departments();
                            e.setDpID(rs.getLong(1));
                            e.setDpname(rs.getString(2));
                            departments.add(e);
                        }
                        return departments;
                    }
                });
        return departments;
    }


    public departments getById(Long id) {
        String sql = "SELECT * FROM tblDepartments where dpID = " + id;
        departments departments = jdbcTemplate.queryForObject(sql, new RowMapper<departments>() {
            @Override
            public departments mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new departments(rs.getLong(1), rs.getString(2));
            }
        });
        return departments;
    }

    public departments save(departments departments) throws dbException {
        if (departments.getDpID() == null)//insert
        {
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            insert.withTableName("tblDepartments");
            insert.usingGeneratedKeyColumns("dpID");
            insert.usingColumns("dpName");
            Map<String, Object> values = new HashMap<>();
            values.put("dpName", departments.getDpname());
            Long key = insert.executeAndReturnKey(values).longValue();
            return new departments(key, departments.getDpname());
        } else {//update
            String sql = "UPDATE tblDepartments SET dpName= ? WHERE dpID= ?";
            int countOfRow = jdbcTemplate.update(sql, departments.getDpname(), departments.getDpID());
            if (countOfRow == 1) {
                return departments;
            } else {
                throw new dbException("Cannot update data");
            }
        }
    }


    public departments delete(Long id) throws dbException {
        String sql = "Delete from tblDepartments where dpID = ?";
        departments deleteDepartments = getById(id);
        int deleteRow = jdbcTemplate.update(sql, id);
        if (deleteRow == 1) {
            return deleteDepartments;
        } else {
            throw new dbException("Cannot delete data");
        }
    }


}
