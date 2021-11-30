package racing.refactor.view;

import racing.refactor.dto.ResponseCar;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

public class OutputViewResolver {

    private static final int ZERO = 0;
    private static final PrintStream PRINT_STREAM = System.out;

    private static final String LINE_SYMBOL = "-";

    public void displayWinners(List<ResponseCar> winners) {
        List<String> winnerNames = winners.stream()
                .map(ResponseCar::getName)
                .collect(Collectors.toList());
        String winnersToPlainText = String.join(",", winnerNames);
        PRINT_STREAM.println(winnersToPlainText + " 가 최종 우승했습니다.");
    }

    public void displayTracks(List<ResponseCar> carInformations) {
        for (ResponseCar carInformation : carInformations) {
            PRINT_STREAM.println(displayOneTrack(carInformation));
        }
        PRINT_STREAM.println();
    }

    private String displayOneTrack(ResponseCar carInformation) {
        String name = carInformation.getName();
        int count = carInformation.getDistanceDriven();

        return name + " : " + displayLine(count);
    }

    private String displayLine(int lineLength) {
        StringBuilder line = new StringBuilder();
        for (int i = ZERO; i < lineLength; i++) {
            line.append(LINE_SYMBOL);
        }
        return line.toString();
    }
}