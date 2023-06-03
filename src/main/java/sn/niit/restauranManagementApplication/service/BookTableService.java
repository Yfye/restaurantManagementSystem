package sn.niit.restauranManagementApplication.service;

import java.util.List;

import sn.niit.restauranManagementApplication.domain.BookTable;
import sn.niit.restauranManagementApplication.dto.BookTableDto;

public interface BookTableService {

    BookTable saveBookingTable(BookTableDto bookTableDto);

    List<BookTable> getBookingTables();

    List<BookTable> getBookingTablesByEmail(String email);

}
