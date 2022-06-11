package az.spring.demo.springrestdemonew.rest.model.response;


import az.spring.demo.springrestdemonew.rest.model.dto.EmployeeDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {
    List<EmployeeDto> employees;
}
