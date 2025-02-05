package racingcar;

import racingcar.domain.game.RacingGame;
import racingcar.domain.random.RangeRandomNumberGenerator;
import racingcar.view.ConsoleView;
import racingcar.view.NameParser;

import java.util.List;

public class RacingGameController {
    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView(new NameParser());

        int trial = consoleView.numbOfTrial();
        List<String> names = consoleView.namesOfCar();

        RacingGame racingGame = new RacingGame(
                trial,
                names,
                new RangeRandomNumberGenerator(0, 9));

        for (int t = 0; t < trial; t++) {
            consoleView.printCarsLocation(racingGame.runOnce());
        }

        consoleView.printWinners(racingGame.winnerCars());
    }

}

