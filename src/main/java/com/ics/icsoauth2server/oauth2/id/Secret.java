package com.ics.icsoauth2server.oauth2.id;

import net.jcip.annotations.Immutable;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import static java.nio.charset.StandardCharsets.*;

@Immutable
public class Secret implements Serializable {


    private static final long serialVersionUID = 1L;

    public static final int DEFAULT_BYTE_LENGTH = 32;

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private byte[] value;

    private final Date expDate;

    public Secret(final String value) {
        this(value, null);
    }


    public Secret(final String value, final Date expDate) {
        this.value = value.getBytes(UTF_8);
        this.expDate = expDate;
    }


    public Secret(final int byteLength) {
        this(byteLength, null);
    }


    public Secret(final int byteLength, final Date expDate) {
        if (byteLength < 1)
            throw new IllegalArgumentException("The byte length must be a positive integer");

        byte[] n = new byte[byteLength];

        SECURE_RANDOM.nextBytes(n);

        value = Base64.getUrlEncoder().withoutPadding().encodeToString(n).getBytes(UTF_8);

        this.expDate = expDate;
    }

    public Secret() {

        this(DEFAULT_BYTE_LENGTH);
    }



    public String getValue() {

        if (value == null) {
            return null; // value has been erased
        }

        return new String(value, UTF_8);
    }


    public byte[] getValueBytes() {
        return value;
    }


    public byte[] getSHA256() {

        if (value == null) {
            return null;
        }

        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return sha256.digest(value);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



    public void erase() {

        if (value == null) {
            return; // Already erased
        }

        Arrays.fill(value, (byte) 0);

        value = null;
    }



    public Date getExpirationDate() {
        return expDate;
    }



    public boolean expired() {

        if (expDate == null) {
            return false; // never expires
        }

        final Date now = new Date();

        return expDate.before(now);
    }



    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }


//    @Deprecated
//    public boolean equalsSHA256Based(final Secret other) {
//
//        if (other == null) {
//            return false;
//        }
//
//        byte[] thisHash = getSHA256();
//        byte[] otherHash = other.getSHA256();
//
//        if (thisHash == null || otherHash == null) {
//            return false;
//        }
//
//        return ConstantTimeUtils.areEqual(thisHash, otherHash);
//    }



//    @Override
////    public boolean equals(final Object o) {
////        if (this == o) return true;
////        if (value == null) return false;
////        if (!(o instanceof Secret)) return false;
////        Secret otherSecret = (Secret) o;
////        return equalsSHA256Based(otherSecret);
////    }




}
