package ru.otus.home6.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne()
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<BookComment> comments = new ArrayList<>();


}
