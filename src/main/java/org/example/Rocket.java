package org.example;

/**
 * Клас, що представляє ракету з можливістю запуску та дозаправки.
 */
public class Rocket {
    private RocketStatus status = RocketStatus.ON_GROUND;
    private float fuelLevel = 0;

    /**
     * Перелік можливих станів ракети.
     */
    public enum RocketStatus {
        ON_GROUND, IN_SPACE, DAMAGED
    }
    
    /**
     * Максимальний рівень пального в ракеті, більший призведе до пошкодженння топлевних баків.
     */
    public static final float MAX_FUEL_LEVEL = 1000;

    /**
     * Мінімальний рівень пального, необхідний для запуску ракети.
     */
    public static final float FUEL_TO_LAUNCH = 100;

    /**
     * Спроба запустити ракету.
     *
     * @return true, якщо запуск успішний, false - якщо ракета вже в космосі або пошкоджена
     * @throws RuntimeException якщо недостатньо пального для запуску
     */
    public boolean launch() {
        if (fuelLevel < FUEL_TO_LAUNCH) {
            status = RocketStatus.DAMAGED;
            throw new RuntimeException("Катастрофа при старті, не вистачило пального");
        } 
        if (status == RocketStatus.ON_GROUND) {
            fuelLevel -= FUEL_TO_LAUNCH;
            status = RocketStatus.IN_SPACE;
            return true;
        }
        return false;
    }

    /**
     * Отримати поточний стан ракети.
     *
     * @return поточний стан ракети
     */
    public RocketStatus getStatus() {
        return status;
    }

    /**
     * Отримати поточний рівень пального.
     *
     * @return кількість пального в ракеті
     */
    public float getFuelLevel() {
        return fuelLevel;
    }

    /**
     * Дозаправити ракету.
     *
     * @param amount кількість пального для дозаправки
     * @throws RuntimeException якщо бак переповнено (більше ніж MAX_FUEL_LEVEL)
     * @throws IllegalArgumentException якщо кількість пального для дозаправки від'ємна
     */
    public void refuel(float amount) {
        if (fuelLevel + amount > MAX_FUEL_LEVEL) {
            status = RocketStatus.DAMAGED;
            throw new RuntimeException("Бак пошкоджено через переповнення");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Кількість пального має бути додатною");
        }
        fuelLevel += amount;
    }
}