package ru.otus.home7.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.home7.domain.Book;
import ru.otus.home7.dto.BookCreateDto;
import ru.otus.home7.dto.BookDto;
import ru.otus.home7.dto.BookUpdateDto;
import ru.otus.home7.mapper.BookMapper;
import ru.otus.home7.repository.BookRepository;
import ru.otus.home7.service.BookService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());

    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            return bookMapper.toDto(book.get());
        } else {
            throw new EntityNotFoundException("Book not found with id " + id);

        }
    }

    @Override
    public Book create(BookCreateDto dto) {
        return bookRepository.save(bookMapper.toNewEntity(dto));

    }

    @Override
    public Book update(BookUpdateDto dto) {
        return bookMapper.toExistEntity(dto);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
