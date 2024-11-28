package oncall.domain;

import java.util.Arrays;
import oncall.error.ErrorCode;

public enum DaysOfWeek {
    MONDAY("일", 1,2),
    TUESDAY("월", 2,3),
    WEDNESDAY("화", 3,4),
    THURSDAY("수", 4,5),
    FRIDAY("목", 5,6),
    SATURDAY("금", 6,7),
    SUNDAY("토", 7,1);

    private static final int NAME_LENGTH = 1;
    private final String name;
    private final int number;
    private final int nextDay;

    DaysOfWeek(String name, int number, int nextDay) {
        this.name = name;
        this.number = number;
        this.nextDay = nextDay;
    }

    public boolean isHoliday(DaysOfWeek input) {
        return input.getNumber() == 1 || input.getNumber() == 7;
    }

    public String getName() {
        return name;
    }

    private int getNextDay() {
        return nextDay;
    }

    private int getNumber() {
        return number;
    }

    public static DaysOfWeek getDayByName(String inputName) {
        validate(inputName);
        return Arrays.stream(DaysOfWeek.values())
                .filter(day -> day.getName().equals(inputName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NOT_FOUND_DAY.getMessage()));
    }

    public static DaysOfWeek getNextDay(DaysOfWeek daysOfWeek) {
        return Arrays.stream(DaysOfWeek.values())
                .filter(day -> day.getNumber() == daysOfWeek.getNextDay())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NOT_FOUND_DAY.getMessage()));
    }

    private static void validate(String inputName){
        if (inputName.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.BLANK_INPUT_MESSAGE.getMessage());
        }

        if(inputName.length() != NAME_LENGTH){
            throw new IllegalArgumentException(ErrorCode.OVER_DAYS_NAME_LENGTH.getMessage());
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
