package com.winning.winningauthenticationtest.ctrl;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lmx
 * @date 2020-06-13 11:16
 */
@RestController
@RequestMapping(value = "/query")
public class QueryController {

    @Autowired
    DataSource dataSource;

    @GetMapping("/users")
    String userList() {
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(
                    "select username from users"
            );
            ResultSet resultSet = ps.executeQuery();

            JSONArray jarr = new JSONArray();
            while (resultSet.next()) {
                jarr.add(resultSet.getString(1));
            }
            return jarr.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "fail";
    }

}
