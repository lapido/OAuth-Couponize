package com.couponize.Auth.dao.impl;

import com.couponize.Auth.dao.AbstractBaseDao;
import com.couponize.Auth.dao.UserDao;
import com.couponize.Auth.dao.util.RowCountMapper;
import com.couponize.Auth.model.User;
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
@Repository
public class UserDaoImpl extends AbstractBaseDao<User> implements UserDao {
    protected SimpleJdbcCall findByCode;

    @Autowired
    @Override
    public void setDataSource(@Qualifier(value = "dataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        create = new SimpleJdbcCall(dataSource).withProcedureName("uspCreateUser").withReturnValue();
        update = new SimpleJdbcCall(jdbcTemplate).withProcedureName("uspCreateUser").withReturnValue();
        find = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_agent")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(User.class));
        findAll = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_all_users").returningResultSet(RESULT_COUNT, new RowCountMapper())
                .returningResultSet(MULTIPLE_RESULT, new BeanPropertyRowMapper<>(User.class));
        findByCode = new SimpleJdbcCall(jdbcTemplate).withProcedureName("find_users_by_email")
                .returningResultSet(SINGLE_RESULT, new BeanPropertyRowMapper<>(User.class));
    }

    public User findByEmail(String agentCode) {
        SqlParameterSource in = new MapSqlParameterSource().addValue("code", agentCode);
        Map<String, Object> m = findByCode.execute(in);
        List<User> list = (List<User>) m.get(SINGLE_RESULT);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

}
