package com.programacion.taller3;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Paths;

public class SimilitudMain {

    static void main() {

        var pathToModel = Paths.get("Modelos/multilingual-e5-small.onnx");
        var pathToTokenizer = Paths.get("Modelos/multilingual-e5-small.json");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathToModel,pathToTokenizer, PoolingMode.MEAN);

        var text  = "Que paso joven";

        var embedding1 = model.embed("Buenos dias, buenas tardes, buenas noches").content();
        var embedding2 = model.embed("Yo quiero comer un helado en un dia lluvioso").content();
        var embedding3 = model.embed("El dia de mi muertes puede ser el mejor dia").content();

        double sim12 = CosineSimilarity.between(embedding1,embedding2);
        double sim13 = CosineSimilarity.between(embedding1,embedding3);
        double sim23 = CosineSimilarity.between(embedding2,embedding3);

        System.out.println("sim12: " + sim12 + "\nsim13: " + sim13 + "\nsim23: " + sim23);



    }
}
