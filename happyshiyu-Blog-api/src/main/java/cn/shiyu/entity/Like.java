package cn.shiyu.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Happyshiyu
 * 点赞记录的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class Like implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int uid;
	
	private int aid;
	
}
