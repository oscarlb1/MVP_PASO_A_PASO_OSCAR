package com.enrique.rakutentv.login_user;

import com.enrique.rakutentv.beans.User;

public interface LoginUserContract {
    public interface View {
        void sucessLogin(User usuario);
        void failureLogin(String message);

    }
    public interface Presenter {
        void getUser(User usuario);
    }
    public interface Model {
        /*Programación Reactiva*/
        interface OnLoginUserListener {
            void onFinished(User usuario);
            void onFailure(String error);
        }
        void getUserService(OnLoginUserListener onLoginUserListener,
                            User usuario);
    }
}
