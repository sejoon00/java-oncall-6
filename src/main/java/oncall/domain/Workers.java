package oncall.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import oncall.error.ErrorCode;

public class Workers {

    public static final int MAX_SIZE = 35;
    public static final int MIN_SIZE = 5;
    Queue<Worker> workers = new LinkedList<>();

    public Workers(String input) {
        String[] split = split(input);
        validateSize(split);
        validateDuplicate(split);
        putWorker(split);
    }

    private void putWorker(String[] split) {
        Arrays.stream(split)
                .map(Worker::new)
                .forEach(worker -> workers.offer(worker));
    }

    private void validateDuplicate(String[] split) {
        Set<String> validator = new HashSet<>(List.of(split));

        if (validator.size() != split.length) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_WORKER.getMessage());
        }
    }

    private void validateSize(String[] split) {
        if (split.length > MAX_SIZE || split.length < MIN_SIZE) {
            throw new IllegalArgumentException(ErrorCode.INVALID_WORKER_SIZE.getMessage());
        }
    }

    private String[] split(String input) {
        String[] split = input.split(",", -1);

        boolean isBlankPresent = Arrays.stream(split)
                .anyMatch(String::isBlank);

        if(isBlankPresent){
            throw new IllegalArgumentException(ErrorCode.BLANK_NICKNAME.getMessage());
        }

        return split;
    }
}
