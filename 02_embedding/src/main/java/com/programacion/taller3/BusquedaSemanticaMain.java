package com.programacion.taller3;



import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.nio.file.Paths;
import java.util.List;

public class BusquedaSemanticaMain {

    static void main() {

        var pathToModel = Paths.get("Modelos/multilingual-e5-small.onnx");
        var pathToTokenizer = Paths.get("Modelos/multilingual-e5-small.json");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathToModel,pathToTokenizer, PoolingMode.MEAN);


         var documenteos  = List.of("Hoy el dia esta muy lindo para salir", "mi final llegara y ya no habra un nuevo amanecer",
                 "Si el honor, la felicidad y la goria no son para mi que sean para los demas");

         List<Embedding> embeddings = documenteos.stream().map(model::embed)
                 .map(Response::content).toList();

         var consulta = "Hoy es un buen dia para morir";
         var embeddingConsulta  =model.embed(consulta).content();

         int index = 0;
         int mejorIndice = 0;
         double mejorScore = 0.0;

         for (var it:embeddings){
             double score = CosineSimilarity.between(embeddingConsulta,it);

             System.out.printf("Documento %d - Similitud: %.4f%n", index, score);

             if (score > mejorScore) {
                 mejorScore = score;
                 mejorIndice = index;
             }

             index++;
         }

        System.out.println("Frase de referencia: "+ consulta);
        System.out.printf("mejor resultado: [%d] - %s%n", mejorIndice, documenteos.get(mejorIndice));
        System.out.println("Similitud: "+mejorScore);
    }
}
