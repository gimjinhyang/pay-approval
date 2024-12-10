package pay.approval.service;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pay.approval.request.ApprovalRequest;
import pay.approval.thirdparty.token.request.TokenVerifyRequest;
import pay.approval.thirdparty.token.service.TokenVerifyService;

/**
 * 결제 승인 서비스
 *
 * @author Jinhyang
 */
@RequiredArgsConstructor
@Component
public class ApprovalService {

  private final TokenVerifyService tokenVerifyService;

  /**
   * 결제 승인 처리
   *
   * @param param {@link ApprovalRequest}
   */
  public void approval(ApprovalRequest param) {

    // 토큰 유효성 확인
    //    requestToken(param);


    // 결제 승인 처리


  }

  /**
   * 토큰 유효성 확인
   *
   * @param param {@link ApprovalRequest}
   * @return 토큰 문자열
   */
  private void requestToken(ApprovalRequest param) {
    final TokenVerifyRequest token = new TokenVerifyRequest(param.getToken());
    tokenVerifyService.verify(token);
  }

}
