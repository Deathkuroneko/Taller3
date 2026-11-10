package com.programacion.taller3;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.OnnxEmbeddingModel;
import dev.langchain4j.model.embedding.onnx.PoolingMode;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import java.nio.file.Paths;
import java.util.List;

public class InMemoryEmbeddingStoreMain {

    static void main() {

        var pathToModel = Paths.get("Modelos/multilingual-e5-small.onnx");
        var pathToTokenizer = Paths.get("Modelos/multilingual-e5-small.json");

        OnnxEmbeddingModel model = new OnnxEmbeddingModel(pathToModel,pathToTokenizer, PoolingMode.MEAN);

        var documentos  = List.of("Hoy el dia esta muy lindo para salir",
                "mi final llegara y ya no habra un nuevo amanecer",
                "Si el honor, la felicidad y la goria no son para mi que sean para los demas");


        InMemoryEmbeddingStore<TextSegment> store = new InMemoryEmbeddingStore<>();

        documentos.forEach(it -> {
            String key = "doc-" + (store.size()+1);
            var embedding = model.embed(it);

            store.add(key, embedding.content(), TextSegment.from(it));
        });

        var consulta = "Hoy es un buen dia para morir";
        var searchQuery = EmbeddingSearchRequest.builder()
                .queryEmbedding(model.embed(consulta).content())
                .maxResults(2)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult =  store.search(searchQuery);
        System.out.println("Referencia: " + consulta);
        for (var it:searchResult.matches()){

            System.out.printf("Score: %.4f - %s%n", it.score(), it.embedded().text());
        }

    }
}
