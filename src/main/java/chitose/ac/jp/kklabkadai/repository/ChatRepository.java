package chitose.ac.jp.kklabkadai.repository;

import chitose.ac.jp.kklabkadai.data.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepository implements IChatRepository{

    // SpringJDBCのデータベース制御用インスタンス
    private final JdbcTemplate jdbc;

    // jdbc の di/ioc 設定（Wicketとやり方が異なる）
    @Autowired
    public ChatRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int insert(String usernName,String chatComment,String time){
        var sql = "insert into chat values(?,?,?)";
        var n = jdbc.update(sql,usernName,chatComment,time);
        return n;
    }

    @Override
    public List<Chat> find(){
        String sql = "select user_name, chat_comment,timeNow from chat ";

        // 検索用のSQLを実行する方法。
        // 取り出したいデータの型によって、第2引数の RowMapper を切り替える。
        // ? を使うSQLであれば、第3引数の Object型配列 の要素に順番に設定する。
        List<Chat> chats = jdbc.query(sql,
                new BeanPropertyRowMapper<>(Chat.class),
                new Object[]{});

        // 取り出したデータ（Listの要素）をそのまま返値とする。
        return chats;
    }

}