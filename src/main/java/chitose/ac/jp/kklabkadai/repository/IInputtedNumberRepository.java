package chitose.ac.jp.kklabkadai.repository;

import chitose.ac.jp.kklabkadai.data.Chat;

import java.util.List;

public interface IInputtedNumberRepository {

    /**
     * 入力された数値をInputtedNumber　テーブルに記録する
     */

    public int insert(int number);

}
