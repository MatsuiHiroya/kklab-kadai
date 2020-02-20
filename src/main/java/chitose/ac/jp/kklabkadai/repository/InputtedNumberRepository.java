package chitose.ac.jp.kklabkadai.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InputtedNumberRepository implements IInputtedNumberRepository{

    // SpringJDBCのデータベース制御用インスタンス
    private final JdbcTemplate jdbc;

    // jdbc の di/ioc 設定（Wicketとやり方が異なる）
    @Autowired
    public InputtedNumberRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int insert(int number){
        var sql = "insert into inputted_number values(?)";
        var n = jdbc.update(sql,number);
        return n;
    }


}
