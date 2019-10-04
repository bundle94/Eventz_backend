package com.eventz.io.eventz.exceptions;

import com.eventz.io.eventz.models.response.BaseResponse;
import com.eventz.io.eventz.models.response.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.util.StringUtils;

/**
 * Created by Michael.Akobundu on 3/10/2019.
 */
public class ExceptionAdvise {
    @ExceptionHandler({ServerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleServerException(ServerException e) {
        BaseResponse response = new BaseResponse();
        String responseCode = StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.SYSTEM_ERROR.getCode();
        String responseMessage = StringUtils.hasText(e.getMessage()) ? e.getMessage() : ResponseCodes.SYSTEM_ERROR.getMessage();

        response.setResponseCode(responseCode);
        response.setResponseMessage(responseMessage);
        return response;
    }

    @ExceptionHandler({InvalidParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleInvalidParameterException(InvalidParameterException e) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(ResponseCodes.BAD_REQUEST.getCode());
        response.setResponseMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler({RequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleRequestException(RequestException e) {
        BaseResponse response = new BaseResponse();
        String responseCode = StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.BAD_REQUEST.getCode();
        response.setResponseCode(responseCode);
        response.setResponseMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler({InsufficientPermissionException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResponse handlePermissionException(InsufficientPermissionException e) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.INSUFFICIENT_PERMISSION.getCode());
        response.setResponseMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse genericException(Exception e) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(ResponseCodes.SYSTEM_ERROR.getCode());
        response.setResponseMessage(ResponseCodes.SYSTEM_ERROR.getMessage());
        return response;
    }

    @ExceptionHandler({UnAuthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResponse handleUnauthorizedException(UnAuthorizedException e) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.UNAUTHORIZED.getCode());
        response.setResponseMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse handleUserNotFoundException(UserNotFoundException e) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.UNAUTHORIZED.getCode());
        response.setResponseMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.UNAUTHORIZED.getCode());
        response.setResponseMessage(e.getMessage());
        return response;
    }
}
