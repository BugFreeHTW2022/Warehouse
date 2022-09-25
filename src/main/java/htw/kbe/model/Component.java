package htw.kbe.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@ToString
@Document(collection = "Components")
public class Component {
    @Id
    int id;
    String name;
    String description;
    String nutriscore;
    String componentGroup;
    boolean vegan;
    int price;
}
