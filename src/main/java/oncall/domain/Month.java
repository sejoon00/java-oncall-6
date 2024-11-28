package oncall.domain;

import java.util.Arrays;
import java.util.List;
import oncall.error.ErrorCode;

public enum Month {
    JANUARY("January", 1, List.of(new Holiday(1, 1))),
    FEBRUARY("February", 2, null),
    MARCH("March", 3, List.of(new Holiday(3, 1))),
    APRIL("April", 4, null),
    MAY("May", 5, List.of(new Holiday(5, 5))),
    JUNE("June", 6, List.of(new Holiday(6, 6))),
    JULY("July", 7, null),
    AUGUST("August", 8, List.of(new Holiday(8, 15))),
    SEPTEMBER("September", 9, null),
    OCTOBER("October", 10, List.of(new Holiday(10, 3), new Holiday(10, 9))),
    NOVEMBER("November", 11, null),
    DECEMBER("December", 12, List.of(new Holiday(12, 25)));

    private static final int INPUT_LENGTH = 2;

    private final String name;
    private final int number;
    private final List<Holiday> holidays;

    Month(String name, int number, List<Holiday> holidays) {
        this.name = name;
        this.number = number;
        this.holidays = holidays;
    }

    public static Month getMonthByInput(String input) {
        validate(input);
        int number = parseInt(input);
        return Arrays.stream(Month.values())
                .filter(day -> day.getNumber() == (number))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.NOT_FOUND_MONTH.getMessage()));
    }

    private static void validate(String inputName){
        if (inputName.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.BLANK_INPUT_MESSAGE.getMessage());
        }

        if(inputName.length() > INPUT_LENGTH){
            throw new IllegalArgumentException(ErrorCode.OVER_MONTH_NAME_LENGTH.getMessage());
        }


    }

    private static int parseInt(String value){
        try{
            int number = Integer.parseInt(value);
            if(number <= 0)
                throw new IllegalArgumentException(ErrorCode.NEGATIVE_INPUT_ERROR.getMessage());
            return number;
        } catch (NumberFormatException e){
            throw new IllegalArgumentException(ErrorCode.MONTH_NOT_INPUT_NUMBER_ERROR.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    private int getNumber() {
        return number;
    }

    static class Holiday{
        private final int month;
        private final int date;

        public Holiday(int month, int date) {
            this.month = month;
            this.date = date;
        }
    }
}
