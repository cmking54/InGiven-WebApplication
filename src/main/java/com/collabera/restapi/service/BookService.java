package com.collabera.restapi.service;


import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.collabera.restapi.Repository.BookRepository;
import com.collabera.restapi.model.Book;
import com.collabera.restapi.model.BookDTO;
import com.collabera.restapi.model.BookMapper;

@Service
@Transactional
public class BookService {

	private final BookRepository bookRepository;
	private final BookMapper bookMapper;

	public BookService(BookRepository bookRepository, BookMapper bookMapper) {
		this.bookMapper = bookMapper;
		this.bookRepository = bookRepository;
	}

	public List<BookDTO> findALl() {
		return bookRepository.findAll().stream().map(m -> bookMapper.toDto(m)).collect(Collectors.toList());
	}

	public BookDTO findOne(Long id) {
		Optional<Book> movieOp = bookRepository.findById(id);
		if (movieOp.isPresent()) {
			return bookMapper.toDto(movieOp.get());
		}
		return null;
	}

	public BookDTO save(BookDTO book) {
		Book entity = bookMapper.toEntity(book);
		Book saved = bookRepository.save(entity);
		return bookMapper.toDto(saved);
	}

	public void delete(Long id) {
		bookRepository.deleteById(id);
	}

}
