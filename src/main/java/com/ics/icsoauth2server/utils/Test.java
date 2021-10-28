//package com.ics.icsoauth2server.utils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Test {
//
//
//    public static class POJO{
//
//        int age;
//        String firstName;
//        String lastName;
//
//        public POJO(int age, String firstName, String lastName){
//            this.age = age;
//            this.firstName =firstName;
//            this.lastName = lastName;
//        }
//
//    }
//
//    public static class ContactDetails {
//        String emailId;
//        String contactNo;
//        String firstName;
//
//        public ContactDetails(String emailId, String contactNo, String firstName){
//            this.contactNo = contactNo;
//            this.firstName = firstName;
//            this.emailId = emailId;
//        }
//
//    }
//
//    public ContactDetails getContacts(List<ContactDetails> contactDetails,String firstName){
//
//        for (ContactDetails c :contactDetails) {
//            if(c.firstName.equals(firstName)) {
//                return c;
//            }
//        }
//
//        return null;
//    }
//        public static void main(String[] args) {
//         List<POJO>  pojos  = new ArrayList<>();
//         POJO pojo = new POJO(11,"abc","xyz");
//         POJO pojo1 = new POJO(12,"smith","xyz");
//
//         String s = "madam";
//
//         StringBuilder stringBuilder = new StringBuilder(s);
//         stringBuilder.reverse();
//         System.out.println(s);
//         System.out.println(stringBuilder.toString());
//
//            pojos.add(pojo);
//            pojos.add(pojo1);
//            pojos.add(new POJO(14,"jackson","xyz"));
//            pojos.add(new POJO(17,"shashank","jain"));
//
//        pojos.stream().filter(x->x.age<15).forEach(System.out::println);
//
//
//            List<ContactDetails>  contactDetails  = new ArrayList<>();
//
//            contactDetails.add(new ContactDetails("xyz@gmail.com","9902642987","abc"));
//            contactDetails.add(new ContactDetails("smith@gmail.com","9902642987","smith"));
//            contactDetails.add(new ContactDetails("jackson@gmail.com","9902642987","jackson123"));
//            contactDetails.add(new ContactDetails("shashank@gmail.com","9902642987","1324536"));
//
//
////            pojos.stream().filter(x->new)
//
//
//
//        }
//
//
//
//}
