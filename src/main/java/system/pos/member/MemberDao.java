package system.pos.member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class MemberDao {
	private Map<String, Member> map = new HashMap<>();
	private NamedParameterJdbcTemplate jdbcTemplate;
	private JdbcTemplate template;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.template = new JdbcTemplate(dataSource);
	}
	
	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from member",
		(ResultSet rs, int rowNum) -> {
			Member member = new Member( rs.getString("RANK"), rs.getString("NAME"),
			rs.getString("ID"), rs.getString("PASSWORD"), rs.getString("REGDATE"));
			return member;
		});
		System.out.println(results.get(0));
		return results;
	}
	
	public void insert(Member member) {
		Map<String, Object> params = new HashMap<String,Object>();
		
		params.put("id", member.getId());
		params.put("rank", member.getRank());
		params.put("password", member.getPassword());
		params.put("name", member.getName());
		params.put("regdate", member.getRegDate());
		
		String sql = "insert into member values" + "(:id, :rank, :password, :name, :regdate)";

		jdbcTemplate.update(sql, params);
		
	}
	
	public Member selectById(String id) {
		List<Member> results = template.query("select * from MEMBER where ID = ?",
		new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member member = new Member( rs.getString("RANK"), rs.getString("NAME"),
					rs.getString("ID"), rs.getString("PASSWORD"), rs.getString("REGDATE"));
					return member;
			}
		}, id);
		return results.isEmpty() ? null : results.get(0);
	}

	public void delete(Member member) {
		String id = member.getId();
		String sql = "delete from Member where id = ?";
		template.update(sql, id);
	}
}
