package com.example.techstore.constant;

public enum SortByDataConstant implements SortByInterface {

    USER {
        @Override
        public String getSortBy(String sortBy) {
            switch (sortBy) {
                case "fullName":
                    return "full_name";
                case "username":
                    return "username";
                case "gender":
                    return "gender";
                case "birthday":
                    return "birthday";
                case "phone":
                    return "phone";
                case "email":
                    return "email";
                case "lastModifiedDate":
                    return "last_modified_date";
                default:
                    return "created_date";
            }
        }
    },
    NEWS {
        @Override
        public String getSortBy(String sortBy) {
            switch (sortBy) {
                case "title":
                    return "title";
                case "avatar":
                    return "avatar";
                case "content":
                    return "content";
                case "status":
                    return "status";
                case "summary":
                    return "summary";
                case "categoryId":
                    return "category_id";
                case "lastModifiedDate":
                    return "last_modified_date";
                default:
                    return "created_date";
            }
        }
    },
}
