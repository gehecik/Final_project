package org.example.data;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ad {
    private String name;
    private String category;
    private String condition;
    private String city;
    private String description;
    private String cost;
    private String img1;
    private String img2;
    private String img3;

    public static Ad adWithFullData() {
        String nameAd = "name_" + RandomStringUtils.randomNumeric(3);
        String costAd = RandomStringUtils.randomNumeric(5);
        return Ad.builder()
                .name(nameAd)
                .category("Садоводство")
                .condition("Б/У")
                .city("Москва")
                .description("description")
                .cost(costAd)
                .img1("src/test/resources/images/img_1.jpg")
                .img2("src/test/resources/images/img_2.jpg")
                .img3("src/test/resources/images/img_3.png")
                .build();
    }

    public static Ad adNewData() {
        String nameAd = "new_name_" + RandomStringUtils.randomNumeric(3);
        String costAd = RandomStringUtils.randomNumeric(6);
        return Ad.builder()
                .name(nameAd)
                .category("Технологии")
                .condition("Новый")
                .city("Казань")
                .description("New description")
                .cost(costAd)
                .img1("src/test/resources/images/img_4.jpg")
                .build();
    }

    public static Ad adDefault() {
        return Ad.builder()
                .name("")
                .category("Авто")
                .condition("Новый")
                .city("Москва")
                .description("")
                .cost("0")
                .build();
    }

    public static Ad adWithDiffValue(String category, String condition, String city) {
        String nameAd = "name_" + RandomStringUtils.randomNumeric(3);
        String costAd = RandomStringUtils.randomNumeric(5);
        return Ad.builder()
                .name(nameAd)
                .category(category)
                .condition(condition)
                .city(city)
                .description("description")
                .cost(costAd)
                .build();
    }

    public static Ad adWithOneImg() {
        String nameAd = "name_" + RandomStringUtils.randomNumeric(3);
        String costAd = RandomStringUtils.randomNumeric(5);
        return Ad.builder()
                .name(nameAd)
                .category("Садоводство")
                .condition("Б/У")
                .city("Москва")
                .description("description")
                .cost(costAd)
                .img1("src/test/resources/images/img_3.png")
                .build();
    }
}
