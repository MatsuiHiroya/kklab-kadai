package chitose.ac.jp.kklabkadai.Service;

import chitose.ac.jp.kklabkadai.data.Chat;

import java.util.List;

public interface IChatService {

    public void registerChats(String userName,String chatComment,String time);

    /**
     * ユーザ名とパスワードの一覧を、Chat型のリストで検索する
     *
     * @return Chat型のListインスタンス
     */
    public List<Chat> find();
}
