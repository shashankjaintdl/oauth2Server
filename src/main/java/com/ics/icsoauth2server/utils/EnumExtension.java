//package com.ics.icsoauth2server.utils;
//
//
//public class EnumExtension {
//
//    public static enum eOrderBy {
//        enAsc("Asc", "Ascending Order"), enDesc("Desc", "Descending Order");
//
//        private String firstvalue;
//        private String secondValue;
//
//        eOrderBy(String firstvalue, String secondValue) {
//            this.firstvalue = firstvalue;
//            this.secondValue = secondValue;
//        }
//
//        public String getKey() {
//            return this.firstvalue;
//        }
//
//        public String getValue() {
//            return this.secondValue;
//        }
//
//        public static String getFirstValue(String value) {
//            for (EnumsExtension.eOrderBy item : EnumsExtension.eOrderBy.values()) {
//                if (item.getValue().equalsIgnoreCase(value))
//                    return item.getKey();
//            }
//            return "";
//        }
//
//        public static String getSecondValue(String value) {
//            for (EnumsExtension.eOrderBy item : EnumsExtension.eOrderBy.values()) {
//                if (item.getKey().equalsIgnoreCase(value))
//                    return item.getValue();
//            }
//            return "";
//        }
//    }
//}
