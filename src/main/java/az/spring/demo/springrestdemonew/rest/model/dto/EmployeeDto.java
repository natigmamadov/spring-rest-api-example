package az.spring.demo.springrestdemonew.rest.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    private int age;
    private double salary;

}
