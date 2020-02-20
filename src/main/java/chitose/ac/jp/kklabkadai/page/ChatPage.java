package chitose.ac.jp.kklabkadai.page;

import chitose.ac.jp.kklabkadai.Service.IChatService;
import chitose.ac.jp.kklabkadai.Service.ITimeService;
import chitose.ac.jp.kklabkadai.data.Chat;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("ChatPage")
public class ChatPage extends WebPage {

    @SpringBean
    private IChatService chatService;
    @SpringBean
    private ITimeService timeService;

    private static IModel<String> attentionModel;

    public ChatPage(){

        var userNameModel = Model.of("");
        var chatContentModel = Model.of("");

        var chatForm = new Form<>("chatInfo"){
            @Override
            protected void onSubmit(){
                String userName = userNameModel.getObject();
                String chatContent = chatContentModel.getObject();

                if(userName!=null && chatContent!=null){
                    chatService.registerChats(userName,chatContent,timeService.makeCurrentHMS());
                    setResponsePage(ChatPage.class);
                }if(userName==null || chatContent==null){
                    System.out.println("ユーザー名とチャット内容の両方を入力してください");
                    //↓ページ飛ぶ
                    setResponsePage(ChatPage.class);
                    //Model化した情報は、更新後に表示したいため、setResponsePageの後にModel化する。
                    //飛んできた後ここに来る//
                    //情報をModel化する
                    attentionModel=Model.of("ユーザー名とチャット内容の両方を入力してください");

                }

            }
        };
        add(chatForm);

        var userNameField = new TextField<>("userName",userNameModel);
        chatForm.add(userNameField);

        var chatContentField = new TextField<>("chatContent",chatContentModel);
        chatForm.add(chatContentField);

        var toTopPageLink = new BookmarkablePageLink<>("toTopPage",TopPage.class);
        add(toTopPageLink);

        var attentionLabel = new Label("attention",attentionModel);
        add(attentionLabel);

        // Service からデータベースのユーザ一覧をもらい、Modelにする
        // List型のモデルは Model.ofList(...) で作成する。
        var chatModel = Model.ofList(chatService.find());

        //List型のモデルを表示する　ListView
        var chatsLV = new ListView<>("chats",chatModel){
            @Override
            protected void populateItem(ListItem<Chat> listItem){
                // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
                var itemModel = listItem.getModel();
                Chat chat = itemModel.getObject(); //元々のListの n 番目の要素

                // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                // add する先が listItem になることに注意。
                var userNameModels = Model.of(chat.getUserName());
                var userNameLabels = new Label("userNames",userNameModels);
                listItem.add(userNameLabels);

                var currentTimeModel = Model.of(chat.getTimeNow());
                var currentTimeLabel = new Label("currentTime",currentTimeModel);
                listItem.add(currentTimeLabel);

                var chatContentModels = Model.of(chat.getChatComment());
                var chatContentLabels = new Label("chatComments",chatContentModels);
                listItem.add(chatContentLabels);
            }
        };
        add(chatsLV);

    }
}
