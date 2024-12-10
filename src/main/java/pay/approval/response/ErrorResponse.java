package pay.approval.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 에러 응답 모델
 *
 * @author Jinhyang
 */
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

  @JsonProperty("status")
  private int status = HttpStatus.NOT_FOUND.value();

  @JsonProperty("message")
  private String message;

  public ErrorResponse(HttpStatus status, String message) {
    this.status = status.value();
    this.message = message;
  }

}
