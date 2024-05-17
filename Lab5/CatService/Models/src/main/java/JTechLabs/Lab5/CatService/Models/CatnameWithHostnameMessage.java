package JTechLabs.Lab5.CatService.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CatnameWithHostnameMessage {
    private String catname;
    private String hostname;
}
