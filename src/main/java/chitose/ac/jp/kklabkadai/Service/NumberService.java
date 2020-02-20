package chitose.ac.jp.kklabkadai.Service;

import chitose.ac.jp.kklabkadai.repository.IInputtedNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberService implements INumberService{

    private IInputtedNumberRepository iInputtedNumberRepository;

    @Autowired
    public NumberService(IInputtedNumberRepository iInputtedNumberRepository) {
        this.iInputtedNumberRepository = iInputtedNumberRepository;
    }

    @Override
    public void registerNumber(int number){
        int n = iInputtedNumberRepository.insert(number);
        System.out.println("記録行数:" + n);
        System.out.println("送信した番号:" + number);
    }

}
