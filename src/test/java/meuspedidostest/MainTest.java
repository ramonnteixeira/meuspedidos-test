package meuspedidostest;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    
    @Test
    public void readJsonContent() throws IOException {
        Main main = new Main();
        byte[] data = Files.readAllBytes(new File(getClass().getResource("/content.json").getFile()).toPath());
        List<Person> people = main.parseContent(new String(data));
        Assert.assertNotNull(people);
        Assert.assertFalse(people.isEmpty());        
        Assert.assertTrue(people.size() == 14);        
    }
    
    @Test
    public void readCSVContent() throws IOException {
        Main main = new Main();
        byte[] data = Files.readAllBytes(new File(getClass().getResource("/content.csv").getFile()).toPath());
        List<Person> people = main.parseContent(new String(data));
        Assert.assertNotNull(people);
        Assert.assertFalse(people.isEmpty());        
        Assert.assertTrue(people.size() == 14);        
    }
    
}
