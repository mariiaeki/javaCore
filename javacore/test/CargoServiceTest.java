import homework_15_2001_concurrency.cargo.domain.OutfitCargo;
import homework_15_2001_concurrency.cargo.domain.PerishableCargo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.Date;

public class CargoServiceTest {
    private PerishableCargo prepareFood() {
        PerishableCargo food = new PerishableCargo();
        food.setId(randLong());
        food.setName(randString());
        food.setWeight(randInt());
        food.setStoreTemperature(randInt());
        food.setDateOfExpire(new Date());

        return food;
    }

    private OutfitCargo prepareClothers() {
        OutfitCargo clothers = new OutfitCargo();
        clothers.setName(randString());
        clothers.setId(randLong());
        clothers.setSize(randInt());
        clothers.setWeight(randInt());
        clothers.setGender(OutfitCargo.Gender.FEMALE);

        return clothers;
    }

    private String randString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    private int randInt() {
        return RandomUtils.nextInt();
    }

    private long randLong() {
        return RandomUtils.nextLong();
    }
}
