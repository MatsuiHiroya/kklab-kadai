package chitose.ac.jp.kklabkadai.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthUserRepository implements IAuthUserRepository{

    private final JdbcTemplate jdbc;

    @Autowired
    public AuthUserRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public int insert(String userName,String userPass){
        var sql = "insert into auth_user values(?,?)";
        var n = jdbc.update(sql,userName,userPass);
        return n;
    }

    /**

    //この下の文章に関するいんてーフェースの決してよし
    @Override
    public int newChatTable(String userName,String comment){
        var sql = "create new table ?()";
        var n = jdbc.update(sql,userName);
        return n;
    }*/
}
