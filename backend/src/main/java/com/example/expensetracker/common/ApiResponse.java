package com.example.expensetracker.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;    # list controllers
    find backend/src/main/java -type f -name "*.java" -exec grep -Hn "@RestController\|@Controller" {} \;
    
    # list endpoint mappings
    grep -R --line-number -E "@(RequestMapping|GetMapping|PostMapping|PutMapping|DeleteMapping)" backend/src/main/java || true
    
    # list Spring components
    grep -R --line-number -E "@Service|@Repository|@Component|@Configuration" backend/src/main/java || true
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, data);
    }

    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(false, message, null);
    }
}


