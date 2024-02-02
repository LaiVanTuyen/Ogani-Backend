package com.vn.OganiBE.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {


    @NotNull(message="Username rỗng")
    @NotEmpty(message="Username rỗng")
    @Size(min=5,max=30,message="Username từ 5-30 ký tự")
    @Schema(description="Username",example="admin",required=true)
    private String username;

    @NotNull(message="Email rỗng")
    @NotEmpty(message="Email rỗng")
    @Size(min =5,max=30, message="Email từ 5-30 ký tự")
    @Email(message="Email không hợp lệ")
    @Schema(description = "Email",example="admin@gmail.com",required=true)
    private String email;

    @NotNull(message="Mật khẩu rỗng")
    @NotEmpty(message="Mật khẩu rỗng")
    @Size(min=6,max=30,message="Mật khẩu từ 6-30 ký tự")
    @Schema(description="Mật khẩu",example="123456")
    private String password;

    private Set<String> role;
}
