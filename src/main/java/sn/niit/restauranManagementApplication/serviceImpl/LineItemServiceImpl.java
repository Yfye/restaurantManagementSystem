package sn.niit.restauranManagementApplication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.niit.restauranManagementApplication.domain.LineItem;
import sn.niit.restauranManagementApplication.repository.LineItemRepository;
import sn.niit.restauranManagementApplication.service.LineItemService;

@Service
public class LineItemServiceImpl implements LineItemService {

    @Autowired
    private LineItemRepository lineItemRepository;

    public void saveOrUpdateLineItem(LineItem lineItem) {
        lineItemRepository.save(lineItem);
    }

    public LineItem findByLineItemId(Long lineItemId) {
        return lineItemRepository.findById(lineItemId)
                .orElseThrow(() -> new RuntimeException("LineItem does not exist."));
    }
}
