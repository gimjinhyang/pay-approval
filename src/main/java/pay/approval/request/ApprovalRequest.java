package pay.approval.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 결제 승인 파라미터
 *
 * @author Jinhyang
 */
@Getter
@Setter
@ToString
public class ApprovalRequest {

  /**
   * 토큰 값
   */
  private String token;

}
