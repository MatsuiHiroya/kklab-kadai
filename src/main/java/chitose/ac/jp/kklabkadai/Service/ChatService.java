package chitose.ac.jp.kklabkadai.Service;

import chitose.ac.jp.kklabkadai.data.Chat;
import chitose.ac.jp.kklabkadai.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements IChatService {

    private IChatRepository chatRepos;

    @Autowired
    public ChatService(IChatRepository chatRepos){
        this.chatRepos = chatRepos;
    }

    @Override
    public void registerChats(String userName,String chatComment,String timeNow){
        int n = chatRepos.insert(userName,chatComment,timeNow);
        System.out.println("記録行数:" + n);
        System.out.println("投稿したユーザー:" + userName);
        System.out.println("投稿された内容:" + chatComment);
        System.out.println("送信された時刻:" + timeNow);
    }

    @Override
    public List<Chat> find() {
        var chats = chatRepos.find();
        System.out.println("chatテーブル内のデータ件数：" + chats.size());
        return chats;
    }
}
