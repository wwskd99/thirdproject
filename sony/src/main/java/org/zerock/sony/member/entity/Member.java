package org.zerock.sony.member.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member {
	@Id
	private String userid;
	private String name;
	private String pwd;
	private String email;
	private String address;
	private String phone;
	private int grade;
	private int gender;// integer(1)
	private int mile;
	private boolean fromSocial;
	
	@ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole MemberRole){
        roleSet.add(MemberRole);
    }
}
