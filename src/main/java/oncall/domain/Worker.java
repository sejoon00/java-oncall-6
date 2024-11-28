package oncall.domain;

import java.util.Objects;
import oncall.error.ErrorCode;

public class Worker {

    private static final int MAX_LENGTH = 5;
    private String name;

    public Worker(String input) {
        validate(input);
        this.name = input;
    }

    public String getName() {
        return name;
    }

    private void validate(String input) {
        if (input.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorCode.OVER_WORKER_NAME_LENGTH.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker worker = (Worker) o;
        return Objects.equals(name, worker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
