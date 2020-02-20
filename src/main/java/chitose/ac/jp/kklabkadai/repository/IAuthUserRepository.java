package chitose.ac.jp.kklabkadai.repository;

public interface IAuthUserRepository {

    /**
     * auth_user テーブルに　ユーザー名　と　パスワードを記録
     *
     * @param userName
     * @param userPass
     * @return　データベースの更新行数
     */

    public int insert(String userName,String userPass);

    //public int newChatTable(String userName,String comments);

}
