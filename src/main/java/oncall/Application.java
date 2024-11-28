package oncall;

import java.util.List;
import oncall.domain.MonthSchedule;
import oncall.domain.TargetMonth;
import oncall.io.InputView;
import oncall.io.OutputView;

public class Application {
    public static void main(String[] args) {
        try(InputView inputView = new InputView()){
            TargetMonth targetMonth = inputView.inputTargetMonth();
            MonthSchedule monthSchedule = inputView.inputWorkers(targetMonth);
            List<String> schedule = monthSchedule.createMonthSchedule();
            OutputView.printSchedule(schedule);
        }
    }
}
