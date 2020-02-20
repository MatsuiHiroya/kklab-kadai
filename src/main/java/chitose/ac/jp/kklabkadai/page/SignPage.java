package chitose.ac.jp.kklabkadai.page;

import chitose.ac.jp.kklabkadai.Service.IAuthUserService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("SignPage")
public class SignPage extends WebPage {

    @SpringBean
    private IAuthUserService authUserService;

    public SignPage(){

        var userNameModel = Model.of("");
        var userPassModel = Model.of("");
        var signNameModel = Model.of("");
        var signPassModel = Model.of("");

        var makeForm = new Form<>("makeUserForm"){
            @Override
            protected void onSubmit(){
                String userName = userNameModel.getObject();
                String userPass = userPassModel.getObject();

                if(userName!=null && userPass!=null){
                    authUserService.registerUser(userName,userPass);
                    setResponsePage(SignPage.class);
                }else if(userName==null || userPass==null){
                    System.out.println("ユーザー名とチャット内容の両方を入力してください");
                    setResponsePage(ChatPage.class);
                }
            }
        };
        add(makeForm);

        var makeUserField = new TextField<>("newUserName",userNameModel);
        makeForm.add(makeUserField);

        var makeUserPassField = new PasswordTextField("newPassWord",userPassModel);
        makeForm.add(makeUserPassField);

        //authUserService.createChat("me");

        var signForm = new Form<>("signUserForm"){
            @Override
            protected void onSubmit(){
                String userName = userNameModel.getObject();
                String userPass = userPassModel.getObject();

                if(userName!=null && userPass!=null){

                    setResponsePage(SignPage.class);
                }else if(userName==null || userPass==null){
                    System.out.println("ユーザー名とチャット内容の両方を入力してください");
                    setResponsePage(ChatPage.class);
                }
            }
        };
        add(signForm);

        var signNameField = new TextField<>("signUserName",signNameModel);
        signForm.add(signNameField);

        var signPassField = new PasswordTextField("signPassWord",signPassModel);
        signForm.add(signPassField);

    }

}
