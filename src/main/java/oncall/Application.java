package oncall;

import oncall.domain.TargetMonth;
import oncall.io.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        InputView inputView = new InputView();

        while (true) {
            TargetMonth targetMonth = inputView.inputTargetMonth();

        }
    }
}
