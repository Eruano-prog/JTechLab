package JTechLabs.Lab5.CatService.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CatWithHostnameMessage {
    String hostName;
    CatDTO cat;
}
