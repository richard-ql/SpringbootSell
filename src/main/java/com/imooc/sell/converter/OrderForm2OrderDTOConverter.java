package com.imooc.sell.converter;

import com.google.gson.Gson;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.form.OrderForm;

public class OrderForm2OrderDTOConverter {
    private static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        return null;
    }
}
