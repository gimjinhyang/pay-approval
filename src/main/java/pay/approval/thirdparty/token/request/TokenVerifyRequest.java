package pay.approval.thirdparty.token.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 토큰 유효성 확인 파라미터
 *
 * @author Jinhyang
 */
@Getter
@Setter
@ToString
public class TokenVerifyRequest {

  /**
   * 토큰 값
   */
  private String token;

  public TokenVerifyRequest(String token) {
    this.token = token;
  }

}
