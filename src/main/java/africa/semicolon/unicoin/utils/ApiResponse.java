package africa.semicolon.unicoin.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.ZonedDateTime;
@Setter
@Getter
@Builder
@AllArgsConstructor
public class ApiResponse {
    private ZonedDateTime timeStamp;
    private int statusCode;
    private String path;
    private Object data;
    private Boolean isSuccessful;
}
