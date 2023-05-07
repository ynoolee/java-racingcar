package racingcar.car;

import racingcar.random.RandNum;

public class Car {

    private final Name name;
    private Position position;

    public Car(String name) {
        this(new Name(name), new Position());
    }

    public Car(String name, int location) {
        this(new Name(name), new Position(location));
    }

    public Car(Name name, Position position) {
        this.name = name;
        this.position = position;
    }

    public Position location() {
        return this.position;
    }

    public Name name() {
        return this.name;
    }

    public void move(RandNum randNum) {
        this.position = position.move(randNum);
    }

    public boolean isWinner(Position maxLocation) {
        return this.position.isGreaterThan(maxLocation);
    }

    public Position maxLocation(Position criteria) {
        return this.position.max(criteria);
    }

    public boolean isAt(Position location) {
        return this.position.equals(location);
    }
}
