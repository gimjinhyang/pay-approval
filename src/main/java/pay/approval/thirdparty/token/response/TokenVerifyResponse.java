/*
 * Copyright ⓒ 2011 Hellomarket Inc. All Rights Reserved
 */
package pay.approval.thirdparty.token.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 토큰 유효성 확인 결과
 *
 * @author Jinny
 */
@Getter
@Setter
@ToString
public class TokenVerifyResponse {

  /**
   * 상태값
   */
  private int status;

  /**
   * 상태 메시지
   */
  private String message;

}
