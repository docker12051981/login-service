package com.annuaire.loginservice.model;

import com.annuaire.loginservice.enumeration.Roles;
import com.annuaire.loginservice.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class AccountUser {
    @Id
    private String id;
    @NotNull
    @Field("organisme")
    private String organismeId;
    @Field("groupes")
    @DBRef
    private List<Groupe> groupes;
    @Field("role")
    private Roles role;
    @NotNull
    @Field("identifiant")
    private String identifiant;
    @NotNull
    @Field("password")
    private String password;
    @NotNull
    @Field("status")
    private Status status;
    @NotNull
    @Field("email")
    private String email;
    private String createdBy;
    private LocalDateTime createTime;
}
