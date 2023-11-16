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

    PRODUCT {
        @Override
        public String getSortBy(String sortBy) {
            switch (sortBy) {
                case "name":
                    return "name";
                case "screenTechnology":
                    return "screen_technology";
                case "scanFrequency":
                    return "scan_frequency";
                case "batteryCapacity":
                    return "battery_capacity";
                case "weight":
                    return "weight";
                case "supplier":
                    return "supplier";
                case "launchDate":
                    return "launch_date";
                case "lastModifiedDate":
                    return "last_modified_date";
                default:
                    return "created_date";
            }
        }
    },

    PRODUCT_OPTION {
        @Override
        public String getSortBy(String sortBy) {
            switch (sortBy) {
                case "ram":
                    return "ram";
                case "storageCapacity":
                    return "storage_capacity";
                case "price":
                    return "price";
                case "quantity":
                    return "quantity";
                case "status":
                    return "status";
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
    SLIDE {
        @Override
        public String getSortBy(String sortBy) {
            switch (sortBy) {
                case "avatar":
                    return "avatar";
                case "status":
                    return "status";
                case "productId":
                    return "product_id";
                case "lastModifiedDate":
                    return "last_modified_date";
                default:
                    return "position";
            }
        }
    },

}
