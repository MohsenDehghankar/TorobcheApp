package com.sharifdev.torobche.model;

import com.sharifdev.torobche.R;

public class ProfileAvatar {
    public static int getAvatarResourceId(int avatarID) {
        switch (avatarID) {
            case 2:
                return R.mipmap.avatar_2;
            case 3:
                return R.mipmap.avatar_3;
            case 4:
                return R.mipmap.avatar_4;
            case 5:
                return R.mipmap.avatar_5;
            case 6:
                return R.mipmap.avatar_6;
            case 7:
                return R.mipmap.avatar_7;
            case 8:
                return R.mipmap.avatar_8;
            default:
                return R.mipmap.avatar_1;
        }
    }
}
