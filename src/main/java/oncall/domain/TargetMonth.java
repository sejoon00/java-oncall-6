package oncall.domain;

import oncall.error.ErrorCode;

public class TargetMonth {

    private static final int INPUT_LENGTH = 1;
    private static final int INPUT_CASE_NUMBER = 2;

    private Month month;
    private DaysOfWeek day;
    private int tempDate;

    public TargetMonth(String input) {
        String[] split = split(input);
        this.month = Month.getMonthByInput(split[0]);
        this.day = DaysOfWeek.getDayByName(split[1]);
        this.tempDate = 1;
    }

    private String[] split(String input) {
        String[] split = input.split(",");

        if(split.length != INPUT_CASE_NUMBER){
            throw new IllegalArgumentException(ErrorCode.INPUT_ERROR.getMessage());
        }

        return split;
    }

    public DaysOfWeek getNextDay() {
        return day;
    }

    public String getNextDate() {
        return month.getNumber() + "월 " + tempDate + "일";
    }

    public String getNextDateAndDay() {
        String result = month.getNumber() + "월 " + tempDate + "일 " + day.getName();
        this.day = DaysOfWeek.getNextDay(this.day);
        tempDate++;

        return result;
    }

    public boolean isFinishDateInMonth() {
        return tempDate > month.getLastDate();
    }
}
