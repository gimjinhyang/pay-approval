package pay.approval.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 토큰 Property
 *
 * @author Jinhyang
 */
@Getter
@Setter
@ToString
@ConfigurationProperties("token")
public class TokenProperties {

  /**
   * 주소
   */
  private String url;

  /**
   * 토큰 유효성 확인 API
   */
  private String apiTokenValid;

}
