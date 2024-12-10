package pay.approval.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pay.approval.request.ApprovalRequest;
import pay.approval.response.ErrorResponse;
import pay.approval.service.ApprovalService;

/**
 * 결제 승인 컨트롤러
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/approval")
public class ApprovalController {

  private final ApprovalService approvalService;

  /**
   * 결제 승인 요청
   *
   * @param param {@link ApprovalRequest}
   * @return
   */
  @PostMapping
  public ResponseEntity<?> post(@RequestBody ApprovalRequest param) {
    try {

      approvalService.approval(param);
      return ResponseEntity.ok(new ErrorResponse(HttpStatus.OK, "ok"));

    } catch (IllegalStateException e) {
      log.warn("caught a " + e.getClass() + " with message: " + e.getMessage(), e);
      return ResponseEntity.ok(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
    } catch (Exception e) {
      log.warn("caught a " + e.getClass() + " with message: " + e.getMessage(), e);
      return ResponseEntity.ok(new ErrorResponse(HttpStatus.BAD_REQUEST, "결제 승인을 실패했습니다"));
    }
  }

}
