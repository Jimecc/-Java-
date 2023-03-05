package com.jim.mp.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Jim
 * @Description
 * @createTime 2023年03月05日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User {
    @TableId("id")
    private Long id;
    @TableField("user_name")
    private String userNmae;
    @TableField("password")
    private String password;
    @TableField("name")
    private String name;
    @TableField("age")
    private Integer age;
    @TableField("email")
    private String email;
    @TableField("birthday")
    private LocalDateTime birthday;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userNmae='" + userNmae + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
