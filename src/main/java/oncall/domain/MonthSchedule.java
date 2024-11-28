package oncall.domain;

import java.util.ArrayList;
import java.util.List;

public class MonthSchedule {

    private TargetMonth targetMonth;
    private Workers weekDayWorkers;
    private Workers holidayWorkers;
    private List<String> schedule = new ArrayList<>();

    public MonthSchedule(TargetMonth targetMonth, Workers weekDayWorkers, Workers holidayWorkers) {
        this.targetMonth = targetMonth;
        this.weekDayWorkers = weekDayWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    public List<String> createMonthSchedule() {
        while(!targetMonth.isFinishDateInMonth()){
            String nextDate = targetMonth.getNextDate();
            DaysOfWeek nextDay = targetMonth.getNextDay();
            String nextDateAndDay = targetMonth.getNextDateAndDay();

            if(nextDay.isHoliday(nextDay)){
                Worker holidayWorker = getHolidayWorker();
                schedule.add(nextDateAndDay + " " + holidayWorker.getName());
                continue;
            }

            if(Month.isHoliday(nextDate)){
                Worker holidayWorker = getHolidayWorker();
                schedule.add(nextDateAndDay + "(휴일) " + holidayWorker.getName());
                continue;
            }
            Worker weekDayWorker = weekDayWorkers.getCurrentWorker();

            schedule.add(nextDateAndDay + " " + weekDayWorker.getName());
        }

        return schedule;
    }

    private Worker getHolidayWorker() {
        Worker weekDayWorker = weekDayWorkers.peekCurrentWorker();
        Worker holidayWorker = holidayWorkers.getCurrentWorker();
        if(weekDayWorker.equals(holidayWorker)){
            weekDayWorkers.changeWorkerSequence();
        }
        return holidayWorker;
    }
}
