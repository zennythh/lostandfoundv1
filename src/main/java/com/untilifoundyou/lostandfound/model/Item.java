package com.untilifoundyou.lostandfound.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.untilifoundyou.lostandfound.enums.ItemStatus;
import com.untilifoundyou.lostandfound.enums.ItemCampus;
import com.untilifoundyou.lostandfound.enums.ItemCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description = "";

    @Column(nullable = false)
    private String location = "";

    @Column(nullable = false)
    private LocalDateTime reportedOn = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime foundOn = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemCampus campus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ItemCategory category;

    @Column(nullable = false)
    private Boolean deleted = false;

    /*JsonManagedReference
    @JsonProperty("author")
    @ManyToOne(fetch = FetchType.EAGER)*/
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "image_path")
    private String imagePath;


}
