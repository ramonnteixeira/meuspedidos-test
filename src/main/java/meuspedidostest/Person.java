package meuspedidostest;

import lombok.Getter;
import lombok.Setter;
import org.csveed.annotations.CsvFile;

@Getter
@Setter
@CsvFile(separator = ',')
public class Person {

    private String nome;
    private String cidade;
    private String estado;
    
}
