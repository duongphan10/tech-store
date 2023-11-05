package com.example.techstore.constant;

public class UrlConstant {

    public static class Auth {
        private static final String PRE_FIX = "/auth";

        public static final String LOGIN = PRE_FIX + "/login";
        public static final String LOGOUT = PRE_FIX + "/logout";

        private Auth() {
        }
    }

    public static class User {
        private static final String PRE_FIX = "/user";
        public static final String GET_BY_ID = PRE_FIX + "/{userId}";
        public static final String GET_CURRENT_USER = PRE_FIX + "/my";
        public static final String GET_ALL = PRE_FIX + "/all";
        public static final String CREATE = PRE_FIX + "/create";
        public static final String UPDATE = PRE_FIX;
        public static final String DELETE = PRE_FIX + "/{userId}";

        private User() {
        }
    }

}