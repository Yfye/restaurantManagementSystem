package sn.niit.restauranManagementApplication.repository;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sn.niit.restauranManagementApplication.domain.BookTable;
import sn.niit.restauranManagementApplication.dto.BookTableDto;
import sn.niit.restauranManagementApplication.serviceImpl.BookTableServiceImpl;

@RestController
@RequestMapping("/booking")
public class BookTableController {

    private final BookTableServiceImpl bookTableServiceImpl;

    public BookTableController(BookTableServiceImpl bookTableServiceImpl) {
        this.bookTableServiceImpl = bookTableServiceImpl;
    }

    // @PostMapping("/save")
    // public ResponseEntity<BookTable> bookTable(@RequestBody BookTableDto
    // bookTableDto) {
    // return new
    // ResponseEntity<BookTable>(bookTableServiceImpl.saveBookingTable(bookTableDto),
    // HttpStatus.CREATED);
    // }
    @PostMapping(path = "/save", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public void bookTable(BookTableDto bookTableDto, HttpServletResponse response) throws IOException {
        bookTableServiceImpl.saveBookingTable(bookTableDto);
        response.sendRedirect("/site/home");
    }

}
