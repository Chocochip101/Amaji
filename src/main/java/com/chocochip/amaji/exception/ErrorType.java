package com.chocochip.amaji.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    SECURITY_EXCEPTION("SECURITY-001", "로그인이 필요합니다."),

    USER_NOT_FOUND("AUTH-001", "해당 유저를 찾을 수 없습니다."),
    INVALID_TOKEN("AUTH-002", "유효하지 않은 토큰입니다."),
    TOKEN_PAYLOAD_EXTRACTION_FAILURE("AUTH-003", "토큰 페이로드 추출에 실패했습니다"),
    INVALID_AUTH_KEY("AUTH-004", "올바르지 않은 인증키입니다."),
    NURIGO_EXCEPTION("AUTH-005", "서버 내 외부모듈 통신에러"),

    MISSING_REQUIRED_VALUE_ERROR("COMMON-001", "필수 요청값이 누락되었습니다."),
    NOT_ALLOWED_PERMISSION_ERROR("COMMON-002", "허용되지 않은 권한입니다."),
    DUPLICATED_REQUEST_ERROR("COMMON-003", "중복된 요청입니다."),
    INVALID_REQUEST_ERROR("COMMON-004", "올바르지 않은 데이터 요청입니다."),
    ASYNC_HANDLING_ERROR("COMMON-005", "비동기 처리에서 문제가 발생했습니다."),
    NETWORK_ERROR("COMMON-006", "네트워크 처리에서 문제가 발생했습니다."),
    NICKNAME_FORMAT_ERROR("USER-001", "올바르지 않은 닉네임 입력 양식입니다."),
    EMAIL_FORMAT_ERROR("USER-002", "올바르지 않은 이메일 입력 양식입니다."),
    DUPLICATED_NICKNAME_ERROR("USER-003", "중복된 닉네임입니다."),
    DUPLICATED_EMAIL_ERROR("USER-004", "중복된 이메일입니다."),
    CITY_NAME_ERROR("CITY-001", "잘못된 도시 이름입니다."),
    CITY_NOT_FOUND("CITY-002", "도시를 찾을 수 없습니다."),
    RESTAURANT_NOT_FOUND("RESTAURANT-001", "식당을 찾을 수 없습니다."),
    SERVICE_BEING_CHECKED("SERVICE-001", "서비스가 점검중입니다."),
    ORDER_DETAIL_NOT_EXISTS("ADMIN-001", "존재하지 않는 주문 상세입니다."),
    ADMIN_TOKEN_VERIFY_FAILURE("ADMIN-002", "어드민 토큰이 아닙니다."),
    ARTIST_NOT_FOUND("ADMIN-003", "해당 ID에 대응되는 아티스트가 없습니다."),
    EVENT_NOT_FOUND("ADMIN-004", "해당 ID에 대응되는 이벤트가 없습니다."),
    THREAD_INTERRUPTED("ADMIN-005", "쓰레드에 문제가 발생했습니다."),
    UNEXPECTED_SERVER_ERROR("SERVER-001", "서버 관리자에게 문의하세요.");
    private final String errorCode;
    private final String message;
}
