package JTechLabs.Lab5.APIService.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HostGetMessage {
    public Integer requestID;
    public String hostName;
}
