package cart.persistence.dao;

import cart.persistence.entity.MemberEntity;
import cart.exception.AuthenticationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AuthDao {
    private static final RowMapper<MemberEntity> MAPPER = (resultSet, rowNum) -> new MemberEntity(
            resultSet.getLong("id"),
            resultSet.getString("grade"),
            resultSet.getString("email"),
            resultSet.getString("password")
    );

    private final JdbcTemplate jdbcTemplate;

    public AuthDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MemberEntity findByEmail(final String email) {
        final String sql = "SELECT * FROM member WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, MAPPER, email);
        } catch (EmptyResultDataAccessException e) {
            throw new AuthenticationException();
        }
    }
}
