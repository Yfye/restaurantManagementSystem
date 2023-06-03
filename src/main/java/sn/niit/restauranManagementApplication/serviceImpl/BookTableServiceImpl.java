package sn.niit.restauranManagementApplication.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.niit.restauranManagementApplication.domain.BookTable;
import sn.niit.restauranManagementApplication.dto.BookTableDto;
import sn.niit.restauranManagementApplication.repository.BookTableRepository;
import sn.niit.restauranManagementApplication.service.BookTableService;

@Service
public class BookTableServiceImpl implements BookTableService {

    private final BookTableRepository bookTableRepository;

    public BookTableServiceImpl(BookTableRepository bookTableRepository) {
        this.bookTableRepository = bookTableRepository;
    }

    @Override
    public List<BookTable> getBookingTables() {
        return bookTableRepository.findAll();
    }

    @Override
    public List<BookTable> getBookingTablesByEmail(String email) {
        return bookTableRepository.findByEmail(email);
    }

    @Override
    public BookTable saveBookingTable(BookTableDto bookTableDto) {
        if (bookTableDto.getEmail() == "" || bookTableDto.getName() == "" || bookTableDto.getMessage() == "")
            throw new RuntimeException("An error occured. Cannot book table.");
        return bookTableRepository
                .save(new BookTable(bookTableDto.getName(), bookTableDto.getEmail(), bookTableDto.getMessage()));

    }

}
