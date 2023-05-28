package sn.niit.restauranManagementApplication.service;

import sn.niit.restauranManagementApplication.domain.LineItem;

public interface LineItemService {

    void saveOrUpdateLineItem(LineItem lineItem);

    LineItem findByLineItemId(Long lineItemId);
}
