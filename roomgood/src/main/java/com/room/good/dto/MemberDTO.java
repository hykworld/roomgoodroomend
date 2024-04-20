package com.room.good.dto;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberDTO {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phone;
    private String adress;
    private Long money;
    private Long mileage;
    private String grade;
    private String birth;
    public MemberDTO(String email, String password){
        this.email = email;
        this.password = password;

    }
}
