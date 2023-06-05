package cart.dao;

import cart.entity.ProductEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private static final RowMapper<ProductEntity> MAPPER = (resultSet, rowNum) -> new ProductEntity(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("image_url")
    );

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertAction;

    public ProductDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertAction = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("product")
                .usingColumns("name", "price", "image_url")
                .usingGeneratedKeyColumns("id");
    }

    public List<ProductEntity> findAll() {
        final String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            final long productId = rs.getLong("id");
            final String name = rs.getString("name");
            final int price = rs.getInt("price");
            final String imageUrl = rs.getString("image_url");
            return new ProductEntity(productId, name, price, imageUrl);
        });
    }

    public ProductEntity findById(final long id) {
        final String sql = "SELECT * FROM product WHERE id = ?";
        final List<ProductEntity> productEntities = jdbcTemplate.query(sql, MAPPER, id);
        return productEntities.isEmpty() ? null : productEntities.get(0);
    }

    public long createProduct(final ProductEntity productEntity) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", productEntity.getName());
        params.put("price", productEntity.getPrice());
        params.put("image_url", productEntity.getImageUrl());
        return insertAction.executeAndReturnKey(params).longValue();
    }

    public void updateProduct(final Long id, final ProductEntity productEntity) {
        final String sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, productEntity.getName(), productEntity.getPrice(), productEntity.getImageUrl(), id);
    }

    public void deleteProduct(final Long id) {
        final String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
