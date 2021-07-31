import java.text.DecimalFormat;
import java.util.Arrays;

public class Hospital {

    public static float[] generatePatientsTemperatures(int patientsCount) {
        float[] temperature = new float[patientsCount];
        for (int a = 0; a < patientsCount; a++) {
            temperature[a] = getTemperature(32, 40);
        }
        //TODO: напишите метод генерации массива температур пациентов
        return temperature;
    }

    public static float getTemperature(float minTemperature, float maxTemperature) {
        float temperature = (float) ((Math.random() * (maxTemperature - minTemperature)) + minTemperature);
        return temperature;
    }

    public static String getReport(float[] temperatureData) {
        float totalTemperature = 0;
        float middleTemperature;
        int countHealthyPatient = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int a = 0; a < temperatureData.length; a++) {
            totalTemperature += temperatureData[a];
            stringBuilder.append(temperatureData[a]).append(" ");
            if (temperatureData[a] < 37.0 && temperatureData[a] > 36.1) {
                countHealthyPatient++;
            }
        }

        middleTemperature = totalTemperature / temperatureData.length;
        double scale = Math.pow(10, 2);
        middleTemperature = (float) (Math.round(middleTemperature * scale) / scale);

        /*
        TODO: Напишите код, который выводит среднюю температуру по больнице,количество здоровых пациентов,
            а также температуры всех пациентов.
        */
        String report =
                "Температуры пациентов: " + stringBuilder.toString().trim() +
                        "\nСредняя температура: " + middleTemperature +
                        "\nКоличество здоровых: " + countHealthyPatient;

        return report;
    }
}
