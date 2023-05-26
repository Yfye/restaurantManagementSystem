package sn.niit.restauranManagementApplication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import sn.niit.restauranManagementApplication.domain.LineItem;
import sn.niit.restauranManagementApplication.repository.LineItemRepository;
import sn.niit.restauranManagementApplication.service.LineItemService;

public class LineItemServiceImpl implements LineItemService {

    @Autowired
    private LineItemRepository lineItemRepository;

    public LineItem findById(Long lineItemId) {
        return lineItemRepository.findById(lineItemId)
                .orElseThrow(() -> new RuntimeException("LineItem does not exist."));
    }
}
