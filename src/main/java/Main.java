import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "countries.csv";

    // nie zmieniaj nic w main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main main = new Main();
        main.run(scanner);
    }

    void run(Scanner scanner) {
        try {
            Map<String, Country> countryMap = readCountryFromFile(FILE_NAME);
            System.out.println("Podaj kod kraju, o którym chcesz zobaczyć informacje:");
            String countryCode = scanner.nextLine();
            if (countryMap.containsKey(countryCode)) {
                System.out.println(countryMap.get(countryCode));
            } else {
                System.out.printf("Kod kraju %s nie został znaleziony", countryCode);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku countries.csv.");
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku");
        }
    }

    private Map<String, Country> readCountryFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Map<String, Country> countryMap = new HashMap<>();
        String nextLine;
        while ((nextLine = reader.readLine()) != null) {
            String[] split = nextLine.split(";");
            if (!countryMap.containsKey(split[0])) {
                countryMap.put(split[0], new Country(split[0], split[1], Integer.parseInt(split[2])));
            }
        }
        return countryMap;
    }
}
