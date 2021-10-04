package com.ics.icsoauth2server.oauth2.id;

import net.jcip.annotations.Immutable;

@Immutable
public class ClientID extends Identifier{


    public ClientID(final String value){
        super(value);
    }

    public ClientID(final Identifier value){
        super(value.getValue());
    }

    public ClientID(final int byteLength){
        super(byteLength);
    }


    public ClientID() {
        super();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ClientID &&
                this.toString().equals(obj.toString());
    }

}
