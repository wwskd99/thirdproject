package org.zerock.sony.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class AuthMemberDTO extends User implements OAuth2User {
    private String email;
    private String password;
    private String name;
    private boolean fromSocial;
    private Map<String, Object> attr;
    private int grade;

    public AuthMemberDTO(String username, String password, boolean fromSocial, int grade,
                             Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
        this(username,password, fromSocial, grade, authorities);
        this.attr = attr;
    }
    
    public AuthMemberDTO(String username, String password, boolean fromSocial, int grade,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.grade = grade;
        this.email = username;
        this.password = password;
        this.fromSocial = fromSocial;
}
    
    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}
