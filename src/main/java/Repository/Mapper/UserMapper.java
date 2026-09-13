package Repository.Mapper;

import Domain.UserAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserAccount> {

    @Override
    public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserAccount(
                rs.getString("username"),
                null,
                rs.getString("role"),
                rs.getBoolean("enabled")
        );
    }
}
