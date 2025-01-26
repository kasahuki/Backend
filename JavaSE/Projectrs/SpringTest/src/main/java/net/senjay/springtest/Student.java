package net.senjay.springtest;
import lombok.*;
// 导入lombok
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Data // getter and setter
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 全参构造
@Component("student")
public class Student {

    @Autowired
    private GradesToll grades;

    @Value("Senjay")
    private String name;
    @Value("#{18+2}") // SpEL表达式
    private int age;
    @Value("M")
    private char gender;
}
