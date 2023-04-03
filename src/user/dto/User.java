package user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	public String toString() {
		return "UserBuilder [userId=" + userId + ", name=" + name + ", nickName=" + nickName + ", password=" + password
				+ ", email=" + email + "]";
	}
	private Long userId;
	private String name;
	private String nickName;
	private String password;
	private String email;
	

}
