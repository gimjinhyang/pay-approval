package pay.approval.thirdparty.token.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pay.approval.properties.TokenProperties;
import pay.approval.thirdparty.token.request.TokenVerifyRequest;
import pay.approval.thirdparty.token.response.TokenVerifyResponse;

/**
 * 토큰 유효성 확인 서비스
 *
 * @author Jinhyang
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class TokenValidService {

  private final TokenProperties tokenProperties;

  /**
   * 토큰 유효성 확인
   *
   * @param param {@link TokenVerifyRequest}
   * @return 토큰 문자열
   */
  public void verify(TokenVerifyRequest param) {

    final String uri = makeUri();
    log.debug("Verify URI: {}", uri);

    // 토큰 유효성 확인
    final RestClient restClient = RestClient.create();
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
   * 토큰 유효성 확인 URL 생성
   *
   * @return
   */
  private String makeUri() {
    return StringUtils.join(tokenProperties.getUrl(), tokenProperties.getApiTokenValid());
  }

  /**
   * 오류가 있는지 확인
   *
   * @param response 요청 결과
   */
  private void verifyError(ResponseEntity<TokenVerifyResponse> response) {
    if (response.getBody() == null) {
      throw new IllegalStateException("토큰 유효성 확인에 실패 했습니다.");
    }
    if (!response.getStatusCode().is2xxSuccessful() || response.getBody().getStatus() != 200) {
      throw new IllegalStateException(response.getBody().getMessage());
    }
  }

}
