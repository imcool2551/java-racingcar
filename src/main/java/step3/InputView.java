package step3;

import java.util.Scanner;

public class InputView {

    private static final String carCount = "자동차 대수는 몇 대 인가요?";
    private static final String tryCount = "시도할 회수는 몇 회 인가요?";
    private static final String wrongInput = "입력이 잘못되었습니다.";

    private static final Scanner input = new Scanner(System.in);

    private InputView() {}

    public static InputDto input() {
        System.out.println(carCount);
        int carCount = input.nextInt();

        System.out.println(tryCount);
        int tryCount = input.nextInt();

        if (!isValidInput(carCount, tryCount)) {
            throw new IllegalArgumentException(wrongInput);
        }

        return new InputDto(carCount, tryCount);
    }

    public static boolean isValidInput(int carCount, int tryCount) {
        if (carCount < 0 || tryCount < 0) {
            return false;
        }

        return true;
    }
}