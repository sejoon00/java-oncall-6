package oncall;

import oncall.domain.TargetMonth;
import oncall.domain.Workers;
import oncall.io.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        InputView inputView = new InputView();


        TargetMonth targetMonth = inputView.inputTargetMonth();
        Workers weekdayWorkers = inputView.inputWeekdayWorkers();

    }
}
