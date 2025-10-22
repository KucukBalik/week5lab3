package ie.atu.week5lab3.errorHandling;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDetails {

    private String fieldName;
    private String fieldValue;
}
