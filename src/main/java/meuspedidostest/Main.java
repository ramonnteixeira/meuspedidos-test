package meuspedidostest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.csveed.api.CsvClient;
import org.csveed.api.CsvClientImpl;

class Main {

    private static final String CSV_PATTERN = "(\\,|\\r?\\n|\\r|^)(?:\"([^\"]*(?:\"\"[^\"]*)*)\"|([^\"\\,\\r\\n]*))";
    
    public static void main(String[] args) throws IOException {
        if (args == null || args.length != 1) {
            throw new RuntimeException("Chamada errada. utilize o caminho do arquivo como parametro");
        }
        
        new Main()
                .exec(args[0]);
    }

    void exec(String fileName) throws IOException {
        String file = readFile(fileName);
        List<Person> people = parseContent(file);
        people
            .stream()
            .collect(Collectors.groupingBy(Person::getEstado, Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Comparator.comparing(Entry::getKey))
            .forEach(e -> System.out.printf("%s - %s\n", e.getKey(), e.getValue()));
    }

    String readFile(String fileName) throws IOException {
        try (InputStream in = getInputStream(fileName)) {
            
            
            byte[] data = new byte[in.available()];
            in.read(data);
            return new String(data, Charset.defaultCharset());
        }
    }

    InputStream getInputStream(String fileName) throws IOException {
        if (fileName.startsWith("http")) {
            return new URL(fileName).openConnection().getInputStream();
        }
        return new FileInputStream(fileName);
    }

    List<Person> parseContent(String file) {
        if (file.replaceAll(CSV_PATTERN, "").isEmpty()) {
            CsvClient<Person> csvClient = new CsvClientImpl<>(new StringReader(file), Person.class);
            return csvClient.readBeans();
        }
        return new Gson().fromJson(file, new TypeToken<List<Person>>(){}.getType());
    }
    
}
