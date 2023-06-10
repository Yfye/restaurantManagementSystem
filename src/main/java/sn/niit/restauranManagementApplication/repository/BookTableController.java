package sn.niit.restauranManagementApplication.repository;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sn.niit.restauranManagementApplication.domain.BookTable;
import sn.niit.restauranManagementApplication.dto.BookTableDto;
import sn.niit.restauranManagementApplication.serviceImpl.BookTableServiceImpl;

@Controller
@RequestMapping("/booking")
public class BookTableController {

    private final BookTableServiceImpl bookTableServiceImpl;

    public BookTableController(BookTableServiceImpl bookTableServiceImpl) {
        this.bookTableServiceImpl = bookTableServiceImpl;
    }

    @GetMapping("/list")
    public String showAllProduit(Model model, String keyword) {
        if (keyword != null) {
            List<BookTable> bookings = bookTableServiceImpl.findBookingsByKeyword(keyword);
            model.addAttribute("listProduit", bookings);
        } else {
            List<BookTable> bookings = bookTableServiceImpl.getBookingTables();
            model.addAttribute("listProduit", bookings);
        }

        return showPaginatedPage(1, model);
    }

    @PostMapping(path = "/save", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public void bookTable(BookTableDto bookTableDto, HttpServletResponse response) throws IOException {
        bookTableServiceImpl.saveBookingTable(bookTableDto);
        response.sendRedirect("/site/home");
    }

    @GetMapping("/new")
    public String showForm(BookTableDto booking, Model model) {
        // model.addAttribute("categories", categoryService.getCategories());
        return "admin/booking-new";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("booking", bookTableServiceImpl.getBookTableById(id));

        return "admin/booking-edit";
    }

    @PostMapping("/update/{id}")
    public String updateBooking(@PathVariable("id") Long id, BookTableDto booking) {
        bookTableServiceImpl.saveBookingTable(booking);
        return "redirect:/booking/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") Long id) {
        bookTableServiceImpl.deleteBooking(id);
        return "redirect:/booking/list";
    }

    @GetMapping("/list/page/{pageNumber}")
    public String showPaginatedPage(@PathVariable("pageNumber") int pageNumber, Model model) {
        int pageSize = 5;
        Page<BookTable> page = bookTableServiceImpl.findPaginated(pageNumber, pageSize);
        List<BookTable> bookings = page.getContent();

        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("bookings", bookings);
        model.addAttribute("page", page);

        return "admin/booking-list";

    }

}
