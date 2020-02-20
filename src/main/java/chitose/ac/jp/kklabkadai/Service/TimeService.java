package chitose.ac.jp.kklabkadai.Service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeService implements ITimeService{

    @Override
    public String makeCurrentHMS(){
        var now = LocalDateTime.now();
        var str = "(" + now.getMonth() + "." +
                now.getDayOfMonth() + ":" +
                now.getHour() + "." +
                now.getMinute() + ")";
        return str;
    }

}
