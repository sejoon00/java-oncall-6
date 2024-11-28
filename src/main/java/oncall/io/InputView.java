package oncall.io;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;
import oncall.domain.TargetMonth;
import oncall.domain.Worker;
import oncall.domain.Workers;
import oncall.error.ErrorCode;

public class InputView implements AutoCloseable {

    private final Reader DEFAULT_READER = new ConsoleReader();
    private final Reader reader;

    public InputView(Reader reader) {
        this.reader = reader;
    }

    public InputView() {
        this.reader = DEFAULT_READER;
    }

    public TargetMonth inputTargetMonth() {
        return retryInput("비상 근무를 배정할 월과 시작 요일을 입력하세요> ",
                () -> new TargetMonth(readLine()));
    }

    public void inputWorkers() {
        while (true) {
            try {
                System.out.print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
                Workers weekdayWorkers = new Workers(readLine());
                System.out.print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
                Workers holidaysWorkers = new Workers(readLine());

                System.out.println();
            } catch (IllegalArgumentException ex) {
                printReInput(ex);
            }
        }
    }

    private String readLine() {
        String input = reader.readLine();
        validate(input);
        return input;
    }

    private static void printReInput(IllegalArgumentException ex) {
        System.out.flush();
        System.out.println(ex.getMessage());
    }

    private static void validate(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.BLANK_INPUT_MESSAGE.getMessage());
        }
    }

    private <T> T retryInput(String prompt, Supplier<T> inputSupplier) {
        while (true) {
            try {
                System.out.print(prompt);
                T result = inputSupplier.get();
                System.out.println();
                return result;
            } catch (IllegalArgumentException ex) {
                printReInput(ex);
            }
        }
    }

    @Override
    public void close() {
        reader.close();
    }
}
