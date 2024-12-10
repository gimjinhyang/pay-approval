package pay.approval.thirdparty.token.service;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import pay.approval.properties.TokenProperties;
import pay.approval.thirdparty.token.request.TokenVerifyRequest;
import pay.approval.thirdparty.token.response.TokenVerifyResponse;

/**
 * 토큰 유효성 확인 서비스
 *
 * @author Jinhyang
 */
@RequiredArgsConstructor
@Component
public class TokenVerifyService {

  private final TokenProperties tokenProperties;

  /**
   * 토큰 유효성 확인
   *
   * @param param {@link TokenVerifyRequest}
   * @return 토큰 문자열
   */
  public void verify(TokenVerifyRequest param) {

    final String uri = makeVerifyUri();
    final RestClient restClient = RestClient.create();

    // 카드 등록 요청
    final ResponseEntity<TokenVerifyResponse> response = restClient.post()
                                                             .uri(uri)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .body(param)
                                                             .retrieve()
                                                             .toEntity(TokenVerifyResponse.class);

    // 오류 확인
    verifyError(response);
  }

  /**
   * 토큰 발급 요청 URL 생성
   *
   * @return
   */
  private String makeVerifyUri() {
    return StringUtils.join(tokenProperties.getUrl(), tokenProperties.getApiTokenVerify());
  }

  /**
   * 오류가 있는지 확인
   *
   * @param response 요청 결과
   */
  private void verifyError(ResponseEntity<TokenVerifyResponse> response) {
    if (response.getBody() == null) {
      throw new IllegalStateException("카드 등록을 실패 했습니다.");
    }
    if (!response.getStatusCode().is2xxSuccessful() || response.getBody().getStatus() != 200) {
      throw new IllegalStateException(response.getBody().getMessage());
    }
  }

}
