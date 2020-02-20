package chitose.ac.jp.kklabkadai.Service;

import chitose.ac.jp.kklabkadai.repository.IAuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements IAuthUserService{

    private IAuthUserRepository authUserRepos;

    @Autowired
    public AuthUserService(IAuthUserRepository authUserRepos){
        this.authUserRepos = authUserRepos;
    }

    @Override
    public void registerUser(String userName,String userPass){
        int n = authUserRepos.insert(userName,userPass);
        System.out.println("記録行数:" + n);
        System.out.println("投稿したユーザー:" + userName);
        System.out.println("投稿したパスワード:" + userPass);
    }

    /**
    @Override
    public void createChat(String userName){
        authUserRepos.newChatTable(userName,"qq");
    }*/


}
