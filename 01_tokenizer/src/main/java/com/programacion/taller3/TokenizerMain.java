package com.programacion.taller3;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.IntArrayList;
import com.knuddels.jtokkit.api.ModelType;

public class TokenizerMain {

    static void main(){
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();

        Encoding tokenizer = registry.getEncodingForModel(ModelType.TEXT_DAVINCI_003);

        var text = "Hello, do you like tea? endoftext In the sunlit terrance of someunknownPlace.";

        IntArrayList tokenids = tokenizer.encode(text);

        System.out.println("Total tokens: " + tokenids.size());
        System.out.println(tokenids);
        System.out.println();


        //decode
        String textDecode = tokenizer.decode(tokenids);
        System.out.println("Decode: " + textDecode);

    }
}
