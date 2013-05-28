/**
 * 
 * @author 王铁堂 (Wang Tietang)
 * @email mylife1000@gmail.com
 * @blog http://blog.csdn.net/myloon
 * @googleCode http://code.google.com/u/mylife1000/
 * @date 2011年03月05日 11时49分26秒  创建 By MyBatis Hare Generator code tools.
 * @comments Not the code written,is lonely!
 */
package hawthorstein.twitter.entity;
import java.io.Serializable;
/**
* Users
*/
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	/**id**/
	private Integer id;
  
	/**username**/
	private String username;
	/**email**/
	private String email;
	/**password**/
	private String password;
	/**status**/
	private String status;
	
	public Integer getId() {
		return id;
	}

	public void setId(
		Integer id) {
		this.id = id;
	}
 
	public String getUsername() {
		return username;
	}

	public void setUsername(
		String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(
		String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(
		String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(
		String status) {
		this.status = status;
	}


}