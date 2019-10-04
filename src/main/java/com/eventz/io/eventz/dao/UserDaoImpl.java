package com.eventz.io.eventz.dao;

import com.eventz.io.eventz.models.request.AuthRequest;
import com.eventz.io.eventz.models.response.User;
import com.eventz.io.eventz.utils.RowCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by Michael.Akobundu on 4/4/2019.
 */
@Repository
public class UserDaoImpl extends AbstractBaseDao<User> implements UserDao {

    private SimpleJdbcCall authenticateUser, findUserByEmail, findUserByPhoneNumber, findUserByUsername;

    @Override
    @Autowired
    public void setDataSource(@Qualifier(value = "dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("create_user").withReturnValue();
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("update_user").withReturnValue();
    }

    @Override
    @Autowired
    public void setReadOnlyDataSource(@Qualifier(value = "readOnlyDataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        find = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_user")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(User.class));
        findAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_all_users").returningResultSet(RESULT_COUNT, new RowCountMapper())
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(User.class));
        findUserByEmail = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_user_by_email")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(User.class));
        findUserByPhoneNumber = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_user_by_phone_number")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(User.class));
        findUserByUsername = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_user_by_username")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(User.class));
        authenticateUser = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_user_by_username_password").returningResultSet(RESULT_COUNT, new RowCountMapper())
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(User.class));
    }

    public User authenticate(String username, String password) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("user_name", username)
                .addValue("password", password);
        Map<String, Object> m = authenticateUser.execute(in);
        List<User> list = (List<User>) m.get(SINGLE_RESULT);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public User findUserByEmail(String email) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("email", email);
        Map<String, Object> m = findUserByEmail.execute(in);
        List<User> list = (List<User>) m.get(SINGLE_RESULT);
        if(list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public User findUserByPhoneNumber(String phoneNumber) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("phone_number", phoneNumber);
        Map<String, Object> m = findUserByPhoneNumber.execute(in);
        List<User> list = (List<User>) m.get(SINGLE_RESULT);
        if(list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public User findUserByUsername(String username) {
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("phone_number", username);
        Map<String, Object> m = findUserByUsername.execute(in);
        List<User> list = (List<User>) m.get(SINGLE_RESULT);
        if(list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
