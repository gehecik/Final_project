package org.example.data;

import lombok.*;
import org.openqa.selenium.WebElement;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdCard {
    private String imgSrc;
    private String name;
    private String city;
    private String cost;
    private WebElement button;
    private WebElement card;
}
