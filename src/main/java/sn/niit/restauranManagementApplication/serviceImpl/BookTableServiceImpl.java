package sn.niit.restauranManagementApplication.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sn.niit.restauranManagementApplication.domain.BookTable;
import sn.niit.restauranManagementApplication.domain.Product;
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

    public BookTable getBookTableById(Long bookingId) {
        return bookTableRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking does not exist !"));
    }

    @Override
    public List<BookTable> findBookingsByKeyword(String keyword) {
        return bookTableRepository.findByKeyword(keyword);
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

    public void deleteBooking(Long bookTableId) {
        bookTableRepository.delete(getBookTableById(bookTableId));
    }

    // @Override
    public Page<BookTable> findPaginated(int pageNumber, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);

        return bookTableRepository.findAll(pageable);
    }

}
