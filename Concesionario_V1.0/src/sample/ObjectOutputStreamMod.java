package sample;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectOutputStreamMod extends ObjectOutputStream {

    protected void writeStreamHeader (){

    }

    public ObjectOutputStreamMod() throws IOException {
        super();
    }

    public ObjectOutputStreamMod(OutputStream file) throws IOException{
        super(file);
    }


}
