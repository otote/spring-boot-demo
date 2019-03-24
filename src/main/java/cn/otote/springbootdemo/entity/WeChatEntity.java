package cn.otote.springbootdemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/***
 * Created on 2019-03-24 15:26
 * Created by otote
 *
 ***/
@Getter
@Setter
@ToString
public class WeChatEntity implements Serializable {
    String code;
    String openid;
    String encryptedData;
    String iv;
    String session_key;
}
