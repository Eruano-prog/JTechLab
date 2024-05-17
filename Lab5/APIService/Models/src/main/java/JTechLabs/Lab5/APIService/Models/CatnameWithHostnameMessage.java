package JTechLabs.Lab5.APIService.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CatnameWithHostnameMessage {
    private String hostname;
    private String catname;
}
