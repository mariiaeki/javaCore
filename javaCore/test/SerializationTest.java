import homework_15_2001_concurrency.cargo.domain.OutfitCargo;
import homework_15_2001_concurrency.cargo.domain.PerishableCargo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static homework_15_2001_concurrency.common.comparator.SimpleComparator.LONG_COMPARATOR;
import static homework_15_2001_concurrency.common.comparator.SimpleComparator.STRING_COMPARATOR;

public class SerializationTest {
    private Path tempFile = null;

    @Before
    public void createTempFile() throws IOException {
        tempFile = Files.createTempFile("temp", "test");
    }

    @AfterEach
    public void deleteTempFile() {
        deleteFile(tempFile);
    }

    @Test
    public void testSerializationFoodCargo() throws Exception {
        PerishableCargo cargo = prepareFood();
        String pathToFile = tempFile.toAbsolutePath().toString();

        serializeToFile(cargo, pathToFile);
        PerishableCargo deserialized = readSerializedObjectFromFile(pathToFile);


        Assertions.assertTrue(areFoodEntitiesEquals(cargo, deserialized));
    }

    @Test
    public void testSerializationFoodCargos() throws Exception {
        List<PerishableCargo> foods = Arrays.asList(prepareFood(), prepareFood());
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(foods, pathToFile);
        List<PerishableCargo> deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areFoodEntitiesEquals(foods, deserialized));
    }

    @Test
    public void testSerializationFoodNullCargo() throws Exception {
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(null, pathToFile);
        Object deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertNull(deserialized);
    }

    @Test
    public void testSerializationClothersCargo() throws Exception {
        OutfitCargo clothers = prepareClothers();
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(clothers, pathToFile);
        OutfitCargo deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areClotherEntitiesEquals(clothers, deserialized));
    }

    @Test
    public void testSerializationClothersCargos() throws Exception {
        List<OutfitCargo> clothers = Arrays.asList(prepareClothers(), prepareClothers());
        String pathToFile = tempFile.toAbsolutePath().toString();

        serializeToFile(clothers, pathToFile);
        List<OutfitCargo> deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areClotherEntitiesEquals(clothers, deserialized));
    }

    @Test
    public void testSerializationClothersNullCargos() throws Exception {
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(null, pathToFile);
        Object deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertNull(deserialized);
    }

    private <T> void serializeToFile(T entity, String file) throws Exception {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(entity);
        }
    }

    private <T> T readSerializedObjectFromFile(String file) throws Exception {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (T) objectInputStream.readObject();
        }
    }

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

    private boolean areFoodEntitiesEquals(PerishableCargo food1, PerishableCargo food2) {
        if (food1 == null && food2 == null) {
            return true;
        } else if (food1 != null && food2 == null) {
            return false;
        } else if (food1 == null) {
            return false;
        } else {
            return STRING_COMPARATOR.compare(food1.getName(), food2.getName()) == 0
                    && LONG_COMPARATOR.compare(food1.getId(), food2.getId()) == 0
                    && food1.getWeight() == food2.getWeight()
                    && food1.getStoreTemperature() == food2.getStoreTemperature()
                    && STRING_COMPARATOR.compare(String.valueOf(food1.getDateOfExpire()), String.valueOf(food2.getDateOfExpire())) == 0;

        }
    }

    private boolean areFoodEntitiesEquals(List<PerishableCargo> foods1, List<PerishableCargo> foods2) {
        if (foods1 == null && foods2 == null) {
            return true;
        } else if (foods1 != null && foods2 == null) {
            return false;
        } else if (foods1 == null) {
            return false;
        } else if (foods1.size() != foods2.size()) {
            return false;
        } else {
            for (int i = 0; i < foods1.size(); i++) {
                if (!areFoodEntitiesEquals(foods1.get(i), foods2.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean areClotherEntitiesEquals(OutfitCargo clother1, OutfitCargo clother2) {
        if (clother1 == null && clother2 == null) {
            return true;
        } else if (clother1 != null && clother2 == null) {
            return false;
        } else if (clother1 == null) {
            return false;
        } else {
            return STRING_COMPARATOR.compare(clother1.getName(), clother2.getName()) == 0
                    && LONG_COMPARATOR.compare(clother1.getId(), clother2.getId()) == 0
                    && STRING_COMPARATOR.compare(String.valueOf(clother1.getGender()), String.valueOf(clother2.getGender())) == 0
                    && clother1.getSize() == clother2.getSize()
                    && clother1.getWeight() == clother2.getWeight();
        }
    }

    private boolean areClotherEntitiesEquals(List<OutfitCargo> clothers1,
                                             List<OutfitCargo> clothers2) {
        if (clothers1 == null && clothers2 == null) {
            return true;
        } else if (clothers1 != null && clothers2 == null) {
            return false;
        } else if (clothers1 == null) {
            return false;
        } else if (clothers1.size() != clothers2.size()) {
            return false;
        } else {
            for (int i = 0; i < clothers1.size(); i++) {
                if (!areClotherEntitiesEquals(clothers1.get(i), clothers2.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    private void deleteFile(Path path) {
        if (path != null && path.toFile().isFile()) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
