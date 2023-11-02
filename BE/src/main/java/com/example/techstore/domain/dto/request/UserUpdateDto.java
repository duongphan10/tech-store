package com.example.techstore.domain.dto.request;

import com.example.techstore.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserUpdateDto {
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String fullName;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String username;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String gender;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String birthday;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String phone;
    @NotBlank(message = ErrorMessage.NOT_BLANK_FIELD)
    private String email;
    @NotNull(message = ErrorMessage.INVALID_SOME_THING_FIELD_IS_REQUIRED)
    private MultipartFile avatar;
}
