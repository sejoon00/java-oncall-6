package oncall.io;

import java.util.List;

public class OutputView {

    public static void printSchedule(List<String> result) {
        StringBuilder sb = new StringBuilder();
        result.forEach(temp -> sb.append(temp).append("\n"));

        System.out.println(sb);
    }
}
