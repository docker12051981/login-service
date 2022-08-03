package com.annuaire.loginservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

    /**
     * @author karim hmadi

     * @description groupe entity

     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Document(collection = "groupe")
    public class Groupe {
        @Id
        private String id;
        @NotNull
        @Indexed(unique = true)
        private String code;
        private String name;
        private String description;
    }

