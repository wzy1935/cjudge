package com.wzebra.cjudge.algorithm.encrypt;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EncryptInfo {
    int userId;
    long time;

    public EncryptInfo(int userId) {
        this.userId = userId;
        this.time = System.currentTimeMillis();
    }

    public EncryptInfo(int userId, long time) {
        this.userId = userId;
        this.time = time;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - time > 259200000;
    }

}
