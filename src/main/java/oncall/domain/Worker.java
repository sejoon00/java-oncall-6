package oncall.domain;

import oncall.error.ErrorCode;

public class Worker {

    private static final int MAX_LENGTH = 5;
    private String name;

    public Worker(String input) {
        validate(input);
        this.name = input;
    }

    private void validate(String input) {
        if (input.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorCode.OVER_WORKER_NAME_LENGTH.getMessage());
        }
    }
}
