package cn.shiyu.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String username;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private int role;
	
	private String headImage;
	
	private String createDate;
}
