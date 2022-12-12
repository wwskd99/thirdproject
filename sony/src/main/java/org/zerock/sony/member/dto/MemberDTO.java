package org.zerock.sony.member.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	private String name;	// varchar(10)
	private String userid;
	private String pwd;
	private String email;
	private String address;
	private String phone;
	private int grade;
	private int gender;// integer(1)
	private int mile;
}
