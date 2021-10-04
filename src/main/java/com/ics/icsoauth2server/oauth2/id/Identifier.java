package com.ics.icsoauth2server.oauth2.id;

import com.ics.icsoauth2server.utils.StringUtils;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONValue;
import org.apache.commons.codec.binary.Base64;

import java.io.Serializable;
import java.security.SecureRandom;

public class Identifier implements Serializable, Comparable<Identifier>, JSONAware {

    private final static int DEFAULT_BYTE_LENGTH = 32;

    private final static SecureRandom SECURE_RANDOM = new SecureRandom();

    private final String value;


    public Identifier(String value){
        if(StringUtils.isBlank(value)){
            throw new IllegalArgumentException("The value must not be blank");
        }
        this.value = value;
    }


    public Identifier(final int byteLength){
        if(byteLength<1){
            throw new IllegalArgumentException("Byte length must not be less than 1");
        }
        byte[] n = new byte[byteLength];

        SECURE_RANDOM.nextBytes(n);

        value = Base64.encodeBase64(n).toString();
    }


    public Identifier(){
        this(DEFAULT_BYTE_LENGTH);
    }

    public String getValue() {
        return value;
    }


    @Override
    public int compareTo(Identifier o) {
        return getValue().compareTo(o.getValue());
    }

    @Override
    public String toJSONString() {
        return  "\"" + JSONValue.escape(value) + '"';
    }

    @Override
    public String toString() {


        return getValue();
    }

    @Override
    public int hashCode() {
        return getValue()!=null?getValue().hashCode():0;
    }


    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(obj==null || getClass()!=obj.getClass())
            return false;

        Identifier that = (Identifier) obj;

        return getValue()!=null?getValue().equals(that.getValue()):that.getValue()==null;
    }

}
