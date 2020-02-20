package chitose.ac.jp.kklabkadai.data;

import java.io.Serializable;

public class Chat implements Serializable {

    private String userName;
    private String chatComment;
    private String timeNow;

    public Chat(){
        userName = "";
        chatComment = "";
        timeNow = "";
    }

    public String getUserName(){
        return userName;
    }

    public String getChatComment(){
        return chatComment;
    }

    public String getTimeNow(){
        return timeNow;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setChatComment(String chatComment){
        this.chatComment = chatComment;
    }

    public void setTimeNow(String timeNow){
        this.timeNow = timeNow;
    }
}
