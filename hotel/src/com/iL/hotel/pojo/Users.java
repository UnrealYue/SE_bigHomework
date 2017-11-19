package com.iL.hotel.pojo;

import com.iL.hotel.common.encrypt.MD5Util;

import javax.persistence.*;


/**
 * Created by 刘天鸿 on 2.17/11/16
 */

@Entity
@Table(name = "Users",schema = "hotel")
public class Users {

    private String id;
    private String username;
    private String password;
    private Boolean isDeleted;

    private static final Integer minUsernameSize = 6;
    private static final Integer maxUsernameSize = 20;
    private static final Integer minPasswordSize = 6;


    public Users(){
        this.isDeleted = false;
    }

    public Users(String username, String password){
        this.username = username;
        this.password = password;
        this.isDeleted = false;
    }

    public Users(String id, String username, String password, Boolean isDeleted) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    public boolean checkValid(){
        if(this.username.length() >= minUsernameSize
                && this.username.length() <=maxUsernameSize
                && this.password.length() >=minPasswordSize){
           this.password = MD5Util.getMD5String(username+password+username.charAt(minUsernameSize-1)+password.charAt(minPasswordSize-1));
            return true;
        }
        else return false;
    }

    public static String getEncryptedPassword(String username,String password){
        return MD5Util.getMD5String(username+password+username.charAt(minUsernameSize-1)+password.charAt(minPasswordSize-1));
    }


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, length = 12)
    public String getId(){return this.id;}

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "is_deleted", nullable = false)
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
