package chitose.ac.jp.kklabkadai.page;

import chitose.ac.jp.kklabkadai.Service.INumberService;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Random;

@WicketHomePage
@MountPath("TopPage")
public class TopPage extends WebPage {

    private static IModel<String> omikujiModel;
    //private static Label omikujiLabel;

    //INumberService を　IoC/DI
    @SpringBean
    private INumberService numberService;

    public TopPage(){

        Random random = new Random();

        var numberModel = Model.of("");

        var mynameModel = Model.of("松井 宏矢");
        var mynameLabel = new Label("myname",mynameModel);
        add(mynameLabel);

        var ageModel = Model.of("20");
        var ageLabel = new Label("age",ageModel);
        add(ageLabel);

        var gradeModel = Model.of("学部2年");
        var gradeLabel = new Label("grade",gradeModel);
        add(gradeLabel);

        var hobyModel = Model.of("ソシャゲのガチャ");
        var hobyLabel = new Label("hoby",hobyModel);
        add(hobyLabel);

        var numberForm = new Form<>("numberForm"){
            @Override
            protected void onSubmit(){

                int inputNumber = Integer.parseInt(numberModel.getObject());

                if(inputNumber>=0 && inputNumber<=100){

                    int randomNumber = random.nextInt(3);
                    switch (randomNumber){
                        case 0:
                            omikujiModel = Model.of("大吉");
                            break;
                        case 1:
                            omikujiModel = Model.of("中吉");
                            break;
                        case 2:
                            omikujiModel = Model.of("小吉");
                            break;
                    }

                }else if(inputNumber>100 && inputNumber<=1000){
                    omikujiModel = Model.of(String.valueOf(inputNumber));
                }

                // IoC/DI した numberService のメソッドを呼び出す
                numberService.registerNumber(inputNumber);
                setResponsePage(TopPage.class);

            }

        };
        add(numberForm);

        var numberField = new TextField<>("inputNumber",numberModel);
        numberForm.add(numberField);

        var omikujiLabel = new Label("omikuji",omikujiModel);
        add(omikujiLabel);

        var toChatPageLink = new BookmarkablePageLink<>("toChatPage",ChatPage.class);
        add(toChatPageLink);

        var toSignPageLink = new BookmarkablePageLink<>("toSignPage",SignPage.class);
        add(toSignPageLink);

    }
}
