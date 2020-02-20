package chitose.ac.jp.kklabkadai.repository;

import chitose.ac.jp.kklabkadai.data.Chat;

import java.util.List;

public interface IChatRepository {

    /**
     * chat テーブルに ユーザー名 と コメント を記録する
     *
     * @param userName ユーザー名
     * @param chatComment　コメント
     * @return　データベースの更新行数
     */

    public int insert(String userName,String chatComment,String time);

    /**
     * Chat　テーブルから検索
     * @return　レコードの内容を {@link Chat} の {@link List} で返す
     */

    public List<Chat> find();

}
