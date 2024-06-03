package JTechLabs.Lab5.APIService.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatnameWithHostnameMessage {
    private String hostname;
    private String catname;
}
