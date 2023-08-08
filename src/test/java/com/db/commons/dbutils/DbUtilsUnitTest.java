package com.db.commons.dbutils;

import com.google.common.collect.Lists;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class DbUtilsUnitTest {

    private static final String db = "jdbc:h2:mem:;INIT=runscript from 'classpath:/employees.sql'";

    private Connection connection;


    @Before
    public void setupDB() throws Exception {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(db);
    }

    @After
    public void closeBD() {
        DbUtils.closeQuietly(connection);
    }


    private DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl(db);
        return ds;
    }


    @Test
    public void testWithDataSource() throws SQLException {
        MapListHandler beanListHandler = new MapListHandler();
        DataSource dataSource = getDataSource();
        QueryRunner runner = new QueryRunner(dataSource);
        List<Map<String, Object>> list = runner.query("SELECT * FROM employee", beanListHandler);

        System.out.println(list);

        assertEquals(list.size(), 5);
        assertEquals(list.get(0).get("firstname"), "John");
        assertEquals(list.get(4).get("firstname"), "Christian");
    }

    @Test
    public void testBatchInsertRecords() throws SQLException {
        ScalarHandler<Integer> scalarHandler = new ScalarHandler<>();

        Employee employee1 = new Employee();
        employee1.setFirstName("FN1");
        employee1.setLastName("LN1");
        employee1.setHiredDate(new Date());
        employee1.setSalary(33.0);

        Employee employee2 = new Employee();
        employee2.setFirstName("FN2");
        employee2.setLastName("LN2");
        employee2.setHiredDate(new Date());
        employee2.setSalary(66.0);

        ArrayList<Employee> employees = Lists.newArrayList(employee1, employee2);

        Object[][] params = new Object[2][4];
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(0);
            params[i] = new Object[]{employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getHiredDate()};
        }


        QueryRunner runner = new QueryRunner(getDataSource());
        String insertSQL = "INSERT INTO employee (firstname,lastname,salary, hireddate) VALUES (?, ?, ?, ?)";

        int newId = runner.insertBatch(insertSQL, scalarHandler, params);

        BeanListHandler<Employee> listHandler = new BeanListHandler<>(Employee.class);
        List<Employee> employeeList = runner.query("SELECT * FROM employee", listHandler);

        System.out.println(employeeList);

        assertEquals(employeeList.size(), 7);
        assertEquals(newId, 6);
    }

    @Test
    public void testBatchInsert() throws SQLException {
        ScalarHandler<Integer> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner(getDataSource());
        String insertSQL = "INSERT INTO employee (firstname,lastname,salary, hireddate) VALUES (?, ?, ?, ?)";

        int newId = runner.insertBatch(insertSQL, scalarHandler, new Object[][]{{"Jenny", "Medici", 60000.60, new Date()}});

        assertEquals(newId, 6);
    }

    @Test
    public void givenResultHandler_whenExecutingQuery_thenExpectedList() throws SQLException {
        MapListHandler beanListHandler = new MapListHandler();

        QueryRunner runner = new QueryRunner();
        List<Map<String, Object>> list = runner.query(connection, "SELECT * FROM employee", beanListHandler);

        System.out.println(list);

        assertEquals(list.size(), 5);
        assertEquals(list.get(0).get("firstname"), "John");
        assertEquals(list.get(4).get("firstname"), "Christian");
    }

    @Test
    public void givenResultHandler_whenExecutingQuery_thenEmployeeList() throws SQLException {
        BeanListHandler<Employee> beanListHandler = new BeanListHandler<>(Employee.class);

        QueryRunner runner = new QueryRunner();
        List<Employee> employeeList = runner.query(connection, "SELECT * FROM employee", beanListHandler);

        System.out.println(employeeList);

        assertEquals(employeeList.size(), 5);
        assertEquals(employeeList.get(0).getFirstName(), "John");
        assertEquals(employeeList.get(4).getFirstName(), "Christian");
    }

    @Test
    public void givenResultHandler_whenExecutingQuery_thenExpectedScalar() throws SQLException {
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner();
        String query = "SELECT COUNT(*) FROM employee";
        long count = runner.query(connection, query, scalarHandler);

        assertEquals(count, 5);
    }

    @Test
    public void givenResultHandler_whenExecutingQuery_thenEmailsSetted() throws SQLException {
        EmployeeHandler employeeHandler = new EmployeeHandler(connection);

        QueryRunner runner = new QueryRunner();
        List<Employee> employees = runner.query(connection, "SELECT * FROM employee", employeeHandler);

        System.out.println(employees);

        assertEquals(employees.get(0).getEmails().size(), 2);
        assertEquals(employees.get(2).getEmails().size(), 3);
    }

    @Test
    public void givenResultHandler_whenExecutingQuery_thenAllPropertiesSetted() throws SQLException {
        EmployeeHandler employeeHandler = new EmployeeHandler(connection);

        QueryRunner runner = new QueryRunner();
        String query = "SELECT * FROM employee_legacy";
        List<Employee> employees = runner.query(connection, query, employeeHandler);

        assertEquals((int) employees.get(0).getId(), 1);
        assertEquals(employees.get(0).getFirstName(), "John");
    }

    @Test
    public void whenInserting_thenInserted() throws SQLException {
        QueryRunner runner = new QueryRunner();
        String insertSQL = "INSERT INTO employee (firstname,lastname,salary, hireddate) VALUES (?, ?, ?, ?)";

        int numRowsInserted = runner.update(connection, insertSQL, "Leia", "Kane", 60000.60, new Date());

        assertEquals(numRowsInserted, 1);
    }

    @Test
    public void givenHandler_whenInserting_thenExpectedId() throws SQLException {
        ScalarHandler<Integer> scalarHandler = new ScalarHandler<>();

        QueryRunner runner = new QueryRunner();
        String insertSQL = "INSERT INTO employee (firstname,lastname,salary, hireddate) VALUES (?, ?, ?, ?)";

        int newId = runner.insert(connection, insertSQL, scalarHandler, "Jenny", "Medici", 60000.60, new Date());

        assertEquals(newId, 6);
    }

    @Test
    public void givenSalary_whenUpdating_thenUpdated() throws SQLException {
        double salary = 35000;

        QueryRunner runner = new QueryRunner();
        String updateSQL = "UPDATE employee SET salary = salary * 1.1 WHERE salary <= ?";
        int numRowsUpdated = runner.update(connection, updateSQL, salary);

        assertEquals(numRowsUpdated, 3);
    }

    @Test
    public void whenDeletingRecord_thenDeleted() throws SQLException {
        QueryRunner runner = new QueryRunner();
        String deleteSQL = "DELETE FROM employee WHERE id = ?";
        int numRowsDeleted = runner.update(connection, deleteSQL, 3);

        assertEquals(numRowsDeleted, 1);
    }

    @Test
    public void givenAsyncRunner_whenExecutingQuery_thenExpectedList() throws Exception {
        AsyncQueryRunner runner = new AsyncQueryRunner(Executors.newCachedThreadPool());

        EmployeeHandler employeeHandler = new EmployeeHandler(connection);
        String query = "SELECT * FROM employee";
        Future<List<Employee>> future = runner.query(connection, query, employeeHandler);
        List<Employee> employeeList = future.get(10, TimeUnit.SECONDS);

        assertEquals(employeeList.size(), 5);
    }

}