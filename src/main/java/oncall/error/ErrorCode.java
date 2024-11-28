package oncall.error;

public enum ErrorCode {

    BLANK_INPUT_MESSAGE("잘못된 입력입니다. 다시 입력해 주세요."),
    INPUT_ERROR("잘못된 입력입니다. 다시 입력해주세요"),
    NOT_INTEGER("구입 금액은 정수 범위가 아닌 값이나 문자는 입력할 수 없습니다."),
    OVER_MONTH_NAME_LENGTH("월은 1~12범위만 입력할 수 있습니다."),
    OVER_DAYS_NAME_LENGTH("요일은 한 글자만 입력할 수 있습니다."),
    NOT_FOUND_DAY("잘못된 요일을 입력하셨습니다."),
    NEGATIVE_INPUT_ERROR("입력 값은 음수가 될 수 없습니다"),
    MONTH_NOT_INPUT_NUMBER_ERROR("월은 정수 범위의 양수만 입력받을 수 있습니다"),
    NOT_FOUND_MONTH("잘못된 월을 입력하셨습니다."),
    OVER_WORKER_NAME_LENGTH("사원 닉네임은 5글자를 초과할 수 없습니다."),
    BLANK_NICKNAME("닉네임은 빈칸이 될 수 없습니다"),
    INVALID_WORKER_SIZE("비상근무요원은 5명 이상 35명 이하만 가능합니다"),
    DUPLICATE_WORKER("중복된 근무 편성이 있습니다"),
    ;
    private final String message;
    private static final String PREFIX = "[ERROR] ";

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
