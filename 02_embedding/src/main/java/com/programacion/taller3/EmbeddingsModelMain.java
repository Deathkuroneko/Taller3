package com.programacion.taller3;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;

import java.nio.file.Paths;

public class EmbeddingsModelMain {

    static void main() {

        var pathToModel = Paths.get("Modelos/multilingual-e5-small.onnx");
        var pathToTokenizer = Paths.get("Modelos/multilingual-e5-small.json");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathToModel,pathToTokenizer, PoolingMode.MEAN);

        var text  = "Que paso joven";

        Response<Embedding> response = model.embed(text);

        Embedding embedding = response.content();

        System.out.println("Dimension od the embedding: " + embedding.dimension());
        System.out.println("Embedding vector: " + embedding.vectorAsList());



    }

}
