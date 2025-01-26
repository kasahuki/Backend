package net.senjay.springtest;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component("grades")
public class GradesToll {

    @Value("120")
    private int Chinese;
    @Value("140")
    private int English;
    @Value("130")
    private int Math;

}
