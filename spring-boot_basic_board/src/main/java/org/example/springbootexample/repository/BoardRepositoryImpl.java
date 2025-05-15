package org.example.springbootexample.repository;

import org.example.springbootexample.model.Board;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class BoardRepositoryImpl implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;
    public BoardRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Board> boardRowMapper = (rs, rowNum) -> {
        Board board = new Board();
        board.setId(rs.getLong("id"));
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setUserId(rs.getLong("user_id"));
        board.setViewCount(rs.getInt("view_count"));
        board.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        board.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        // username이 조인 결과로 올 경우
        try {
            board.setUsername(rs.getString("username"));
        } catch (Exception ignored) {
        }

        return board;
    };

    @Override
    public List<Board> findAll(int offset, int pageSize) {
        String sql = "select b.*, u.username from boards b join users u on b.user_id = u.id order by created_at desc limit ? offset ?";
        return jdbcTemplate.query(sql, boardRowMapper, pageSize, offset);
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from boards", Integer.class);
    }

    @Override
    public Board save(Board board) {
        // insert, update
        // insert 기존에 board id 존재
        // update 기존에 board id 존재 x
        if(board.getId()==null){
            return insertBoard(board);
        }else{
            return updateBoard(board);
        }

    }

    private Board insertBoard(Board board){
        String sql = "insert into boards(title, content, user_id, view_count, created_at, updated_at) values(?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, board.getTitle());
            ps.setString(2, board.getContent());
            ps.setLong(3, board.getUserId());
            ps.setInt(4, board.getViewCount());
            ps.setTimestamp(5, Timestamp.valueOf(now));
            ps.setTimestamp(5, Timestamp.valueOf(now));
            return ps;
        }, keyHolder);

        // 키 추출 (H2 DB는 keys로 내려옴)
        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null) {
            Object idObj = keys.getOrDefault("ID", keys.get("id"));
            if (idObj instanceof Number) {
                board.setId(((Number) idObj).longValue());
            } else {
                throw new IllegalStateException("Failed to retrieve generated board ID");
            }
        } else {
            throw new IllegalStateException("Failed to retrieve generated board key");
        }

        board.setViewCount(0);
        board.setCreatedAt(now);
        board.setCreatedAt(now);

        return board;
    }

    private Board updateBoard(Board board){
        String sql = "update boards set title = ?, content = ?, updated_at = ? where id = ?";
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update(sql,
                board.getTitle(),
                board.getContent(),
                Timestamp.valueOf(now),
                board.getId());
        board.setUpdatedAt(now);
        return board;
    }

    @Override
    public Board findById(Long id) {
        String sql = "select b.*, u.username from boards b join users u on b.user_id = u.id where id = ?";

        Board board = jdbcTemplate.queryForObject(sql, boardRowMapper, id);

        return board;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "delete from boards where id = ?";
        int result = jdbcTemplate.update(sql, id);
        return result>0;
    }

    @Override
    public void incrementViewCount(Long id) {
        String sql = "update boards set view_count = view_count + 1 where id = ?";
        jdbcTemplate.update(sql, id);
    }

}