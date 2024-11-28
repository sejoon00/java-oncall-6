package oncall.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import oncall.error.ErrorCode;

public enum Month {
    JANUARY("January", 1, 31, List.of(new Holiday("1월 1일"))),
    FEBRUARY("February", 2, 28, null),
    MARCH("March", 3, 31, List.of(new Holiday("3월 1일"))),
    APRIL("April", 4, 30, null),
    MAY("May", 5, 31, List.of(new Holiday("5월 5일"))),
    JUNE("June", 6, 30, List.of(new Holiday("6월 6일"))),
    JULY("July", 7, 31, null),
    AUGUST("August", 8, 31, List.of(new Holiday("8월 15일"))),
    SEPTEMBER("September", 9, 30, null),
    OCTOBER("October", 10, 31, List.of(new Holiday("10월 3일"), new Holiday("10월 9일"))),
    NOVEMBER("November", 11, 30, null),
    DECEMBER("December", 12, 31, List.of(new Holiday("12월 25일")));

    private static final int INPUT_LENGTH = 2;

    private final String name;
    private final int number;
    private final int lastDate;
    private final List<Holiday> holidays;

    Month(String name, int number, int lastDate, List<Holiday> holidays) {
        this.name = name;
        this.number = number;
        this.lastDate = lastDate;
        this.holidays = holidays;
    }

    public int getLastDate() {
        return lastDate;
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

    public List<Holiday> getHolidays() {
        return holidays;
    }

    public static boolean isHoliday(String date) {
        List<List<Holiday>> list = Arrays.stream(values())
                .map(Month::getHolidays)
                .filter(Objects::nonNull)
                .toList();
        for (List<Holiday> holidays : list) {
            boolean isHoliday = holidays.stream()
                    .anyMatch(holiday -> holiday.isEquals(date));
            if(isHoliday)
                return true;
        }
        return false;
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
