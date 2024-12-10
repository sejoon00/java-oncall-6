package oncall.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import oncall.error.ErrorCode;

public enum Month {
    JANUARY(1, 31, List.of(new Holiday("1월 1일"))),
    FEBRUARY(2, 28, null),
    MARCH(3, 31, List.of(new Holiday("3월 1일"))),
    APRIL(4, 30, null),
    MAY(5, 31, List.of(new Holiday("5월 5일"))),
    JUNE(6, 30, List.of(new Holiday("6월 6일"))),
    JULY(7, 31, null),
    AUGUST(8, 31, List.of(new Holiday("8월 15일"))),
    SEPTEMBER(9, 30, null),
    OCTOBER(10, 31, List.of(new Holiday("10월 3일"), new Holiday("10월 9일"))),
    NOVEMBER(11, 30, List.of()),
    DECEMBER(12, 31, List.of(new Holiday("12월 25일")));

    private static final int INPUT_LENGTH = 2;
    private final int number;
    private final int lastDate;
    private final List<Holiday> holidays;

    Month(int number, int lastDate, List<Holiday> holidays) {
        this.number = number;
        this.lastDate = lastDate;
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

        if(inputName.length() > INPUT_LENGTH) {
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

    public static boolean isHoliday(String date) {
        return Arrays.stream(values())
                .map(Month::getHolidays)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .anyMatch(holiday -> holiday.isEquals(date));
    }

    public int getLastDate() {
        return lastDate;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }

    public int getNumber() {
        return number;
    }

    static class Holiday{
        private final String date;

        public Holiday(String date) {
            this.date = date;
        }

        public boolean isEquals(String input) {
            return date.equals(input);
        }
    }
}
