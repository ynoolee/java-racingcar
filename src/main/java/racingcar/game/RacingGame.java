package racingcar.game;

import racingcar.car.Car;
import racingcar.car.RacingCars;
import racingcar.car.Winners;
import racingcar.game.dto.CarDto;
import racingcar.game.dto.CarsDto;
import racingcar.random.RandomGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {

    private final Trial numbOfTrial;
    private final RacingCars cars;
    private Winners winners  = Winners.emptyWinners();

    public RacingGame(int numbOfTrial,
                      List<String> carNames,
                      RandomGenerator randomGenerator) {

        this.numbOfTrial = new Trial(numbOfTrial);
        this.cars = makeRacingCars(carNames, randomGenerator);
    }

    private RacingCars makeRacingCars(List<String> names,
                                      RandomGenerator randomGenerator) {
        List<Car> cars = new ArrayList<>();

        for (String name : names) {
            cars.add(new Car(name));
        }

        return new RacingCars(cars, randomGenerator);
    }

    public CarsDto runOnce() {
        if (!numbOfTrial.hasChance()) {
            throw new GameException("이미 레이싱이 끝났습니다.");
        }
        numbOfTrial.decrease();
        moveCars();

        return convertToDTO(this.cars.all());
    }

    private void moveCars() {
        this.cars.moveCars();
    }

    public CarsDto winnerCars() {
        if (!isEnded()) {
            throw new GameException("게임이 끝나기 전까지 우승자를 알 수 없습니다");
        }

        if (this.winners.isEmpty()) {
            pickWinners();
        }

        return convertToDTO(this.winners.winners());
    }

    private void pickWinners() {
        this.winners = new Winners(this.cars.findCarsAt(this.cars.maxLocation()));
    }

    private CarsDto convertToDTO(List<Car> cars) {
        return new CarsDto(cars.stream()
                .map(CarDto::new)
                .collect(Collectors.toList()));
    }

    public boolean isEnded() {
        return !this.numbOfTrial.hasChance();
    }
}
